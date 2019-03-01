package com.localarticle.service.localarticlemanager.infrastructure.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.localarticle.service.localarticlemanager.domain.model.LocalArticle;
import com.localarticle.service.localarticlemanager.domain.service.LocalArticleService;

@RestController
public class LocalArticleController {

	@Autowired
	public LocalArticleService localArticleService;
	
	@PostMapping()
	public LocalArticle saveLocalArticle(@RequestBody LocalArticle localArticle) {
		return localArticleService.saveLocalArticle(localArticle);
	}
	
}
