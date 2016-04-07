package com.ohtukki.citations.data;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ohtukki.citations.models.Citation;
import com.ohtukki.citations.models.CitationList;


public class DatabaseJsonDao {
	private String filename;
	private List<Citation> citations;
	
	public DatabaseJsonDao(String filename) {
		this.filename = filename;
		this.citations = new ArrayList<Citation>();
	}
	
	public void saveJson() throws Exception {
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		CitationList list = new CitationList(this.citations);
		String json = gson.toJson(list);
		FileUtils.writeStringToFile(new File(filename), json);
	}

	public void loadJson() throws Exception {
		GsonBuilder builder = new GsonBuilder();
		builder.registerTypeAdapter(Citation.class, new CitationCreator());
		Gson gson = builder.create();
		String data = FileUtils.readFileToString(new File(filename));
		CitationList list = gson.fromJson(data, CitationList.class);
		this.citations = list.getReferences();
	}

	public List<Citation> all() {
		return citations;
	}

	public List<Citation> allByPredicate(Predicate<Citation> predicate) {
		Iterable<Citation> c = Iterables.filter(citations,	predicate);
		List<Citation> targetCollection = new ArrayList<Citation>();
		for (Iterator iterator = c.iterator(); iterator.hasNext();) {
			Citation citation = (Citation) iterator.next();
			targetCollection.add(citation);
		}

		return targetCollection;
	}

	public void save(Citation ref) {
		citations.add(ref);
	}


}
