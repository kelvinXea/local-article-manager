package com.localarticle.service.localarticlemanager.infrastructure.rabbitmq.onlinearticle;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.TypeToken;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Repository;

import com.localarticle.service.localarticlemanager.domain.model.LocalArticle;
import com.localarticle.service.localarticlemanager.domain.port.OnlineArticleRepository;

@Repository
public class OnlineArticleRepositoryAdapter implements OnlineArticleRepository {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Autowired
	private DirectExchange exchange;

	@Autowired
	private MessageConverter messageConverter;

	@Override
	public List<LocalArticle> getListLocalArticleOfOnlineArticle(List<LocalArticle> articles) {
		String routingKey = "onlinearticle.request.ids";
		rabbitTemplate.setMessageConverter(messageConverter);
		ParameterizedTypeReference<List<LocalArticle>> ptr = new ParameterizedTypeReference<List<LocalArticle>>() {
		};
		
		return rabbitTemplate.convertSendAndReceiveAsType(exchange.getName(), routingKey, articles,ptr);
	}

}
