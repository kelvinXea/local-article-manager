package com.localarticle.service.localarticlemanager.infrastructure.mongo.repository.user;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.localarticle.service.localarticlemanager.infrastructure.mongo.repository.document.UserDocument;

@Repository
public interface UserMongoRepository extends MongoRepository<UserDocument,String>{

}
