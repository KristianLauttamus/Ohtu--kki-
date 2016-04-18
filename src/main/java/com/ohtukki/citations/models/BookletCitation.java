package com.ohtukki.citations.models;

import com.ohtukki.citations.domain.AlphabetConverter;

/**
 * Class contains methods for book citations
 */
public class BookletCitation extends Citation {
    public String[] requiredFields = new String[] {"id", "title"};
    public String[] optionalFields = new String[] {"author", "howpublished", "address", "month", "year", "note", "key"};
}
