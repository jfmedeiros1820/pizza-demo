package com.redhat.demo.model;

import java.util.List;

public class CreatedPedidoDTO {

  private String pizza;

  private String cliente;

  private Double valor;

  private List<String> adicionais;

  public CreatedPedidoDTO() {
  }

  public CreatedPedidoDTO(String pizza, String cliente, Double valor, List<String> adicionais) {
    this.pizza = pizza;
    this.cliente = cliente;
    this.valor = valor;
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

  public List<String> getAdicionais() {
    return adicionais;
  }

  public void setAdicionais(List<String> adicionais) {
    this.adicionais = adicionais;
  }
}
