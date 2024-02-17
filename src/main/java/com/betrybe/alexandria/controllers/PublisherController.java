package com.betrybe.alexandria.controllers;

import com.betrybe.alexandria.controllers.dto.PublisherDTO;
import com.betrybe.alexandria.controllers.dto.ResponseDTO;
import com.betrybe.alexandria.models.entities.Publisher;
import com.betrybe.alexandria.service.PublisherService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/publishers")
public class PublisherController {

  private PublisherService publisherService;

  @Autowired
  public PublisherController(PublisherService publisherService) {
    this.publisherService = publisherService;
  }

  @PostMapping()
  public ResponseEntity<ResponseDTO<Publisher>> createPublisher(
      @RequestBody PublisherDTO PublisherDTO) {
    Publisher newPublisher = publisherService.insertPublisher(
        PublisherDTO.toPublisher(PublisherDTO));
    ResponseDTO<Publisher> responseDTO = new ResponseDTO<>(
        "Editora criada com sucesso!", newPublisher);
    return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
  }

  @PutMapping("/{PublisherId}")
  public ResponseEntity<ResponseDTO<Publisher>> updatePublisher(
      @PathVariable Long PublisherId,
      @RequestBody PublisherDTO PublisherDTO
  ) {
    Optional<Publisher> PublisherOptional = publisherService.updatePublisher(
        PublisherDTO.toPublisher(PublisherDTO),
        PublisherId);

    if (PublisherOptional.isEmpty()) {
      ResponseDTO<Publisher> responseDTO = new ResponseDTO<>(
          String.format("Não foi encontrado a editora de ID %d", PublisherId),
          null
      );
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
    }
    ResponseDTO<Publisher> responseDTO = new ResponseDTO<>(
        "Editora atualizado com sucesso!",
        PublisherOptional.get()
    );
    return ResponseEntity.ok(responseDTO);
  }

  @DeleteMapping("/{PublisherId}")
  public ResponseEntity<ResponseDTO<Publisher>> removePublisherById(
      @PathVariable Long PublisherId) {
    Optional<Publisher> PublisherOptional = publisherService.removePublisherById(PublisherId);

    if (PublisherOptional.isEmpty()) {
      ResponseDTO<Publisher> responseDTO = new ResponseDTO<>(
          String.format("Não foi encontrada a editora de ID %d", PublisherId),
          null
      );
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
    }

    ResponseDTO<Publisher> responseDTO = new ResponseDTO<>(
        "Editora removida com sucesso!",
        null
    );
    return ResponseEntity.ok(responseDTO);
  }

  @GetMapping("/{PublisherId}")
  public ResponseEntity<ResponseDTO<Publisher>> getBookById(@PathVariable Long PublisherId) {
    Optional<Publisher> optionalPublisher = publisherService.getPublisherById(PublisherId);

    if (optionalPublisher.isEmpty()) {
      ResponseDTO<Publisher> responseDTO = new ResponseDTO<>(
          String.format("Não foi encontrada a editora de ID %d", PublisherId),
          null
      );
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
    }

    ResponseDTO<Publisher> responseDTO = new ResponseDTO<>("Editora encontrada com sucesso!",
        optionalPublisher.get());
    return ResponseEntity.ok(responseDTO);
  }

  @GetMapping
  public List<PublisherDTO> getAllPublishers() {
    List<Publisher> allPublishers = publisherService.getAllPublishers();
    return allPublishers.stream()
        .map(publisher -> new PublisherDTO(
            publisher.getId(),
            publisher.getName(),
            publisher.getAddress()))
        .collect(Collectors.toList());
  }

}
