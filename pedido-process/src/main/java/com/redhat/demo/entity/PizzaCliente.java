package com.redhat.demo.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "tb_pizza_cliente", schema = "public")
public class PizzaCliente extends PanacheEntity {

  private String nome;

  private String cliente;

  @ElementCollection(targetClass = String.class)
  private List<String> ingredientes;

  public PizzaCliente() {
  }

  public PizzaCliente(String nome, String cliente, List<String> ingredientes) {
    this.nome = nome;
    this.cliente = cliente;
    this.ingredientes = ingredientes;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getCliente() {
    return cliente;
  }

  public void setCliente(String cliente) {
    this.cliente = cliente;
  }

  public List<String> getIngredientes() {
    return ingredientes;
  }

  public void setIngredientes(List<String> ingredientes) {
    this.ingredientes = ingredientes;
  }
}
