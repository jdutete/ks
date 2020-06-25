package com.jdutete.ks.springcamel.route;

import com.jdutete.ks.springcamel.AddUserQuartzConfig;
import com.jdutete.ks.springcamel.AddUserTimerConfig;
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

    private final AddUserQuartzConfig addUserQuartzConfig;
    private final AddUserTimerConfig addUserTimerConfig;

    public MyRoute(AddUserQuartzConfig addUserQuartzConfig, AddUserTimerConfig addUserTimerConfig) {
        this.addUserQuartzConfig = addUserQuartzConfig;
        this.addUserTimerConfig = addUserTimerConfig;
    }

    @Override
    public void configure() {
//        CamelContext context = new DefaultCamelContext();
        from("quartz://myGroup/addUserQuartz?cron={{ks.quartz.add-user.cron}}")
                .routeId("quartz-route")
                .to("direct:addUser");
        from("timer://addUserTimer?delay={{ks.timer.add-user.delay}}&fixedRate={{ks.timer.add-user.fixed-rate}}" +
                "&period={{ks.timer.add-user.period}}")
                .routeId("timer-route")
                .to("direct:addUser");

        from("direct:addUser")
                .log(LoggingLevel.INFO, "body1 >> ${in.body}")
                .to("bean://createRequest")
                .log(LoggingLevel.INFO, "body2 >> ${in.body}")
                .setHeader(HTTP_METHOD, constant(POST))
                .setHeader(CONTENT_TYPE, constant("application/json"))
                .to("bean://headerTest")
                .marshal().json(JsonLibrary.Jackson)
                .to("{{ks.endpoints.users}}")
                .convertBodyTo(String.class)
                .log(LoggingLevel.INFO, "POST result >> ${in.body}")
                .unmarshal().json(JsonLibrary.Jackson, User.class)
                .log(LoggingLevel.INFO, "body >> ${in.body}")
                .to("bean://checkResponse");

    }
}