package com.kalpak.tworkshop.repository;

import com.kalpak.tworkshop.domain.TResource;
import org.springframework.stereotype.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the TResource entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TResourceRepository extends MongoRepository<TResource,String> {
    
}
