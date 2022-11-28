package com.redhat.demo.processor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.redhat.demo.entity.Pedido;
import com.redhat.demo.entity.PedidoOutbox;
import com.redhat.demo.entity.Pizza;
import com.redhat.demo.model.CreatedPedidoDTO;
import com.redhat.demo.model.NewPedidoDTO;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@ApplicationScoped
public class CreatePedidoProcessor implements Processor {

  private static final Logger LOGGER = LoggerFactory.getLogger(CreatePedidoProcessor.class);

  @Override
  @Transactional
  public void process(Exchange exchange) throws Exception {
    LOGGER.info("### Processing the creation af a new pedido ... ### ");
    NewPedidoDTO newPedidoDTO = exchange.getIn().getBody(NewPedidoDTO.class);
    Optional<Pizza> pizzaOpt = Pizza.find("nome", newPedidoDTO.getPizza()).firstResultOptional();
    if (isWorkingHours() && pizzaOpt.isPresent()) {
      LOGGER.info("### Pedido is valid ... ### ");
      Pedido pedido = new Pedido(newPedidoDTO.getPizza(),
        newPedidoDTO.getCliente(),
        newPedidoDTO.getValor(),
        newPedidoDTO.getCardNumber(),
        newPedidoDTO.getDueDate(),
        newPedidoDTO.getCvv(),
        newPedidoDTO.getAdicionais());
      pedido.persist();

      PedidoOutbox pedidoOutbox = new PedidoOutbox(pedido.id, Boolean.FALSE);
      pedidoOutbox.persist();

      CreatedPedidoDTO createdPedidoDTO = new CreatedPedidoDTO(pedido.getPizza(), pedido.getCliente(), pedido.getValor(), pedido.getAdicionais());
      ObjectMapper mapper = new ObjectMapper();
      String jsonInString = mapper.writeValueAsString(createdPedidoDTO);

      exchange.getIn().setBody(jsonInString);
    } else {
      throw new IllegalArgumentException("The store is closed");
    }
  }

  private boolean isWorkingHours() {
    LocalDateTime openStore = LocalDateTime.now().withHour(8).withMinute(0).withSecond(0);
    LocalDateTime closeStore = LocalDateTime.now().withHour(23).withMinute(0).withSecond(0);
    LocalDateTime now = LocalDateTime.now();
    return now.isAfter(openStore) && now.isBefore(closeStore);
  }

}
