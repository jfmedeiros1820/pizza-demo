package com.redhat.demo.routes;

import com.redhat.demo.model.PaymentData;
import com.redhat.demo.model.PaymentDataResponse;
import io.netty.handler.codec.http.HttpHeaderValues;
import org.apache.camel.model.rest.RestBindingMode;

public class OperadoraCartaoResource extends AbstractRestRouteBuilder {

  @Override
  public void doConfigure() throws Exception {
    rest("/api/payment/process")
      .post().description("Validar Pagamento.")
      .produces(HttpHeaderValues.APPLICATION_JSON.toString())
      .consumes(HttpHeaderValues.APPLICATION_JSON.toString())
      .type(PaymentData.class).outType(PaymentDataResponse.class)
      .bindingMode(RestBindingMode.json)
      .to("direct:process-payment");
  }
}
