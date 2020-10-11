package com.rounak.biddingsystem.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@RequiredArgsConstructor
@AllArgsConstructor
public class User {

  @Id
  private Long id;
  private String name;
  private String status;

}
