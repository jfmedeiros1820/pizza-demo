package com.redhat.demo.model;

public class PaymentDataResponse {

  private Long pedidoId;

  private Boolean success;

  public PaymentDataResponse() {
  }

  public PaymentDataResponse(Long pedidoId, Boolean success) {
    this.pedidoId = pedidoId;
    this.success = success;
  }

  public Long getPedidoId() {
    return pedidoId;
  }

  public void setPedidoId(Long pedidoId) {
    this.pedidoId = pedidoId;
  }

  public Boolean isSuccess() {
    return success;
  }

  public void setSuccess(Boolean success) {
    this.success = success;
  }
}
