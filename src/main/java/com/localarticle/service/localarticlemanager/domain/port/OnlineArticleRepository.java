package com.localarticle.service.localarticlemanager.domain.port;

import java.util.List;

import com.localarticle.service.localarticlemanager.domain.model.LocalArticle;

public interface OnlineArticleRepository {
	
	public List<LocalArticle> getListLocalArticleOfOnlineArticle(List<LocalArticle> articles);

}
