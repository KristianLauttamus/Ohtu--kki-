package com.ohtukki.citations.models;

import com.ohtukki.citations.domain.AlphabetConverter;

/**
 * Class contains methods for book citations
 */
public class ProceedingsCitation extends Citation {
    public String[] requiredFields = new String[] {"id", "title", "year"};
    public String[] optionalFields = new String[] {"editor", "publisher", "organization", "address", "month", "note", "key"};
}
