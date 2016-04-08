package com.ohtukki.citations.data;

import java.lang.reflect.Type;

import com.google.gson.InstanceCreator;
import com.ohtukki.citations.models.ArticleCitation;
import com.ohtukki.citations.models.Citation;
import com.ohtukki.citations.test.TestCitation;

public class CitationCreator implements InstanceCreator<Citation> {

	@Override
	public Citation createInstance(Type arg0) {
		return new ArticleCitation();
	}
}
