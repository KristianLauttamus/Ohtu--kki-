package com.ohtukki.citations.controllers;

import com.ohtukki.citations.Application;
import com.ohtukki.citations.data.DatabaseJsonDao;
import com.ohtukki.citations.domain.BibfileParser;
import com.ohtukki.citations.models.*;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public @ResponseBody List<Citation> getAllCitations(){        
        return this.database.all();
    }
    
    /**
     * Return form to create a new Citation
     * @param model
     * @return view from resources/templates
     */
    @RequestMapping(value = "/citation", method = RequestMethod.GET)
    public String create(Model model) {
        model.addAttribute("articleCitation", new ArticleCitation());
        model.addAttribute("bookCitation", new BookCitation());
        model.addAttribute("bookletCitation", new BookletCitation());
        model.addAttribute("conferenceCitation", new ConferenceCitation());
        model.addAttribute("inbookCitation", new InbookCitation());
        model.addAttribute("incollectionCitation", new IncollectionCitation());
        model.addAttribute("inproceedingsCitation", new InproceedingsCitation());
        model.addAttribute("manualCitation", new ManualCitation());
        model.addAttribute("mastersthesisCitation", new MastersthesisCitation());
        model.addAttribute("miscCitation", new MiscCitation());
        model.addAttribute("phdthesisCitation", new PHDThesisCitation());
        model.addAttribute("proceedingsCitation", new ProceedingsCitation());
        model.addAttribute("techreportCitation", new TechReportCitation());
        model.addAttribute("unpublishedCitation", new UnpublishedCitation());
        
        return "create-citation";
    }
    
    /**
     * Handle inputs and store the posted Citation
     * 
     * @param type
     * @param articleCitation
     * @param articleCitationResult
     * @param bookCitation
     * @param bookCitationResult
     * @param bookletCitation
     * @param bookletCitationResult
     * @param conferenceCitation
     * @param conferenceCitationResult
     * @param inbookCitation
     * @param inbookCitationResult
     * @param incollectionCitation
     * @param incollectionCitationResult
     * @param inproceedingsCitation
     * @param inproceedingsCitationResult
     * @param manualCitation
     * @param manualCitationResult
     * @param mastersthesisCitation
     * @param mastersthesisCitationResult
     * @param miscCitation
     * @param miscCitationResult
     * @param phdthesisCitation
     * @param phdthesisCitationResult
     * @param proceedingsCitation
     * @param proceedingsCitationResult
     * @param techreportCitation
     * @param techreportCitationResult
     * @param unpublishedCitation
     * @param unpublishedCitationResult
     * @return 
     */
    @RequestMapping(value = "/citation", method = RequestMethod.POST)
    public String store(@RequestParam("citationType") String citationType,
            @ModelAttribute ArticleCitation articleCitation, BindingResult articleCitationResult,
            @ModelAttribute BookCitation bookCitation, BindingResult bookCitationResult,
            @ModelAttribute BookletCitation bookletCitation, BindingResult bookletCitationResult,
            @ModelAttribute ConferenceCitation conferenceCitation, BindingResult conferenceCitationResult,
            @ModelAttribute InbookCitation inbookCitation, BindingResult inbookCitationResult,
            @ModelAttribute IncollectionCitation incollectionCitation, BindingResult incollectionCitationResult,
            @ModelAttribute InproceedingsCitation inproceedingsCitation, BindingResult inproceedingsCitationResult,
            @ModelAttribute ManualCitation manualCitation, BindingResult manualCitationResult,
            @ModelAttribute MastersthesisCitation mastersthesisCitation, BindingResult mastersthesisCitationResult,
            @ModelAttribute MiscCitation miscCitation, BindingResult miscCitationResult,
            @ModelAttribute PHDThesisCitation phdthesisCitation, BindingResult phdthesisCitationResult,
            @ModelAttribute ProceedingsCitation proceedingsCitation, BindingResult proceedingsCitationResult,
            @ModelAttribute TechReportCitation techreportCitation, BindingResult techreportCitationResult,
            @ModelAttribute UnpublishedCitation unpublishedCitation, BindingResult unpublishedCitationResult,
            RedirectAttributes redirectAttributes) {
        
        boolean validated = true;
        
        switch (citationType) {
            case "article":
                validated = articleCitation.validate(false);
                if(validated)
                    this.database.save(articleCitation);
                break;
            case "book":
                validated = bookCitation.validate(false);
                if(validated)
                    this.database.save(bookCitation);
                break;
            case "booklet":
                validated = bookletCitation.validate(false);
                if(validated)
                    this.database.save(bookletCitation);
                break;
            case "conference":
                validated = conferenceCitation.validate(false);
                if(validated)
                    this.database.save(conferenceCitation);
                break;
            case "inbook":
                validated = inbookCitation.validate(false);
                if(validated)
                    this.database.save(inbookCitation);
                break;
            case "incollection":
                validated = incollectionCitation.validate(false);
                if(validated)
                    this.database.save(incollectionCitation);
                break;
            case "inproceedings":
                validated = inproceedingsCitation.validate(false);
                if(validated)
                    this.database.save(inproceedingsCitation);
                break;
            case "manual":
                validated = manualCitation.validate(false);
                if(validated)
                    this.database.save(manualCitation);
                break;
            case "mastersthesis":
                validated = mastersthesisCitation.validate(false);
                if(validated)
                    this.database.save(mastersthesisCitation);
                break;
            case "misc":
                validated = miscCitation.validate(false);
                if(validated)
                    this.database.save(miscCitation);
                break;
            case "phdthesis":
                validated = phdthesisCitation.validate(false);
                if(validated)
                    this.database.save(phdthesisCitation);
                break;
            case "proceedings":
                validated = proceedingsCitation.validate(false);
                if(validated)
                    this.database.save(proceedingsCitation);
                break;
            case "techreport":
                validated = techreportCitation.validate(false);
                if(validated)
                    this.database.save(techreportCitation);
                break;
            case "unpublished":
                validated = unpublishedCitation.validate(false);
                if(validated)
                    this.database.save(unpublishedCitation);
                break;
        }
        
        if(!validated){
            // Todo error message
            
            return "redirect:/citation";
        }
        
        return "redirect:/";
    }
    
    /**
     * Return form to edit a Citation
     * @param model
     * @param id
     * @return view from resources/templates
     */
    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    public String edit(Model model, @PathVariable String id) {
        Citation citation = this.database.find(id);
        
        if(citation == null){
            return "redirect:/";
        }
        
        model.addAttribute("citation", citation);
        
        return "edit-citation";
    }
    
    /**
     * Update Citation
     * @param citation
     * @param citationResult
     * @param id
     * @param redirectAttributes
     * @return view from resources/templates
     */
    @RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
    public String update(@PathVariable String id, @ModelAttribute DumbCitation citation, BindingResult citationResult, RedirectAttributes redirectAttributes) {
        this.database.update(id, citation);
        
        return "redirect:/";
    }
    
    /**
     * Download Citations as bibtext file
     * @return view from resources/templates
     */
    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public void download(HttpServletResponse response, Model model, 
        @RequestParam(value = "filename", required = false) String filename) throws IOException {
        /* 
        "Content-Disposition : inline" will show viewable types 
        [like images/text/pdf/anything viewable by browser]
        right on browser while others(zip e.g) will be directly downloaded 
        [may provide save as popup, based on your browser setting.]
        */
        if(filename == null || filename.equals("")){
            response.setHeader("Content-Disposition", String.format("attachment; filename=\"BibTex.bib\""));
        } else {
            if(filename.toLowerCase().indexOf(".bib") > 0){
                filename = filename.substring(0, filename.toLowerCase().indexOf(".bib"));
            }
            response.setHeader("Content-Disposition", String.format("attachment; filename=\""+filename+".bib\""));
        }
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
    
    @RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
    @ResponseBody
    public void delete(@PathVariable String id){
        this.database.destroy(id);
    }
    @RequestMapping(method = RequestMethod.POST, value = "/upload")
    public String handleFileUpload( @RequestParam("file") MultipartFile file,
                                    Model model) {
        if (!file.isEmpty()) {
            try {
                BufferedOutputStream stream = new BufferedOutputStream(new ByteArrayOutputStream());
                FileCopyUtils.copy(file.getInputStream(), stream);
                System.out.println("com.ohtukki.citations.controllers.CitationController.handleFileUpload(" + file.getName() + ")");
                stream.close();
                
                BibfileParser parser = new BibfileParser(stream.toString());
                List<Citation> imported = parser.parseCitations();
                for (Citation c : imported) {
                    database.add(c);
                }
                model.addAttribute("citations", this.database.all());
                model.addAttribute("message", "You successfully uploaded " + file.getName() + "! With "+imported.size()+" Citations.");
            } catch (Exception e) {
                model.addAttribute("message", "You failed to upload " + file.getName() + " => " + e.getMessage());
            }
        } else {
            model.addAttribute("message", "You failed to upload " + file.getName() + " because the file was empty");
        }

        return "index";
    }
}
