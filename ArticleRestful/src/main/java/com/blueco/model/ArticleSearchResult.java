package com.blueco.model;

import java.util.ArrayList;
import java.util.List;

public class ArticleSearchResult {
	private String tag;
	private int count;
	private List<Long> articles = new ArrayList<Long>();
	private List<String> relatedTags = new ArrayList<String>();

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<Long> getArticles() {
		return articles;
	}

	public List<String> getRelatedTags() {
		return relatedTags;
	}

}
