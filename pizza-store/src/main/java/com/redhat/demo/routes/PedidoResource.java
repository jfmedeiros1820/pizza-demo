package com.redhat.demo.routes;

import com.redhat.demo.model.CreatedPedidoDTO;
import com.redhat.demo.model.NewPedidoDTO;
import io.netty.handler.codec.http.HttpHeaderValues;
import org.apache.camel.model.rest.RestBindingMode;

public class PedidoResource extends AbstractRestRouteBuilder {

  @Override
  public void doConfigure() throws Exception {
    rest("/api/pedidos")
      .post().description("Cadastrar um novo pedido.")
      .produces(HttpHeaderValues.APPLICATION_JSON.toString())
      .consumes(HttpHeaderValues.APPLICATION_JSON.toString())
      .type(NewPedidoDTO.class).outType(CreatedPedidoDTO.class)
      .bindingMode(RestBindingMode.json)
      .to("direct:create-pedido");
  }
}
