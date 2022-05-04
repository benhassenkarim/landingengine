package com.peerlander.landingengine.application.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.peerlander.landingengine.domain.event.UserRegistredEventHandler;



@Configuration
public class MessagingConfig {

	private static final String TOPIC="userRegistredTopic";
    private static final String QUEUE_NAME="user.regitred.lending";

    @Bean
    public Queue userRegistredQueue(){
    	return new Queue(QUEUE_NAME,false);
    }

    @Bean
    public TopicExchange userRegistredTopic() {
    	return new TopicExchange(TOPIC);
    }

    @Bean 
    public Binding binding(Queue queue,TopicExchange topicExchange) {
    	return BindingBuilder.bind(queue).to(topicExchange).with("user.#");
    }
    
   @Bean
   public SimpleMessageListenerContainer container (ConnectionFactory connectionFactory, 
		                                            MessageListenerAdapter messageListenerAdapter) {
	   SimpleMessageListenerContainer container= new SimpleMessageListenerContainer();
	   container.setConnectionFactory(connectionFactory);
	   container.setQueueNames(QUEUE_NAME);
	   container.setMessageListener(messageListenerAdapter);
	   return container;
	   
   }
   @Bean
   public MessageListenerAdapter userRegistredEventListener
                              (UserRegistredEventHandler userRegistredEventHandler) {
	   return new MessageListenerAdapter(userRegistredEventHandler,"handelUserRegistration");
   }
    }
