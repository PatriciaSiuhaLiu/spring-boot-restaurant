package com.humber.restaurants.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Review {

  @Id
  //@GeneratedValue(strategy = GenerationType.UUID)
  private String review_id = UUID.randomUUID().toString();
  private String date;
  private int score;
  private String review;
}
