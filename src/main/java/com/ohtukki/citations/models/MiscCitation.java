package com.ohtukki.citations.models;

import com.ohtukki.citations.domain.AlphabetConverter;

/**
 * Class contains methods for book citations
 */
public class MiscCitation extends Citation {
    public String[] requiredFields = new String[] {"id"};
    public String[] optionalFields = new String[] {"author", "title", "howpublished", "month", "year", "note", "key"};
}
