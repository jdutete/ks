package com.jdutete.ks.springcamel.route;

import com.jdutete.ks.springcamel.dto.User;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

import static org.apache.camel.Exchange.CONTENT_TYPE;
import static org.apache.camel.Exchange.HTTP_METHOD;
import static org.apache.camel.component.http.HttpMethods.POST;

@Component
public class MyRoute extends RouteBuilder {
    @Override
    public void configure() {
//        CamelContext context = new DefaultCamelContext();
        from("quartz://myGroup/myTimerName?cron=0+*+*+*+*+?")
                .routeId("myRoute")
                .log(LoggingLevel.INFO, "body1 >> ${in.body}")
                .to("bean://createRequest")
                .log(LoggingLevel.INFO, "body2 >> ${in.body}")
                .setHeader(HTTP_METHOD, constant(POST))
                .setHeader(CONTENT_TYPE, constant("application/json"))
                .to("bean://headerTest")
                .marshal().json(JsonLibrary.Jackson)
                .to("http://localhost:9999/users")
                .convertBodyTo(String.class)
                .log(LoggingLevel.INFO, "POST result >> ${in.body}")
                .unmarshal().json(JsonLibrary.Jackson, User.class)
                .log(LoggingLevel.INFO, "body >> ${in.body}")
                .to("bean://checkResponse")
        ;
    }
}