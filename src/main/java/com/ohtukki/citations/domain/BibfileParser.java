package com.ohtukki.citations.domain;

import com.ohtukki.citations.models.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class parses citations from bibtex file. Class handles only files that are
 * created by this program (or follow exactly the same format)
 */
public class BibfileParser {

    final String[] lines;
    List<Citation> citations;

    /**
     * Constructor
     *
     * @param file .bib-file as one string (including '\n'-characters)
     */
    public BibfileParser(String file) {
        this.lines = file.split("\n");
    }

    /**
     * Converts .bib-file to citations
     *
     * @return List of citations
     */
    public List<Citation> parseCitations() {
        this.citations = new ArrayList<>();
        int begin = 0;
        int end = 0;

        while (begin < lines.length - 1) {
            while (lines[end].charAt(0) != '}') {
                end++;
            }
            citations.add(makeCitation(begin, end));
            end += 2; // jump over empty line
            begin = end;
        }
        return citations;
    }

    // Method makes one citation
    private Citation makeCitation(int begin, int end) {
        Citation cit = initCitWithId(begin);
        fillFields(cit, begin + 1, end);
        return cit;
    }

    private Citation initCitWithId(int line) {
        int i = 1;
        String type = "" + lines[line].charAt(i++);
        while (lines[line].charAt(i) != '{') {
            type += lines[line].charAt(i++);
        }
        Citation cit = getEmptyCitation(type);

        i++;
        String id = "" + lines[line].charAt(i++);
        while (i < lines[line].length() - 1) {
            id += lines[line].charAt(i++);
        }

        cit.setId(id);
        return cit;
    }

    private void fillFields(Citation cit, int begin, int end) {
        while (begin < end) {
            String name = getFieldName(begin);
            String value = getFieldValue(begin);
            setField(cit, name, value);
            begin++;
        }
    }

    private String getFieldName(int line) {
        String name = "";
        int i = 0;
        while (lines[line].charAt(i) != ' ') {
            name += lines[line].charAt(i);
            i++;
        }
        return name;
    }

    private String getFieldValue(int line) {
        int i = 0;
        while (lines[line].charAt(i) != '{') {
            i++;
        }

        String value = "";
        for (int j = i + 1; j < lines[line].length() - 2; j++) {
            value += lines[line].charAt(j);
        }

        return value;
    }
    
    private Citation getEmptyCitation(String type) {
        switch (type) {
            case "ARTICLE":
                return new ArticleCitation();
            case "BOOK":
                return new BookCitation();
            case "BOOKLET":
                return new BookletCitation();
            case "CONFERENCE":
                return new ConferenceCitation();
            case "INBOOK":
                return new InbookCitation();
            case "INCOLLECTION":
                return new IncollectionCitation();
            case "INPROCEEDINGS":
                return new InproceedingsCitation();
            case "MANUAL":
                return new ManualCitation();
            case "MASTERSTHESIS":
                return new MastersthesisCitation();
            case "MISC":
                return new MiscCitation();
            case "PHDTHESIS":
                return new PHDThesisCitation();
            case "PROCEEDINGS":
                return new ProceedingsCitation();
            case "TECHREPORT":
                return new TechReportCitation();
            case "UNPUBLISHED":
                return new UnpublishedCitation();
        }
        return null; // this shouldn't happen
    }

    private void setField(Citation cit, String name, String value) {
        switch (name) {
            case "address":
                cit.setAddress(value);
                break;
            case "author":
                cit.setAuthor(value);
                break;
            case "booktitle":
                cit.setBooktitle(value);
                break;
            case "chapter":
                cit.setChapter(value);
                break;
            case "edition":
                cit.setEdition(value);
                break;
            case "editor":
                cit.setEditor(value);
                break;
            case "howpublished":
                cit.setHowpublished(value);
                break;
            case "institution":
                cit.setInstitution(value);
                break;
            case "journal":
                cit.setJournal(value);
                break;
            case "key":
                cit.setKey(value);
                break;
            case "month":
                cit.setMonth(value);
                break;
            case "note":
                cit.setNote(value);
                break;
            case "number":
                cit.setNumber(value);
                break;
            case "organization":
                cit.setOrganization(value);
                break;
            case "pages":
                cit.setPages(value);
                break;
            case "publisher":
                cit.setPublisher(value);
                break;
            case "school":
                cit.setSchool(value);
                break;
            case "series":
                cit.setSeries(value);
                break;
            case "title":
                cit.setTitle(value);
                break;
            case "type":
                cit.setType(value);
                break;
            case "volume":
                cit.setVolume(value);
                break;
            case "year":
                cit.setYear(value);
                break;
        }
    }

}
