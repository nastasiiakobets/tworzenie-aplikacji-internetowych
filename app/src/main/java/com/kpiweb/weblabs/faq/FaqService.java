package com.kpiweb.weblabs.faq;


import com.kpiweb.weblabs.exceptions.CRUDoperationException;
import com.kpiweb.weblabs.faq.Faq;
import com.kpiweb.weblabs.faq.FaqRepository;
import com.kpiweb.weblabs.faq.FaqService;
import com.kpiweb.weblabs.teacher.Teacher;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FaqService {
    private FaqRepository faqRepository;

    public enum SortFields {
        question
    }

    @Autowired
    public FaqService(FaqRepository faqRepository) {
        this.faqRepository = faqRepository;
    }

    public Page<Faq> getFaqs(
            @Min(value = 0,message = "Page is invalid!") Integer page,
            @Min(value = 1,message = "There can`t be less than 1 element per page!") Integer elementsPerPage,
            Sort.Direction sortDirection, FaqService.SortFields sortFields
    )
    {
        Pageable pageable = PageRequest.of(page,elementsPerPage,Sort.by(sortDirection,sortFields.name()));
        return faqRepository.findAll(pageable);
    }

    public Faq getOneById(Long id) {
        Optional<Faq> result = faqRepository.findById(id);
        if (!result.isPresent()) {
            throw new CRUDoperationException("There`s no faq with id: " + id,"id");
        }
        return result.orElse(null);
    }

    public void addNewFaq(@Valid Faq faq) {
        faqRepository.save(faq);
    }

    public void deleteFaq(@Min(value = 1,message = "Id is invalid!") Long id) {
        if(!faqRepository.existsById(id)){
            throw new CRUDoperationException("There is no faq with id: " + id,"id");
        }
        faqRepository.deleteById(id);
    }

    public List<Faq> searchFaqsByQuestions(@NotNull String question){
        return faqRepository.findAllByQuestion(question);
    }

    public void updateFaq(@Valid Faq faq) {
        Optional<Faq> faqOptional = faqRepository.findById(faq.getId());
        if (!faqOptional.isPresent()) {
            throw new CRUDoperationException("There`s no faq with id: " + faq.getId(),"id");
        }
        faqRepository.save(faq);
    }
}
