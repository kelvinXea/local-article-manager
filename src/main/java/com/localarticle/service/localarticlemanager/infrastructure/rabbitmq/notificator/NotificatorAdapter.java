package com.localarticle.service.localarticlemanager.infrastructure.rabbitmq.notificator;

import java.util.List;

import org.springframework.stereotype.Component;

import com.localarticle.service.localarticlemanager.domain.model.Message;
import com.localarticle.service.localarticlemanager.domain.port.Notificator;

@Component
public class NotificatorAdapter implements Notificator{

	@Override
	public void sendMessages(List<Message> messages) {
		
		messages.forEach(v -> System.out.println(v.getContent()));
		
		// TODO Auto-generated method stub
		
	}

}
