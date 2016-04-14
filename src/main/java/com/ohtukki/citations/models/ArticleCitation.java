package com.ohtukki.citations.models;

import com.ohtukki.citations.domain.AlphabetConverter;

/**
 * Class contains methods for article citations
 */
public class ArticleCitation extends Citation {

    /**
     * Method creates bibtex entry from article citation
     *
     * @return Returns bibtex entry
     */
    public String createBibtexEntry() {
        String entry = "@ARTICLE{" + idToBibtex()
                + authorToBibtex()
                + titleToBibtex()
                + journalToBibtex()
                + yearToBibtex()
                + volumeToBibtex()
                + numberToBibtex()
                + pagesToBibtex()
                + monthToBibtex()
                + noteToBibtex()
                + keyToBibtex() + "}\n\n";

        return new AlphabetConverter().umlautsToBibtex(entry);
    }

    // temp. test to see that the citation fields get filled 
    @Override
    public String toString() {
        return "id: " + this.getId() + " author: " + this.getAuthor() + " name: " + this.getName()
                + " journal: " + this.getJournal() + " year: " + this.getYear() + " volume: " + this.getVolume()
                + " number: " + this.getNumber() + " pages: " + this.getPages() + " month: " + this.getMonth()
                + " note: " + this.getMonth() + " key: " + this.getKey();
    }

}
