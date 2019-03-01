package com.localarticle.service.localarticlemanager.domain.port;

import java.util.List;

import com.localarticle.service.localarticlemanager.domain.model.Message;


public interface Notificator {

	public void sendMessages(List<Message> messages);

}
