package com.example.vue.test.jaeger;

import io.jaegertracing.Configuration;
import io.jaegertracing.internal.JaegerSpan;
import io.jaegertracing.internal.JaegerSpanContext;
import io.jaegertracing.internal.JaegerTracer;
import io.jaegertracing.internal.samplers.ConstSampler;
import io.opentracing.References;
import io.opentracing.contrib.spring.web.client.HttpHeadersCarrier;
import io.opentracing.propagation.Format;
import io.opentracing.propagation.TextMapAdapter;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @author: Tengfei Wang
 * @description:
 * @date: Created in 15:52 2019-06-21
 * @modified by:
 */
public class JaegerTest {


    public static void main(String[] args) {
        JaegerTracer tracer = getJaegerTracer();

        JaegerTracer.SpanBuilder test = tracer.buildSpan("test");
        JaegerSpan span = test.start();
        span.setTag("a", "this is test");


        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("test","666");
        httpHeaders.add("uberctx-test","bg-test");
        tracer.inject(span.context(), Format.Builtin.HTTP_HEADERS, new HttpHeadersCarrier(httpHeaders));
        span.finish();

        RestTemplate template = new RestTemplate();
//        template.setInterceptors(Collections.singletonList(new TracingRestTemplateInterceptor(tracer)));

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(httpHeaders);
        ResponseEntity<Map> exchange = template.exchange("http://localhost:8089/tracer", HttpMethod.GET, entity, Map.class);
        Map<String, String> body = exchange.getBody();

        JaegerSpanContext spanContext = tracer.extract(Format.Builtin.TEXT_MAP, new TextMapAdapter(body));

        JaegerSpan span3 = tracer.buildSpan("span3").addReference(References.FOLLOWS_FROM, spanContext).start();
//        JaegerSpan span3 = tracer.buildSpan("span3").addReference(References.CHILD_OF, span.context()).start();
        span3.setTag("test", "this is span3");
        span3.finish();

        tracer.close();
    }

    private static JaegerTracer getJaegerTracer() {
        Configuration configuration = new Configuration("myServiceName");

        Configuration.SenderConfiguration senderConfiguration = new Configuration.SenderConfiguration();
        senderConfiguration.withAgentHost("localhost").withAgentPort(6831);

        Configuration.ReporterConfiguration reporterConfiguration = new Configuration.ReporterConfiguration();
        reporterConfiguration.withSender(senderConfiguration).withLogSpans(true);
        configuration.withReporter(reporterConfiguration);

        Configuration.SamplerConfiguration samplerConfiguration = new Configuration.SamplerConfiguration();
        samplerConfiguration.withType(ConstSampler.TYPE).withParam(1);
        configuration.withSampler(samplerConfiguration);


        return configuration.getTracer();
    }

}
