package com.redhat.demo.routes;

import com.redhat.demo.processor.CreatePedidoProcessor;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class PedidoService extends RouteBuilder {

  @Inject
  private CreatePedidoProcessor createPedidoProcessor;

  @Override
  public void configure() throws Exception {
    from("direct:create-pedido")
      .routeId("route-create-pedido")
      .log("Creating a new pedido: ${body}")
      .process(createPedidoProcessor)
      .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(200))
      .log("Pedido created Successfully: ${body}");
  }
}
