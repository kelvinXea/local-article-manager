package com.localarticle.service.localarticlemanager.infrastructure.mongo.repository.document;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.localarticle.service.localarticlemanager.domain.model.enums.Market;
import com.localarticle.service.localarticlemanager.domain.model.enums.Shipping;

@Document(collection = "LocalArticles")
public class LocalArticleDocument {
	
	@Id
	private String id;
	@DBRef
	private List<UserDocument> users;
	private Long price;
	private String currency;
	private String title;
	private String imageUrl;
	private Integer quantity;
	private Shipping shipping;
	private String address;
	private Float rating;
	private String originalURL;
	private Market market;
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<UserDocument> getUsers() {
		return users;
	}
	public void setUsers(List<UserDocument> users) {
		this.users = users;
	}
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Shipping getShipping() {
		return shipping;
	}
	public void setShipping(Shipping shipping) {
		this.shipping = shipping;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Float getRating() {
		return rating;
	}
	public void setRating(Float rating) {
		this.rating = rating;
	}
	public String getOriginalURL() {
		return originalURL;
	}
	public void setOriginalURL(String originalURL) {
		this.originalURL = originalURL;
	}
	public Market getMarket() {
		return market;
	}
	public void setMarket(Market market) {
		this.market = market;
	}

}
