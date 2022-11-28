package com.redhat.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.camel.component.jpa.Consumed;

import javax.inject.Named;
import javax.persistence.*;

@Entity
@Table(name = "tb_pedido_outbox", schema = "public")
@NamedQueries({
  @NamedQuery(name = "getPedidosOutbox", query = "SELECT pedido FROM PedidoOutbox pedido WHERE pedido.processado = false", lockMode = LockModeType.PESSIMISTIC_WRITE)
})
@Named("pedidosOutbox")
public class PedidoOutbox {

  @Id
  @GeneratedValue
  @JsonIgnore
  private Long id;

  private Long pedidoId;

  @JsonIgnore
  private Boolean processado;

  public PedidoOutbox() {
  }

  public PedidoOutbox(Long pedidoId, Boolean processado) {
    this.pedidoId = pedidoId;
    this.processado = processado;
  }

  @Consumed
  public void afterConsumed() {
    this.processado = Boolean.TRUE;
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
