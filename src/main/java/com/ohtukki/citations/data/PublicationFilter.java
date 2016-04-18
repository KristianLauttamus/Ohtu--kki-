/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ohtukki.citations.data;

import com.ohtukki.citations.models.Citation;
import com.google.common.base.Predicate;

/**
 *
 * @author Esa Potkonen
 */
public class PublicationFilter implements Predicate<Citation>  {
    private String publication;
    public PublicationFilter(String publication) {
            this.publication = publication;
    }

    @Override
    public boolean apply(Citation citation) {
       return citation.publisher.equals(publication);
    }
     
}
