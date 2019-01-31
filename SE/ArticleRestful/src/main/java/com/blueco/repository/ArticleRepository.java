package com.blueco.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.blueco.model.Article;

@Repository
public interface ArticleRepository extends MongoRepository<Article, String> {

	public Article findById(Long id);

	@Query("{tags: ?0, date: ?1}")
	public List<Article> findByTagAndDateOrderByIdAsc(String tag, LocalDate date);
}
