package com.betrybe.alexandria.controllers.dto;

import com.betrybe.alexandria.models.entities.Publisher;

public record PublisherDTO(Long id, String name, String address) {

  public Publisher toPublisher(PublisherDTO publisher) {
    return new Publisher(id, name, address);
  }
}
