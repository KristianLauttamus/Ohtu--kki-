/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ohtukki.citations.data;
import com.google.common.base.Predicate;
import com.ohtukki.citations.models.Citation;

/**
 *
 * @author Esa Potkonen
 */
public class YearFilter implements Predicate<Citation> {
    private String year;
    public YearFilter(String year) {
            this.year = year;
    }

    @Override
    public boolean apply(Citation citation) {
       return citation.getYear().equals(year);
    }
    
}
