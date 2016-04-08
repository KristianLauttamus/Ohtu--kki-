package com.ohtukki.citations.controllers;

import com.ohtukki.citations.data.Database;
import com.ohtukki.citations.models.ArticleCitation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CitationController {
    private Database database;
    
    public CitationController(){
        // Todo
        // this.database = new Database;
    }
    
    /**
     * List all Citations
     * @param model
     * @return view from resources/templates
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        // Todo
        // Get all Citations
        // model.addAttribute("citations", citationsArray(?));
        return "index";
    }
    
    /**
     * Return form to create a new Citation
     * @return view from resources/templates
     */
    @RequestMapping(value = "/citation", method = RequestMethod.GET)
    public String create(Model model) {
        ArticleCitation articleCitation = new ArticleCitation();
        
        model.addAttribute("Article", articleCitation);
        
        return "create-citation";
    }
    
    /**
     * Handle inputs and store the posted Citation
     * @param article 
     * @return view from resources/templates
     */
    @RequestMapping(value = "/citation", method = RequestMethod.POST)
    public String store(@RequestParam("type") String type,
            @ModelAttribute("article") ArticleCitation articleCitation, BindingResult articleCitationResult
    ) {
        if(type == "article"){
            // Todo: save article
        }
        
        return "create-citation";
    }

}
