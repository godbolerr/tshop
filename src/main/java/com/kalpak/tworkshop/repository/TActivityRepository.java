package com.kalpak.tworkshop.repository;

import com.kalpak.tworkshop.domain.TActivity;
import org.springframework.stereotype.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the TActivity entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TActivityRepository extends MongoRepository<TActivity,String> {
    
}
