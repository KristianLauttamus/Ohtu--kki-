package com.ohtukki.citations.data;

import com.google.gson.JsonElement;

import com.ohtukki.citations.models.*;
import io.gsonfire.TypeSelector;

public class CitationCreator implements TypeSelector<Citation> {
	@Override
	public Class<? extends Citation> getClassForElement(JsonElement readElement) {
		String citationType = readElement.getAsJsonObject().get("citationType").getAsString();
		switch (citationType) {
			case "Article":
				return ArticleCitation.class;
			case "Book":
				return BookCitation.class;
			case "Booklet":
				return BookletCitation.class;
			case "Conference":
				return ConferenceCitation.class;
			case "Inbook":
				return InbookCitation.class;
			case "Incollection":
				return IncollectionCitation.class;
			case "Inproceedings":
				return InproceedingsCitation.class;
			case "Manual":
				return ManualCitation.class;
			case "Mastersthesis":
				return MastersthesisCitation.class;
			case "Misc":
				return MiscCitation.class;
			case "PHDThesis":
				return PHDThesisCitation.class;
			case "Proceedings":
				return ProceedingsCitation.class;
			case "TechReport":
				return TechReportCitation.class;
			case "Unpublished":
				return UnpublishedCitation.class;
			default:
				return Citation.class;
		}
	}
}
