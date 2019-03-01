package com.localarticle.service.localarticlemanager.domain.model;

import java.util.List;

import com.localarticle.service.localarticlemanager.domain.model.enums.Market;

public class Message {

	private static final String FIRST = "El articulo \"%s\" se ha actualizado \n";
	private static final String PRICE_UP = "El precio del articulo subio de %d a %d \n";
	private static final String PRICE_DOWN = "El precio del articulo bajo de %d a %d \n";
	private static final String SHIPPING_CHANGE = "El tipo de envio del articulo cambio de %s a %s \n";

	private String content;
	private String idArticle;
	private Market market;
	private List<String> users;

	private StringBuilder sb;
	private LocalArticle oldLA;
	private LocalArticle newLA;

	public Message(LocalArticle oldLA, LocalArticle newLA, List<String> users) {
	    this.sb = new StringBuilder();
		this.users = users;
		this.oldLA = oldLA;
		this.newLA = newLA;
		this.buildMessage();
	}

	public String getContent() {
		return content;
	}

	public String getIdArticle() {
		return idArticle;
	}

	public Market getMarket() {
		return market;
	}

	public List<String> getUsers() {
		return users;
	}

	private void buildMessage() {
		this.firtMessage();
		this.validateAndAddPriceMessaje();
		this.validateAndAddShippingMessaje();

		content = sb.toString();

	}
	
	private void firtMessage() {
		sb.append(String.format(FIRST, oldLA.getTitle()));
	}

	private void validateAndAddPriceMessaje() {

		if (oldLA.getPrice() < newLA.getPrice()) {
			sb.append(String.format(PRICE_UP, oldLA.getPrice(), newLA.getPrice()));
		} else if (oldLA.getPrice() > newLA.getPrice()) {
			sb.append(String.format(PRICE_DOWN, oldLA.getPrice(), newLA.getPrice()));
		}

	}

	private void validateAndAddShippingMessaje() {

		if (!oldLA.getShipping().equals(newLA.getShipping())) {
			sb.append(String.format(SHIPPING_CHANGE, oldLA.getShipping().toString(), newLA.getShipping().toString()));
		}

	}

}
