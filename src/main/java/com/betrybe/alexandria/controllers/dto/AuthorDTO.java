package com.betrybe.alexandria.controllers.dto;

import com.betrybe.alexandria.models.entities.Author;

public record AuthorDTO(Long id, String name, String genre) {

  public Author toAuthor(AuthorDTO authorDTO) {
    return new Author(id, name, genre);
  }
}
