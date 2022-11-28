package com.redhat.demo.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tb_pedido_outbox", schema = "public")
public class PedidoOutbox extends PanacheEntity {

  private Long pedidoId;

  private Boolean processado;

  public PedidoOutbox() {
  }

  public PedidoOutbox(Long pedidoId, Boolean processado) {
    this.pedidoId = pedidoId;
    this.processado = processado;
  }

  public Long getPedidoId() {
    return pedidoId;
  }

  public void setPedidoId(Long pedidoId) {
    this.pedidoId = pedidoId;
  }

  public Boolean getProcessado() {
    return processado;
  }

  public void setProcessado(Boolean processado) {
    this.processado = processado;
  }
}
