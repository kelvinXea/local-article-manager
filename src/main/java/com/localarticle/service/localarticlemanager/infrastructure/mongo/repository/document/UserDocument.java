package com.localarticle.service.localarticlemanager.infrastructure.mongo.repository.document;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Users")
public class UserDocument {

	@Id
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;

	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof UserDocument)
			return (this.id.equals(((UserDocument) obj).getId()));
		else
			return false;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 31).append(id).toHashCode();
	}

	@Override
	public String toString() {
		return this.id;
	}

}
