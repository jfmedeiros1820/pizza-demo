package com.redhat.demo.routes;

import com.redhat.demo.model.PaymentData;
import com.redhat.demo.model.PaymentDataResponse;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class OperadoraCartaoService extends RouteBuilder {

  @Override
  public void configure() throws Exception {
    from("direct:process-payment")
      .routeId("route-process-payment")
      .log("Validating payment method")
      .process(exchange -> {
        PaymentData paymentData = exchange.getIn().getBody(PaymentData.class);
        PaymentDataResponse paymentDataResponse = new PaymentDataResponse(paymentData.getPedidoId(), Boolean.TRUE);
        exchange.getIn().setBody(paymentDataResponse);
      })
      .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(200))
      .log("Payment validated Successfully");
  }
}
