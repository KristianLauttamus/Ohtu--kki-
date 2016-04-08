package com.ohtukki.citations.controllers;

import com.ohtukki.citations.data.Database;
import com.ohtukki.citations.data.DatabaseJsonDao;
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
    private DatabaseJsonDao database;
    
    public CitationController(){
        this.database = new DatabaseJsonDao("test-file");
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
        ArticleCitation articleCitation = new ArticleCitation();
        
        model.addAttribute("Article", articleCitation);
        
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
            @ModelAttribute("article") ArticleCitation articleCitation, BindingResult articleCitationResult
    ) {
        
        if(type == "article"){
            this.database.save(articleCitation);
        }
        
        return "redirect:/";
    }

}
