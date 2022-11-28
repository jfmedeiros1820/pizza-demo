package com.redhat.demo.routes;

import com.redhat.demo.model.PedidoOutbox;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;

public class ConsultarPedidosOutboxRoute extends RouteBuilder {

  @Override
  public void configure() throws Exception {
    JacksonDataFormat dataFormat = new JacksonDataFormat(PedidoOutbox.class);
    dataFormat.setAutoDiscoverObjectMapper(true);

    from("jpa:com.redhat.demo.model.PedidoOutbox?entityType=com.redhat.demo.model.PedidoOutbox&namedQuery=getPedidosOutbox&consumeDelete=false&delay={{jpa.delay.value}}")
      .routeId("pullPedidosOutbox")
      .log(LoggingLevel.INFO, "Pedidos outbox gettered from datasource")
      .split(body()).streaming()
      .marshal(dataFormat)
      .log(LoggingLevel.INFO, "Sending Pedido Outbox: ${body} to kafka")
      .to("kafka:{{kafka.topic.pedidos-outbox.name}}")
      .log(LoggingLevel.INFO, "Pedido Outbox: ${body} sent to kafka");
  }
}
