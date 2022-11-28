package com.redhat.demo.model;

public class PedidoOutbox {

  private Long pedidoId;

  public PedidoOutbox() {
  }

  public PedidoOutbox(Long pedidoId) {
    this.pedidoId = pedidoId;
  }

  public Long getPedidoId() {
    return pedidoId;
  }

  public void setPedidoId(Long pedidoId) {
    this.pedidoId = pedidoId;
  }

  @Override
  public String toString() {
    return "PedidoOutbox{" +
      "pedidoId=" + pedidoId +
      '}';
  }
}
