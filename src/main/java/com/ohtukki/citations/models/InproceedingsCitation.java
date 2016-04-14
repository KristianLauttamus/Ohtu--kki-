package com.ohtukki.citations.models;

import com.ohtukki.citations.domain.AlphabetConverter;

/**
 * Class contains methods for inproceedings citations
 */
public class InproceedingsCitation extends Citation {
    
    /**
     * Method creates bibtex entry from inproceedings citation
     *
     * @return Returns bibtex entry
     */
    public String createBibtexEntry() {
        String entry = "@INPROCEEDINGS{" + idToBibtex()
                + authorToBibtex()
                + titleToBibtex()
                + booktitleToBibtex()
                + yearToBibtex()
                + editorToBibtex()
                + pagesToBibtex()
                + organizationToBibtex()
                + publisherToBibtex()
                + addressToBibtex()
                + monthToBibtex()
                + noteToBibtex()
                + keyToBibtex() + "}\n\n";

        return new AlphabetConverter().umlautsToBibtex(entry);
    }
}
