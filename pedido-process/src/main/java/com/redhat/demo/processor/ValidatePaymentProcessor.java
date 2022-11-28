package com.redhat.demo.processor;

import com.redhat.demo.entity.Pedido;
import com.redhat.demo.model.PaymentData;
import com.redhat.demo.model.PedidoOutbox;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
public class ValidatePaymentProcessor implements Processor {

  @Override
  @Transactional
  public void process(Exchange exchange) throws Exception {
    PedidoOutbox pedidoOutbox = exchange.getIn().getBody(PedidoOutbox.class);
    Pedido pedido = Pedido.findById(pedidoOutbox.getPedidoId());
    PaymentData paymentData = new PaymentData(pedido.id, pedido.getCardNumber(), pedido.getDueDate(), pedido.getCvv());

    exchange.getIn().setBody(paymentData);
  }
}
