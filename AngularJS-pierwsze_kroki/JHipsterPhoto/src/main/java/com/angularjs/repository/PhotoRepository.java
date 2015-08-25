package com.angularjs.repository;

import com.angularjs.domain.Photo;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the Photo entity.
 */
public interface PhotoRepository extends MongoRepository<Photo,String> {

}
