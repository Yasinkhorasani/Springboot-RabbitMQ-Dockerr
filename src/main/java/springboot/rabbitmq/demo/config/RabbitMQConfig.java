package springboot.rabbitmq.demo.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.queue.name}")
    private String queue;
    @Value("${rabbitmq.exchange.name}")
    private String exchange;
    @Value("${rabbitmq.routing.key.name}")
    private String routing_key;

    // spring bean for rabbitMQ queue
    @Bean
    public Queue queue(){
        return new Queue(queue);
    }

    //spring bean for rabbitmq exchange
    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(exchange);
    }

    // binding between queue und exchange using routingKey
    @Bean
    public Binding binding(){
        return BindingBuilder.
                bind(queue())
                .to(exchange())
                .with(routing_key);
    }

    // ConnectionFactory
    //RabbitTemplate
    //RabbitAdmin
}
