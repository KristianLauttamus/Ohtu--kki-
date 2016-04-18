package com.ohtukki.citations.models;

import com.ohtukki.citations.domain.AlphabetConverter;

/**
 * Class contains methods for book citations
 */
public class InbookCitation extends Citation {
    public String[] requiredFields = new String[] {"id", "author", "editor", "title", "chapter", "pages", "publisher", "year"};
    public String[] optionalFields = new String[] {"volume", "series", "address", "edition", "month", "note", "key"};
}
