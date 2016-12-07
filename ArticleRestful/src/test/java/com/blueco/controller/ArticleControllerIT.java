package com.blueco.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.blueco.config.ArticleRestfulApplication;
import com.blueco.model.Article;
import com.blueco.model.ArticleSearchResult;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=ArticleRestfulApplication.class)


public class ArticleControllerIT {

	private RestTemplate restTemplate;
	private String url;

	@Before
	public void setup() throws Exception {
		restTemplate = new RestTemplate();
		url = "http://localhost:8080/";
	}

	@Test
	public void getArticle() {
		Article article = new Article();
		article.setId(13L);
		article.setDate(LocalDate.of(2016, 2, 2));
		article.setTitle("title13");
		article.setBody("body with tags <html>");
		article.getTags().add("tag1");
		article.getTags().add("tag2");
		article.getTags().add("tag3");
		article.getTags().add("tag4");
		article.getTags().add("tag5");
		article.getTags().add("tag6");
		article.getTags().add("tag7");
		article.getTags().add("tag8");
		article.getTags().add("tag9");
		article.getTags().add("tag10");
		article.getTags().add("tag11");
		article.getTags().add("tag12");
		this.restTemplate.postForEntity(url + "articles", article, Article.class);
		
		article = new Article();
		article.setId(14L);
		article.setDate(LocalDate.of(2016, 2, 2));
		article.setTitle("title14");
		article.setBody("body");
		article.getTags().add("tag1");
		this.restTemplate.postForEntity(url+ "articles", article, Article.class);

		article = this.restTemplate.getForObject(url+ "articles/13", Article.class);
		assertThat(article).extracting("title").contains("title13");
		assertThat(article).extracting("body").contains("body with tags <html>");

		ArticleSearchResult result = this.restTemplate.getForObject(url + "tags/tag1/20160202", ArticleSearchResult.class);
		assertThat(result).extracting("tag").contains("tag1");
		assertThat(result).extracting("count").contains(2);
		assertThat(result.getArticles()).containsExactly(13L,14L);
		assertThat(result.getRelatedTags()).containsExactly("tag3","tag4","tag5","tag6","tag7","tag8","tag9","tag10","tag11","tag12");
	}

}