package com.blueco.repository;

/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.blueco.config.ArticleRestfulApplication;
import com.blueco.model.Article;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ArticleRestfulApplication.class)
public class ArticleRepositoryTest {

	@Autowired
	ArticleRepository repo;

	Article one, two, three;

	@Before
	public void setUp() {

		repo.deleteAll();

		one = new Article();
		one.setId(1L);
		one.setDate(LocalDate.of(2016, 2, 28));
		one.setTitle("title");
		one.setBody("body");
		one.getTags().add("health");
		one.getTags().add("insurance");
		one = repo.save(one);

		two = new Article();
		two.setId(2L);
		two.setDate(LocalDate.of(2016, 2, 28));
		two.setTitle("title2");
		two.setBody("body2");
		two.getTags().add("health");
		two = repo.save(two);

		three = new Article();
		three.setId(3L);
		three.setDate(LocalDate.of(2014, 2, 1));
		three.setTitle("title3");
		three.setBody("body3");
		three.getTags().add("trade");
		three = repo.save(three);

	}

	@Test
	public void setsIdOnSave() {
		Article newArticle = new Article();
		newArticle.setId(4L);
		newArticle.setDate(LocalDate.of(2016, 2, 21));
		newArticle.setTitle("title");
		newArticle.setBody("body");
		newArticle.getTags().add("tag1");
		newArticle.getTags().add("tag2");
		newArticle = repo.save(newArticle);
		assertThat(newArticle.getId()).isNotNull();
	}

	@Test
	public void findById() {
		Article result = repo.findById(1L);
		assertThat(result).extracting("title").contains("title");
	}

	@Test
	public void findByTagAndDate() {
		List<Article> results;

		results = repo.findByTagAndDateOrderByIdAsc("trade",
				LocalDate.of(2014, 2, 1));
		assertThat(results).hasSize(1).extracting("title").contains("title3");

		results = repo.findByTagAndDateOrderByIdAsc("health",
				LocalDate.of(2016, 2, 28));
		assertThat(results).hasSize(2).extracting("title")
				.contains("title", "title2");

		results = repo.findByTagAndDateOrderByIdAsc("health",
				LocalDate.of(2016, 2, 21));
		assertThat(results).hasSize(0);

	}

}