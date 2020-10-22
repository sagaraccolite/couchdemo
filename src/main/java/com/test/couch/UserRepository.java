package com.test.couch;

import java.util.List;

import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CouchbaseRepository<User, String> {

    List<User> findByEmailLike(String firstName);
}