package com.localarticle.service.localarticlemanager.infrastructure.mongo.repository;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.localarticle.service.localarticlemanager.domain.model.LocalArticle;
import com.localarticle.service.localarticlemanager.domain.port.LocalArticleRepository;
import com.localarticle.service.localarticlemanager.infrastructure.mongo.repository.document.LocalArticleDocument;
import com.localarticle.service.localarticlemanager.infrastructure.mongo.repository.document.UserDocument;
import com.localarticle.service.localarticlemanager.infrastructure.mongo.repository.localarticle.LocalArticleMongoRepository;
import com.localarticle.service.localarticlemanager.infrastructure.mongo.repository.user.UserMongoRepository;

@Component
public class LocalArticleRepositoryAdapter implements LocalArticleRepository {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private LocalArticleMongoRepository localArticleMongoRepository;

	@Autowired
	private UserMongoRepository userMongoRepository;

	@Override
	public LocalArticle saveLocalArticle(LocalArticle localArticle) {

		UserDocument user;

		if (userMongoRepository.existsById(localArticle.getIdUser())) {
			user = userMongoRepository.findById(localArticle.getIdUser()).get();
		} else {
			user = new UserDocument();
			user.setId(localArticle.getIdUser().toLowerCase());
			user = userMongoRepository.save(user);
		}

		LocalArticleDocument lad = modelMapper.map(localArticle, LocalArticleDocument.class);
		List<UserDocument> users = new ArrayList<>();
		users.add(user);
		lad.setUsers(users);
		LocalArticle la = modelMapper.map(localArticleMongoRepository.save(lad), LocalArticle.class);
		la.setIdUser(localArticle.getIdUser());
		return la;

	}

	@Override
	public List<LocalArticle> getLocalArticlesByIdUser(String idUser) {
		Type listType = new TypeToken<List<LocalArticle>>() {
		}.getType();
		UserDocument user = new UserDocument();
		user.setId(idUser);

		return modelMapper.map(localArticleMongoRepository.findByUsers(user), listType);

	}

	@Override
	public Optional<LocalArticle> getLocalArticleById(String id) {
		Optional<LocalArticleDocument> localArticleDocument = localArticleMongoRepository.findById(id);

		return localArticleDocument.isPresent()
				? Optional.of(modelMapper.map(localArticleDocument.get(), LocalArticle.class))
				: Optional.empty();
	}

	@Override
	public LocalArticle updateLocalArticle(LocalArticle localArticle) {
		Optional<LocalArticleDocument> localArticleDocument = localArticleMongoRepository
				.findById(localArticle.getId());
		LocalArticleDocument lad = modelMapper.map(localArticle, LocalArticleDocument.class);
		LocalArticle lar;
		if (localArticleDocument.isPresent()) {

			UserDocument ud = new UserDocument();
			ud.setId(localArticle.getIdUser().toLowerCase());

			if (!localArticleDocument.get().getUsers().contains(ud)) {
				localArticleDocument.get().getUsers().add(userMongoRepository.save(ud));
			}

			lad.setUsers(localArticleDocument.get().getUsers());

		}

		lar = modelMapper.map(localArticleMongoRepository.save(lad), LocalArticle.class);
		lar.setIdUser(localArticle.getIdUser());

		return lar;

	}

	@Override
	public List<String> getAllIdUsersByLocalArticleId(String idLocalArticle) {
		Optional<LocalArticleDocument> localArticleDocument = localArticleMongoRepository.findById(idLocalArticle);
		return localArticleDocument.isPresent()
				? localArticleDocument.get().getUsers().stream().map(UserDocument::getId).collect(Collectors.toList())
				: new ArrayList<>();
	}

	@Override
	public List<LocalArticle> searchLocalArticleByTitleLike(String query, String idUser) {
		UserDocument user = userMongoRepository.findById(idUser)
				//TODO
				.orElseThrow(() -> new RuntimeException("cambiar esto por una custom exception"));
		
		List<LocalArticleDocument> lista = localArticleMongoRepository.findByTitleIgnoreCaseContainingAndUsers(query, user);
		Type listType = new TypeToken<List<LocalArticle>>() {
		}.getType();

		return modelMapper.map(lista, listType);
	}

	@Override
	public List<LocalArticle> getAllLocalArticles() {
		Type listType = new TypeToken<List<LocalArticle>>() {
		}.getType();
		return modelMapper.map(localArticleMongoRepository.findAll(), listType);
		
	}

}
