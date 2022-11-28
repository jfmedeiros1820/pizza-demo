package com.redhat.demo.model;

import java.util.List;

public class NewPedidoDTO {

  private String pizza;

  private String cliente;

  private Double valor;

  private String cardNumber;

  private String dueDate;

  private Integer cvv;

  private List<String> adicionais;

  public NewPedidoDTO() {
  }

  public NewPedidoDTO(String pizza, String cliente, Double valor, String cardNumber, String dueDate, Integer cvv, List<String> adicionais) {
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

  public Double getValor() {
    return valor;
  }

  public void setValor(Double valor) {
    this.valor = valor;
  }

  public List<String> getAdicionais() {
    return adicionais;
  }

  public void setAdicionais(List<String> adicionais) {
    this.adicionais = adicionais;
  }

  @Override
  public String toString() {
    return "NewPedidoDTO{" +
      "pizza='" + pizza + '\'' +
      ", cliente='" + cliente + '\'' +
      ", valor=" + valor +
      ", cardNumber='" + cardNumber + '\'' +
      ", dueDate='" + dueDate + '\'' +
      ", cvv=" + cvv +
      ", adicionais=" + adicionais +
      '}';
  }
}
