package com.redhat.demo.model;

public class PaymentData {

  private Long pedidoId;

  private String cardNumber;

  private String dueDate;

  private Integer cvv;

  public PaymentData() {
  }

  public PaymentData(Long pedidoId, String cardNumber, String dueDate, Integer cvv) {
    this.pedidoId = pedidoId;
    this.cardNumber = cardNumber;
    this.dueDate = dueDate;
    this.cvv = cvv;
  }

  public Long getPedidoId() {
    return pedidoId;
  }

  public void setPedidoId(Long pedidoId) {
    this.pedidoId = pedidoId;
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
}
