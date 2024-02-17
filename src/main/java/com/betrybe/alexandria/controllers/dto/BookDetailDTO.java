package com.betrybe.alexandria.controllers.dto;

import com.betrybe.alexandria.models.entities.BookDetail;
import com.fasterxml.jackson.annotation.JsonProperty;

public record BookDetailDTO(
    Long id,
    String summary,
    @JsonProperty("page_count") // transforma um atributo snake_case para o camelCase
    Integer pageCount,
    String year,
    String isbn) {

  public BookDetail toBookDetail() {
    return new BookDetail(id, summary, pageCount, year, isbn);
  }
}
