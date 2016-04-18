package com.ohtukki.citations.models;

import com.ohtukki.citations.domain.AlphabetConverter;

/**
 * Class contains methods for book citations
 */
public class MasterthesisCitation extends Citation {
    public String[] requiredFields = new String[] {"id", "author", "title", "school", "year"};
    public String[] optionalFields = new String[] {"address", "month", "note", "key"};
}
