package com.vishruth.SpringBoot_MongoDB.repository;

import com.vishruth.SpringBoot_MongoDB.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<Book, String> {

}
