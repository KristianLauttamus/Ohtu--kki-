package com.ohtukki.citations.controllers;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.ohtukki.citations.data.AuthorFilter;
import com.ohtukki.citations.data.Database;
import com.ohtukki.citations.data.DatabaseJsonDao;
import com.ohtukki.citations.data.PublicationFilter;
import com.ohtukki.citations.data.YearFilter;
import com.ohtukki.citations.models.ArticleCitation;
import com.ohtukki.citations.models.Citation;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CitationController {
    private DatabaseJsonDao database;
    
    public CitationController(){
        this.database = new DatabaseJsonDao(DatabaseJsonDao.DEFAULT_FILE);
    }
    
    /**
     * List all Citations
     * @param model
     * @return view from resources/templates
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("citations", this.database.all());
        
        return "index";
    }
    
    /**
     * Return form to create a new Citation
     * @param model
     * @return view from resources/templates
     */
    @RequestMapping(value = "/citation", method = RequestMethod.GET)
    public String create(Model model) {
        model.addAttribute("articleCitation", new ArticleCitation());
        
        return "create-citation";
    }
    
    /**
     * Handle inputs and store the posted Citation
     * @param type
     * @param articleCitation
     * @param articleCitationResult
     * @return view from resources/templates
     */
    @RequestMapping(value = "/citation", method = RequestMethod.POST)
    public String store(@RequestParam("type") String type,
            @ModelAttribute ArticleCitation articleCitation, BindingResult articleCitationResult) {
        
        if(type.equals("article")){
            this.database.save(articleCitation);
        }
        
        return "redirect:/";
    }
    /**
     * Handle filtered listing
     * @return view from resources/templates
     */
    @RequestMapping(value = "/filter", method = RequestMethod.POST)
    public String filter(@RequestParam(value = "publication", required = false) String publication,
            @RequestParam(value = "author") String author,
            @RequestParam(value = "year") String year,
            Model model) {
        Predicate<Citation> filter = Predicates.notNull();
        if(StringUtils.hasText(publication)) {
            filter = Predicates.and(filter, new PublicationFilter(publication));
        }
        if(StringUtils.hasText(author)) {
            filter = Predicates.and(filter, new AuthorFilter(author));
        }
        if(StringUtils.hasText(year)) {
            filter = Predicates.and(filter, new YearFilter(year));
        }
        model.addAttribute("citations", database.allByPredicate(filter));
        return "index";
    }
     /**
     * Download Citations as bibtext file
    * @return view from resources/templates
     */
    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public void download(HttpServletResponse response, Model model) throws IOException {
        /* 
        "Content-Disposition : inline" will show viewable types 
        [like images/text/pdf/anything viewable by browser]
        right on browser while others(zip e.g) will be directly downloaded 
        [may provide save as popup, based on your browser setting.]
        */
        response.setHeader("Content-Disposition", String.format("inline; filename=\"BibTex.bib\""));
        response.setContentType("text/plain");
        formatBibText(database.all(), response.getOutputStream());
    }
    
    private void formatBibText(List<Citation> citations, OutputStream out) throws IOException {
        StringBuffer sb = new StringBuffer();
        for(Citation c : citations) {
            sb.append(c.createBibtexEntry());
        }
        InputStream is = new BufferedInputStream(new ByteArrayInputStream(sb.toString().getBytes()));
        //Copy bytes from source to destination, closes both streams.
        FileCopyUtils.copy(is, out);
    }
}
