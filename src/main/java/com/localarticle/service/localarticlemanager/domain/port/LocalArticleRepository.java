package com.localarticle.service.localarticlemanager.domain.port;

import java.util.List;
import java.util.Optional;

import com.localarticle.service.localarticlemanager.domain.model.LocalArticle;

public interface LocalArticleRepository {
	
	public LocalArticle saveLocalArticle (LocalArticle localArticle);
	
	public Optional<LocalArticle> getLocalArticleById (String id);
	
	public LocalArticle updateLocalArticle (LocalArticle localArticle);
	
	public List<LocalArticle> getLocalArticlesByIdUser (String idUser);
	
	public List<LocalArticle> getAllLocalArticles ();
	
	public List<String> getAllIdUsersByLocalArticleId (String idLocalArticle);

	public List<LocalArticle> searchLocalArticleByTitleLike (String query, String idUser);
	
}
