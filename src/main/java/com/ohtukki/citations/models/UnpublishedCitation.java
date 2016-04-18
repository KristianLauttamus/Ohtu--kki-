package com.ohtukki.citations.models;

import com.ohtukki.citations.domain.AlphabetConverter;

/**
 * Class contains methods for book citations
 */
public class UnpublishedCitation extends Citation {
    public String[] requiredFields = new String[] {"id", "author", "title", "note"};
    public String[] optionalFields = new String[] {"month", "year", "key"};
}
