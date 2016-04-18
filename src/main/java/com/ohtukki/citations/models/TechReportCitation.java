package com.ohtukki.citations.models;

import com.ohtukki.citations.domain.AlphabetConverter;

/**
 * Class contains methods for book citations
 */
public class TechReportCitation extends Citation {
    public String[] requiredFields = new String[] {"id", "author", "title", "institution", "year"};
    public String[] optionalFields = new String[] {"type", "number", "address", "month", "note", "key"};
}
