package com.localarticle.service.localarticlemanager;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.localarticle.service.localarticlemanager.domain.port.LocalArticleRepository;
import com.localarticle.service.localarticlemanager.domain.port.Notificator;
import com.localarticle.service.localarticlemanager.domain.port.OnlineArticleRepository;
import com.localarticle.service.localarticlemanager.domain.service.LocalArticleService;

@SpringBootApplication
public class LocalArticleManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(LocalArticleManagerApplication.class, args);
	}

	@Bean
	public LocalArticleService localArticleService(LocalArticleRepository localArticleRepository,
			OnlineArticleRepository onlineArticleRepository, Notificator notificator) {
		return new LocalArticleService(localArticleRepository, onlineArticleRepository, notificator);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
