package com.ohtukki.citations.data;

import com.google.common.base.Predicate;
import com.ohtukki.citations.models.Citation;

public class AuthorFilter implements Predicate<Citation> {
	private String author;
	public AuthorFilter(String author) {
            this.author = author;
	}
	public boolean apply(Citation citation) {
            return citation.getAuthor().equals(author);
	}
}
