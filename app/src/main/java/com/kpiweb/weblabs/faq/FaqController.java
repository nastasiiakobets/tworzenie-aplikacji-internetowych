package com.kpiweb.weblabs.faq;


import com.kpiweb.weblabs.group.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = "/faqs")
@RestController
public class FaqController {

    private final FaqService faqService;

    @Autowired
    public FaqController(FaqService faqService) {
        this.faqService = faqService;
    }


    @GetMapping
    public Page<Faq> getFaqs(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer elementsPerPage,
            @RequestParam(required = false, defaultValue = "ASC") Sort.Direction sortDirection,
            @RequestParam(required = false, defaultValue = "name") FaqService.SortFields sortField
    ){
        return faqService.getFaqs(page,elementsPerPage,sortDirection,sortField);
    }

    @GetMapping(path = "/search")
    public List<Faq> searchFaqs(@RequestParam(required = false, defaultValue = "") String question

    ){
        return faqService.searchFaqsByQuestions(question);
    }

    @GetMapping("/{id}")
    public Faq getOne(@PathVariable Long id){
        return faqService.getOneById(id);
    }

    @PostMapping
    public void registerNewFaq(@RequestBody Faq Faq){
        faqService.addNewFaq(Faq);
    }

    @DeleteMapping(path = "/{faqId}")
    public void deleteFaq(@PathVariable("faqId") Long id){
        faqService.deleteFaq(id);
    }

    @PutMapping(path = "/{Id}")
    public void updateFaq(
            @RequestBody Faq Faq){
        faqService.updateFaq(Faq);
    }

}
