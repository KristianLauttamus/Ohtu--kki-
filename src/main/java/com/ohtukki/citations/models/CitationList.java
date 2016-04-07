package com.ohtukki.citations.models;

import java.util.List;

public class CitationList {
	private List<Citation> references;

	public CitationList(List<Citation> references) {
		this.references = references;
	}
	public List<Citation> getReferences() {
		return references;
	}
}
