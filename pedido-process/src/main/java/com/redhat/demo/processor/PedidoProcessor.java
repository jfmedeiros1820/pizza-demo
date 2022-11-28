package com.redhat.demo.processor;

import com.redhat.demo.entity.Pedido;
import com.redhat.demo.entity.Pizza;
import com.redhat.demo.entity.PizzaCliente;
import com.redhat.demo.model.PaymentDataResponse;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class PedidoProcessor implements Processor {

  @Override
  @Transactional
  public void process(Exchange exchange) throws Exception {
    PaymentDataResponse response = exchange.getIn().getBody(PaymentDataResponse.class);
    if(response.isSuccess()) {
      Pedido pedido = Pedido.findById(response.getPedidoId());
      Optional<Pizza> pizzaOpt = Pizza.find("nome", pedido.getPizza()).firstResultOptional();
      pizzaOpt.ifPresent(pizza -> {
        List<String> ingredientes = new ArrayList<>();
        ingredientes.addAll(pizza.getIngredientes());
        ingredientes.addAll(pedido.getAdicionais());
        PizzaCliente pizzaCliente = new PizzaCliente(pedido.getPizza(), pedido.getCliente(), ingredientes);
        pizzaCliente.persist();
      });
    }
  }
}
