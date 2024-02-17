package com.betrybe.alexandria.service;

import com.betrybe.alexandria.models.entities.Author;
import com.betrybe.alexandria.models.repositores.AuthorRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

  private AuthorRepository authorRepository;

  @Autowired
  public AuthorService(AuthorRepository authorRepository) {
    this.authorRepository = authorRepository;
  }

  public Author insertAuthor(Author author) {
    return authorRepository.save(author);
  }

  public Optional<Author> updateAuthor(Author author, Long id) {
    Optional<Author> optionalAuthor = authorRepository.findById(id);

    if (optionalAuthor.isPresent()) {
      Author authorFromDb = optionalAuthor.get();
      authorFromDb.setName(author.getName());
      authorFromDb.setNationality(author.getNationality());

      Author updatedAuthor = authorRepository.save(authorFromDb);
      return Optional.of(updatedAuthor);
    }

    return optionalAuthor;
  }

  public Optional<Author> removeAuthorById(Long id) {
    Optional<Author> optionalAuthor = authorRepository.findById(id);

    if (optionalAuthor.isPresent()) {
      authorRepository.deleteById(id);
    }
    return optionalAuthor;
  }

  public Optional<Author> getAuthorById(Long id) {
    return authorRepository.findById(id);
  }

  public List<Author> getAllAuthors() {
    return authorRepository.findAll();
  }
}
