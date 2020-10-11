package com.rounak.biddingsystem.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class User {

  @Id
  private Long id;
  private String name;
  private String status;

}
