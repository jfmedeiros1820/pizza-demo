package com.redhat.demo.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "tb_pizza", schema = "public")
public class Pizza extends PanacheEntity {

  private String nome;

  @ElementCollection(targetClass = String.class)
  private List<String> ingredientes;

  public Pizza() {
  }

  public Pizza(String nome, List<String> ingredientes) {
    this.nome = nome;
    this.ingredientes = ingredientes;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public List<String> getIngredientes() {
    return ingredientes;
  }

  public void setIngredientes(List<String> ingredientes) {
    this.ingredientes = ingredientes;
  }
}
