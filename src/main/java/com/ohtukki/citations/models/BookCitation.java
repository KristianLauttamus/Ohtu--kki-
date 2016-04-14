package com.ohtukki.citations.models;

import com.ohtukki.citations.domain.AlphabetConverter;

/**
 * Class contains methods for book citations
 */
public class BookCitation extends Citation {
    
    /**
     * Method creates bibtex entry from book citation
     *
     * @return Returns bibtex entry
     */
    public String createBibtexEntry() {
        String entry = "@BOOK{" + idToBibtex()
                + authorToBibtex()
                + editorToBibtex()
                + titleToBibtex()
                + publisherToBibtex()
                + yearToBibtex()
                + volumeToBibtex()
                + seriesToBibtex()
                + addressToBibtex()
                + editionToBibtex()
                + monthToBibtex()
                + noteToBibtex()
                + keyToBibtex() + "}\n\n";

        return new AlphabetConverter().umlautsToBibtex(entry);
    }
}
