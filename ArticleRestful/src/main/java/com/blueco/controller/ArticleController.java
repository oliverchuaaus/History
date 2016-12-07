package com.blueco.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.blueco.model.Article;
import com.blueco.model.ArticleSearchResult;
import com.blueco.service.ArticleService;

@RestController
public class ArticleController {
	@Autowired
	private ArticleService service;

	@RequestMapping(value = "/articles", method = RequestMethod.POST)
	public Article createArticle(@RequestBody() Article article) {
		article = service.createArticle(article);
		return article;
	}

	@RequestMapping(value = "/articles/{id}", method = RequestMethod.GET)
	public Article getArticle(@PathVariable Long id) {
		return service.getArticle(id);
	}
	
	@RequestMapping(value = "/tags/{tag}/{date}", method = RequestMethod.GET)
	public ArticleSearchResult getArticle(@PathVariable String tag, @PathVariable @DateTimeFormat(pattern="yyyyMMdd") LocalDate date) {
		return service.findArticle(tag, date);
	}
}
