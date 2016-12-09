package com.blueco.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blueco.model.Article;
import com.blueco.model.ArticleSearchResult;
import com.blueco.repository.ArticleRepository;

@Service
public class ArticleService {
	@Autowired
	private ArticleRepository repo;

	public Article createArticle(Article article) {
		Article newArticle = repo.save(article);
		return newArticle;
	}

	public Article getArticle(Long id) {
		Article newArticle = repo.findById(id);
		return newArticle;
	}

	public ArticleSearchResult findArticle(String tag, LocalDate date) {
		List<Article> articleList = repo
				.findByTagAndDateOrderByIdAsc(tag, date);
		ArticleSearchResult articleSearchResult = new ArticleSearchResult();
		articleSearchResult.setTag(tag);
		articleSearchResult.setCount(articleList.size());
		LinkedHashSet<String> set = new LinkedHashSet<String>();
		for (Article article : articleList) {
			articleSearchResult.getArticles().add(article.getId());
			set.addAll(article.getTags());
		}
		// Remove main tag
		set.remove(tag);
		// Reduce to latest/last 10
		List<String> list = new ArrayList<String>(set);
		int index = set.size() <= 10 ? 0 : set.size() - 10;
		list = list.subList(index, set.size());
		articleSearchResult.getRelatedTags().addAll(list);

		return articleSearchResult;
	}
}
