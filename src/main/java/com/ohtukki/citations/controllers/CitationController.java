package com.ohtukki.citations.controllers;

import com.ohtukki.citations.components.Message;
import com.ohtukki.citations.components.User;
import com.ohtukki.citations.data.DatabaseJsonDao;
import com.ohtukki.citations.domain.BibfileParser;
import com.ohtukki.citations.models.*;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CitationController {
    private DatabaseJsonDao database;

    @Autowired
    private User user;
    
    public CitationController(){
        this.database = new DatabaseJsonDao(DatabaseJsonDao.DEFAULT_FILE);
    }
    
    /**
     * List all Citations
     * @param model
     * @param session
     * @return view from resources/templates
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model, HttpSession session) {
        model.addAttribute("citations", this.database.all());  
        session.setAttribute("user", user);
        
        return "index";
    }
    
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public @ResponseBody List<Citation> getAllCitations(){        
        return this.database.all();
    }
    
    /**
     * Return form to create a new Citation
     * @param model
     * @param session
     * @return view from resources/templates
     */
    @RequestMapping(value = "/citation", method = RequestMethod.GET)
    public String create(Model model, HttpSession session) {
        addAttributes(model);
        
        session.setAttribute("user", user);
        
        return "create-citation";
    }

    private void addAttributes(Model model) {
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
    }

    /**
     * Handle inputs and store the posted Citation
     * 
     * @param citationType
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
     * @param redirectAttributes
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

        validated = isValidated(citationType, articleCitation, bookCitation, bookletCitation, conferenceCitation, inbookCitation, incollectionCitation, inproceedingsCitation, manualCitation, mastersthesisCitation, miscCitation, phdthesisCitation, proceedingsCitation, techreportCitation, unpublishedCitation, validated);

        if(!validated){
            // Todo error message
            redirectAttributes.addAttribute("citationType", citationType);
            
            return "redirect:/citation";
        }

        user.addScore(1);

        return "redirect:/";
    }

    private boolean isValidated(@RequestParam("citationType") String citationType, @ModelAttribute ArticleCitation articleCitation, @ModelAttribute BookCitation bookCitation, @ModelAttribute BookletCitation bookletCitation, @ModelAttribute ConferenceCitation conferenceCitation, @ModelAttribute InbookCitation inbookCitation, @ModelAttribute IncollectionCitation incollectionCitation, @ModelAttribute InproceedingsCitation inproceedingsCitation, @ModelAttribute ManualCitation manualCitation, @ModelAttribute MastersthesisCitation mastersthesisCitation, @ModelAttribute MiscCitation miscCitation, @ModelAttribute PHDThesisCitation phdthesisCitation, @ModelAttribute ProceedingsCitation proceedingsCitation, @ModelAttribute TechReportCitation techreportCitation, @ModelAttribute UnpublishedCitation unpublishedCitation, boolean validated) {
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
        return validated;
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
     * @param response
     * @param model
     * @param filename
     * @throws java.io.IOException
     */
    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public void download(HttpServletResponse response, Model model, 
        @RequestParam(value = "filename", required = false) String filename) throws IOException {

        if(filename == null || filename.equals("")){
            response.setHeader("Content-Disposition", String.format("attachment; filename=\"BibTex.bib\""));
        } else {
            response.setHeader("Content-Disposition", String.format("attachment; filename=\""+filename+".bib\""));
        }
        response.setContentType("text/plain");
        formatBibText(database.all(), response.getOutputStream());
    }
    
    private void formatBibText(List<Citation> citations, OutputStream out) throws IOException {
        StringBuilder sb = new StringBuilder();
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
    
    @RequestMapping(value = "/checkId/{id}", method = RequestMethod.GET)
    public @ResponseBody Citation checkId(@PathVariable String id){
        return this.database.find(id);
    }
    
    @RequestMapping(method = RequestMethod.POST, value = "/upload")
    public String handleFileUpload( @RequestParam("file") MultipartFile file,
                                    RedirectAttributes redirect, HttpSession session) {
        if (!file.isEmpty()) {
            try {
                List<Citation> rejected = new ArrayList<Citation>();
                int count = parseInputFile(file, rejected);
                
                if(count > 0){
                    fileSuccess(file, redirect, count);
                }

                rejected(redirect, rejected);
            } catch (Exception e) {
                failedMessage(file, redirect, e);
            }
        } else {
            fileIsEmptyMessage(file, redirect);
        }
        
        return "redirect:/";
    }

    private void rejected(RedirectAttributes redirect, List<Citation> rejected) {
        redirect.addFlashAttribute("rejected", rejected);
    }

    private void fileSuccess(@RequestParam("file") MultipartFile file, RedirectAttributes redirect, int count) {
        successMessage(file, redirect, count);

        user.addScore(count);
    }

    private void fileIsEmptyMessage(@RequestParam("file") MultipartFile file, RedirectAttributes redirect) {
        redirect.addFlashAttribute("message", new Message("", "Your file (" + file.getName() + ") was empty"));
    }

    private void failedMessage(@RequestParam("file") MultipartFile file, RedirectAttributes redirect, Exception e) {
        redirect.addFlashAttribute("message", new Message("Failed!", "Upload had an error " + file.getName() + " => " + e.getMessage(), "danger"));
    }

    private void successMessage(@RequestParam("file") MultipartFile file, RedirectAttributes redirect, int count) {
        redirect.addFlashAttribute("message", new Message("Success!", "File was uploaded " + file.getName() + " with "+count+" Citations.", "success"));
    }

    private int parseInputFile(MultipartFile file, List<Citation> rejected) throws IOException {
        ByteArrayOutputStream byos = new ByteArrayOutputStream();
        
        try (BufferedOutputStream stream = new BufferedOutputStream(byos)) {
            FileCopyUtils.copy(file.getInputStream(), stream);
        }

        BibfileParser parser = new BibfileParser(byos.toString());

        List<Citation> imported = parser.parseCitations();
        for (Citation c : imported) {
            if(c.validate(false)) {
                database.add(c);
            } else {
                rejected.add(c);
            }
        }
        database.saveAll();
        
        return imported.size()-rejected.size();
    }
}
