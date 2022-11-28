package com.redhat.demo.routes;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;

public abstract class AbstractRestRouteBuilder extends RouteBuilder {

  @Override
  public void configure() throws Exception {
    restConfiguration()
      .bindingMode(RestBindingMode.json)
      .apiProperty("api.title", "API Pizza")
      .apiProperty("api.version", "1.0.0")
      .apiProperty("api.description", "API para solicitar pedidos de pizza")
      .enableCORS(true)
      .corsHeaderProperty("Access-Control-Allow-Origin", "*")
      .corsHeaderProperty("Access-Control-Allow-Credentials", "*")
      .corsHeaderProperty("Access-Control-Allow-Methods", "GET, HEAD, OPTIONS, POST, PUT, DELETE")
      .corsHeaderProperty("Access-Control-Request-Headers", "Origin, Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers, api_key, authorization, Referer, User-Agent")
      .corsHeaderProperty("Access-Control-Allow-Headers", "Origin, Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers, authorization, Referer, User-Agent");
    doConfigure();
  }

  public abstract void doConfigure() throws Exception;

}
