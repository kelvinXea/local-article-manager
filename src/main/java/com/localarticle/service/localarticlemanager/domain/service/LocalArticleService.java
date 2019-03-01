package com.localarticle.service.localarticlemanager.domain.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.localarticle.service.localarticlemanager.domain.model.LocalArticle;
import com.localarticle.service.localarticlemanager.domain.model.Message;
import com.localarticle.service.localarticlemanager.domain.port.LocalArticleRepository;
import com.localarticle.service.localarticlemanager.domain.port.Notificator;
import com.localarticle.service.localarticlemanager.domain.port.OnlineArticleRepository;

public class LocalArticleService {

	private LocalArticleRepository localArticleRepository;

	private OnlineArticleRepository onlineArticleRepository;

	private Notificator notificator;

	public LocalArticleService(LocalArticleRepository localArticleRepository,
			OnlineArticleRepository onlineArticleRepository, Notificator notificator) {
		this.localArticleRepository = localArticleRepository;
		this.onlineArticleRepository = onlineArticleRepository;
		this.notificator = notificator;
	}

	public LocalArticle saveLocalArticle(LocalArticle localArticle) {
		if (localArticleRepository.getLocalArticleById(localArticle.getId()).isPresent()) {
			return localArticleRepository.updateLocalArticle(localArticle);
		} else {
			return localArticleRepository.saveLocalArticle(localArticle);
		}

	}

	public List<LocalArticle> getAllLocalArticlesByIdUser(String idUser) {
		return localArticleRepository.getLocalArticlesByIdUser(idUser);
	}

	public List<LocalArticle> findLocalArticleBylikeTitleAndIdUser(String query, String idUser) {
		return localArticleRepository.searchLocalArticleByTitleLike(query, idUser);
	}

	public void updateLocalArticles() {
		List<Message> messageList = new ArrayList<>();
		List<LocalArticle> oldLAs = localArticleRepository.getAllLocalArticles();
		List<LocalArticle> newLAs = onlineArticleRepository.getListLocalArticleOfOnlineArticle(oldLAs);

		oldLAs.forEach(la -> {
			Optional<LocalArticle> newLA = newLAs.stream().filter(nla -> nla.getId().equals(la.getId())).findFirst();

			if (newLA.isPresent() && la.hasDifference(newLA.get())) {
				List<String> idUsers = localArticleRepository.getAllIdUsersByLocalArticleId(newLA.get().getId());
				newLA.get().setIdUser(idUsers.get(0));
				Message m = new Message(la, newLA.get(), idUsers);
				this.localArticleRepository.updateLocalArticle(newLA.get());
				messageList.add(m);
			}

		});

		notificator.sendMessages(messageList);

	}

}
