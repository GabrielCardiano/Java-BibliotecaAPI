package com.betrybe.alexandria.controllers;

import com.betrybe.alexandria.controllers.dto.BookDTO;
import com.betrybe.alexandria.controllers.dto.BookDetailDTO;
import com.betrybe.alexandria.controllers.dto.ResponseDTO;
import com.betrybe.alexandria.models.entities.Book;
import com.betrybe.alexandria.models.entities.BookDetail;
import com.betrybe.alexandria.service.BookService;
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
@RequestMapping(value = "/books")
public class BookController {

  private final BookService bookService;

  @Autowired
  public BookController(BookService bookService) {
    this.bookService = bookService;
  }

  /*
   * Métodos Book
   */
  @PostMapping()
  public ResponseEntity<ResponseDTO<Book>> createBook(@RequestBody BookDTO bookDTO) {
    Book newBook = bookService.insertBook(bookDTO.toBook());
    ResponseDTO<Book> responseDTO = new ResponseDTO<>("Livro criado com sucesso!", newBook);
    return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
  }

  @PutMapping("/{bookId}")
  public ResponseEntity<ResponseDTO<Book>> updateBook(@PathVariable Long bookId,
      @RequestBody BookDTO bookDTO) {
    Optional<Book> optionalBook = bookService.updateBook(bookId, bookDTO.toBook());

    if (optionalBook.isEmpty()) {
      ResponseDTO<Book> responseDTO = new ResponseDTO<>(
          String.format("Não foi encontrado o livro de ID %d", bookId), null);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
    }

    ResponseDTO<Book> responseDTO = new ResponseDTO<>(
        "Livro atualizado com sucesso!", optionalBook.get());
    return ResponseEntity.ok(responseDTO);
  }

  @DeleteMapping("/{bookId}")
  public ResponseEntity<ResponseDTO<Book>> removeBookById(@PathVariable Long bookId) {
    Optional<Book> optionalBook = bookService.removeBookById(bookId);

    if (optionalBook.isEmpty()) {
      ResponseDTO<Book> responseDTO = new ResponseDTO<>(
          String.format("Não foi encontrado o livro de ID %d", bookId), null);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
    }

    ResponseDTO<Book> responseDTO = new ResponseDTO<>("Livro removido com sucesso!", null);
    return ResponseEntity.ok(responseDTO);
  }

  @GetMapping("/{bookId}")
  public ResponseEntity<ResponseDTO<Book>> getBookById(@PathVariable Long bookId) {
    Optional<Book> optionalBook = bookService.getBookById(bookId);

    if (optionalBook.isEmpty()) {
      ResponseDTO<Book> responseDTO = new ResponseDTO<>(
          String.format("Não foi encontrado o livro de ID %d", bookId), null);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
    }

    ResponseDTO<Book> responseDTO = new ResponseDTO<>("Livro encontrado com sucesso!",
        optionalBook.get());
    return ResponseEntity.ok(responseDTO);
  }

  @GetMapping()
  public List<BookDTO> getAllBooks() {
    List<Book> allBooks = bookService.getAllBooks();
    return allBooks.stream()
        .map((book) -> new BookDTO(
            book.getId(),
            book.getTitle(),
            book.getGenre()))
        .collect(Collectors.toList());
  }

  /*
   * Métodos Bookdetail
   */
  @PostMapping("/{bookId}/detail")
  public ResponseEntity<ResponseDTO<BookDetail>> createBook(
      @RequestBody BookDetailDTO BookDetailDTO) {
    BookDetail newBookdetail = bookService.insertBookDetail(BookDetailDTO.toBookDetail());
    ResponseDTO<BookDetail> responseDTO = new ResponseDTO<>("Detalhe do livro criado com sucesso!",
        newBookdetail);
    return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
  }

  @PutMapping("/{bookId}/detail/{id}")
  public ResponseEntity<ResponseDTO<BookDetail>> updateBookDetail(@PathVariable Long id,
      @RequestBody BookDetailDTO bookDetailDTO) {
    Optional<BookDetail> optionalBookDetail = bookService.updateBookDetail(id,
        bookDetailDTO.toBookDetail());

    if (optionalBookDetail.isEmpty()) {
      ResponseDTO<BookDetail> responseDTO = new ResponseDTO<>(
          String.format("Não foi encontrado o detalhe do livro de ID %d", id), null);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
    }

    ResponseDTO<BookDetail> responseDTO = new ResponseDTO<>(
        "detalhe do livro atualizado com sucesso!", optionalBookDetail.get());
    return ResponseEntity.ok(responseDTO);
  }

  @DeleteMapping("/{bookId}/detail/{id}")
  public ResponseEntity<ResponseDTO<BookDetail>> removeBookDetailById(@PathVariable Long id) {
    Optional<BookDetail> optionalBookDetail = bookService.removeBookDetailById(id);

    if (optionalBookDetail.isEmpty()) {
      ResponseDTO<BookDetail> responseDTO = new ResponseDTO<>(
          String.format("Não foi encontrado o detalhe do livro de ID %d", id), null);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
    }

    ResponseDTO<BookDetail> responseDTO = new ResponseDTO<>("Livro removido com sucesso!", null);
    return ResponseEntity.ok(responseDTO);
  }

  @GetMapping("/{bookId}/detail/{id}")
  public ResponseEntity<ResponseDTO<BookDetail>> getBookDetailById(@PathVariable Long id) {
    Optional<BookDetail> optionalBookDetail = bookService.getBookDetailById(id);

    if (optionalBookDetail.isEmpty()) {
      ResponseDTO<BookDetail> responseDTO = new ResponseDTO<>(
          String.format("Não foi encontrado o detalhe do livro de ID %d", id), null);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
    }

    ResponseDTO<BookDetail> responseDTO = new ResponseDTO<>(
        "Detalhe do livro encontrado com sucesso!",
        optionalBookDetail.get());
    return ResponseEntity.ok(responseDTO);
  }
}
