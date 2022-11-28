package com.redhat.demo.routes;

import com.redhat.demo.model.PaymentDataResponse;
import com.redhat.demo.model.PedidoOutbox;
import com.redhat.demo.processor.PedidoProcessor;
import com.redhat.demo.processor.PrepareFailureProcessor;
import com.redhat.demo.processor.ValidatePaymentProcessor;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.vertx.core.http.HttpMethod;
import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class HandlePedidoKafka extends RouteBuilder {

  private final PedidoProcessor pedidoProcessor;

  private final ValidatePaymentProcessor validatePaymentProcessor;

  private final PrepareFailureProcessor prepareFailureProcessor;

  @ConfigProperty(name = "app.operadora-cartao.url")
  private String apiURL;

  public HandlePedidoKafka(PedidoProcessor pedidoProcessor, ValidatePaymentProcessor validatePaymentProcessor, PrepareFailureProcessor prepareFailureProcessor) {
    this.pedidoProcessor = pedidoProcessor;
    this.validatePaymentProcessor = validatePaymentProcessor;
    this.prepareFailureProcessor = prepareFailureProcessor;
  }

  @Override
  public void configure() throws Exception {
    JacksonDataFormat dataFormat = new JacksonDataFormat(PedidoOutbox.class);
    dataFormat.setAutoDiscoverObjectMapper(true);

    errorHandler(deadLetterChannel("direct:queue-dead-letter")
      .logStackTrace(false)
      .logExhaustedMessageHistory(false)
      .maximumRedeliveries(3)
      .redeliveryDelay(3000)
      .useOriginalMessage()
      .onPrepareFailure(prepareFailureProcessor)
      .retryAttemptedLogLevel(LoggingLevel.WARN));

    from("direct:queue-dead-letter")
      .choice()
        .when(header("KAFKA_RETRY_COUNT").isLessThanOrEqualTo(2))
          .to("kafka:{{kafka.topic.name}}")
          .log(LoggingLevel.INFO, "Pedido Outbox: ${body} reposted to kafka")
      .endChoice();

    from("kafka:{{kafka.topic.name}}?" +
      "groupId={{kafka.topic.group.id}}" +
      "&consumerRequestTimeoutMs={{kafka.topic.consumer-request-timeout}}" +
      "&maxPollIntervalMs={{kafka.topic.max-poll-interval}}" +
      "&maxPollRecords={{kafka.topic.max-poll-records}}" +
      "&consumersCount={{kafka.topic.consumer-count}}")
      .routeId("consumePedidoOutbox")
      .unmarshal(dataFormat)
      .log(LoggingLevel.INFO, "\n#######################################################\n" +
        " ### Pedido Recebido :: ${body} :: ###\n" +
        "####################################################### \n")
      .to("direct:validate-payment");

    from("direct:validate-payment")
      .routeId("validatePayment")
      .errorHandler(noErrorHandler())
      .log(LoggingLevel.INFO, "Requesting Dados Basicos for Processo ${body}")
      .process(validatePaymentProcessor)
      .marshal(dataFormat)
      .setHeader(Exchange.HTTP_METHOD, constant(HttpMethod.POST))
      .setHeader(Exchange.CONTENT_TYPE, constant(HttpHeaderValues.APPLICATION_JSON))
      .to(apiURL)
      .to("direct:process-payment-response");

    from("direct:process-payment-response")
      .routeId("processPedido")
      .errorHandler(noErrorHandler())
      .unmarshal(new JacksonDataFormat(PaymentDataResponse.class))
      .process(pedidoProcessor);
  }
}
