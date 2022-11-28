package com.redhat.demo.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.kafka.KafkaConstants;
import org.apache.kafka.common.header.internals.RecordHeader;

import javax.enterprise.context.ApplicationScoped;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Optional;

@ApplicationScoped
public class PrepareFailureProcessor implements Processor {

  @Override
  public void process(Exchange exchange) throws Exception {
    ArrayList<RecordHeader> headers = exchange.getIn().getHeader(KafkaConstants.HEADERS, ArrayList.class);
    int kafkaRetryCount = 0;
    Optional<RecordHeader> headerOpt = headers.stream().filter(recordHeader -> recordHeader.key().equals("KAFKA_RETRY_COUNT")).findFirst();
    if (headerOpt.isPresent()) {
      kafkaRetryCount = new BigInteger(headerOpt.get().value()).intValue();
    }
    exchange.getIn().setHeader("KAFKA_RETRY_COUNT", ++kafkaRetryCount);
    exchange.getIn().removeHeader(KafkaConstants.HEADERS);
    exchange.getIn().removeHeader(KafkaConstants.OFFSET);
    exchange.getIn().removeHeader(KafkaConstants.PARTITION);
    exchange.getIn().removeHeader(KafkaConstants.TIMESTAMP);
    exchange.getIn().removeHeader(KafkaConstants.TOPIC);
  }
}
