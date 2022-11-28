package com.redhat.demo.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "tb_pedido", schema = "public")
public class Pedido extends PanacheEntity {

  private String pizza;

  private String cliente;

  private Double valor;

  private String cardNumber;

  private String dueDate;

  private Integer cvv;

  @ElementCollection(targetClass = String.class)
  private List<String> adicionais;

  public Pedido() {
  }

  public Pedido(String pizza, String cliente, Double valor, String cardNumber, String dueDate, Integer cvv, List<String> adicionais) {
    this.pizza = pizza;
    this.cliente = cliente;
    this.valor = valor;
    this.cardNumber = cardNumber;
    this.dueDate = dueDate;
    this.cvv = cvv;
    this.adicionais = adicionais;
  }

  public String getPizza() {
    return pizza;
  }

  public void setPizza(String pizza) {
    this.pizza = pizza;
  }

  public String getCliente() {
    return cliente;
  }

  public void setCliente(String cliente) {
    this.cliente = cliente;
  }

  public Double getValor() {
    return valor;
  }

  public void setValor(Double valor) {
    this.valor = valor;
  }

  public String getCardNumber() {
    return cardNumber;
  }

  public void setCardNumber(String cardNumber) {
    this.cardNumber = cardNumber;
  }

  public String getDueDate() {
    return dueDate;
  }

  public void setDueDate(String dueDate) {
    this.dueDate = dueDate;
  }

  public Integer getCvv() {
    return cvv;
  }

  public void setCvv(Integer cvv) {
    this.cvv = cvv;
  }

  public List<String> getAdicionais() {
    return adicionais;
  }

  public void setAdicionais(List<String> adicionais) {
    this.adicionais = adicionais;
  }
}
