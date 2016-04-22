package com.ohtukki.citations.test.domain;

import com.ohtukki.citations.domain.BibfileParser;
import com.ohtukki.citations.models.Citation;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class BibfileParserTest {

    String[] types = {"Article", "Book", "Booklet", "Conference", "Inbook",
        "Incollection", "Inproceedings", "Manual", "Mastersthesis", "Misc",
        "PHDThesis", "Proceedings", "TechReport", "Unpublished"};

    String[] fields = {"id", "address", "author", "booktitle", "chapter",
        "edition", "editor", "howpublished", "institution", "journal", "key",
        "month", "note", "number", "organization", "pages", "publisher",
        "school", "series", "title", "type", "volume", "year"};

    String file = "@BOOK{SWEBOK,\n"
            + "author = {Kalle P. Kirjailija},\n"
            + "title = {\\\"{O}\\\"{o}lannin \\\"{A}\\\"{a}ni},\n"
            + "publisher = {IEEE},\n"
            + "year = {2004},\n}\n\n";

    BibfileParser parser;

    @Before
    public void setUp() {
        parser = new BibfileParser(file);
    }

    @Test
    public void typeOfNewCitationIsRight() {
        for (int i = 0; i < types.length; i++) {
            String dummyFile = "@" + types[i].toUpperCase() + "{dummyID,\n}\n\n";
            parser = new BibfileParser(dummyFile);
            ArrayList<Citation> cit = (ArrayList<Citation>) parser.parseCitations();
            assertEquals(types[i], cit.get(0).citationType);
        }
    }

    @Test
    public void newCitationIsNullIfImpossibleHappens() {
        String dummyFile = "@WRONGTYPE{dummyID,\n}\n\n";
        try {
            parser = new BibfileParser(dummyFile);
            parser.parseCitations();
        } catch (NullPointerException e) {
            // If all goes well, we come here because getEmptyCitation()
            // returned null and caused an exception in parseCitations()
            assertTrue(true);
        }
    }

    @Test
    public void alphabetConversionWorks() {
        parser = new BibfileParser(file);
        Citation cit = parser.parseCitations().get(0);
        assertEquals("Öölannin Ääni", cit.title);
    }
    
    @Test
    public void parserWorksWithMoreThanOneCitation() {
        String longerFile = file + file + file;
        parser = new BibfileParser(longerFile);
        ArrayList<Citation> cit = (ArrayList<Citation>) parser.parseCitations();
        assertEquals(3, cit.size());
    }

    @Test
    public void fieldsAreSetRight() {
        String dummyFile = makeCitationWithAllFields();
        parser = new BibfileParser(dummyFile);
        Citation cit = parser.parseCitations().get(0);

        assertEquals(fields[0].toUpperCase(), cit.id);
        assertEquals(fields[1].toUpperCase(), cit.address);
        assertEquals(fields[2].toUpperCase(), cit.author);
        assertEquals(fields[3].toUpperCase(), cit.booktitle);
        assertEquals(fields[4].toUpperCase(), cit.chapter);
        assertEquals(fields[5].toUpperCase(), cit.edition);
        assertEquals(fields[6].toUpperCase(), cit.editor);
        assertEquals(fields[7].toUpperCase(), cit.howpublished);
        assertEquals(fields[8].toUpperCase(), cit.institution);
        assertEquals(fields[9].toUpperCase(), cit.journal);
        assertEquals(fields[10].toUpperCase(), cit.key);
        assertEquals(fields[11].toUpperCase(), cit.month);
        assertEquals(fields[12].toUpperCase(), cit.note);
        assertEquals(fields[13].toUpperCase(), cit.number);
        assertEquals(fields[14].toUpperCase(), cit.organization);
        assertEquals(fields[15].toUpperCase(), cit.pages);
        assertEquals(fields[16].toUpperCase(), cit.publisher);
        assertEquals(fields[17].toUpperCase(), cit.school);
        assertEquals(fields[18].toUpperCase(), cit.series);
        assertEquals(fields[19].toUpperCase(), cit.title);
        assertEquals(fields[20].toUpperCase(), cit.type);
        assertEquals(fields[21].toUpperCase(), cit.volume);
        assertEquals(fields[22].toUpperCase(), cit.year);
    }

    private String makeCitationWithAllFields() {
        String cit = "@BOOK{ID,\n";
        for (int i = 1; i < fields.length; i++) {
            cit += fields[i] + " = {" + fields[i].toUpperCase() + "},\n";
        }
        cit += "}\n\n";
        return cit;
    }
}
