package com.tgt.rysetii.learningresourcesapiyamini_vithanala.controllers;
import com.tgt.rysetii.learningresourcesapiyamini_vithanala.entity.LearningResource;
import com.tgt.rysetii.learningresourcesapiyamini_vithanala.services.LearningResourceService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/learningresources/v1")
public class LearningResourceController {

    private final LearningResourceService learningResourceService;

    public LearningResourceController(LearningResourceService learningResourceService) {
        this.learningResourceService = learningResourceService;
    }

    @GetMapping("/")
    public List<LearningResource> getAllLearningResources(){
        return learningResourceService.getLearningResources();
    }

    @PostMapping(value = "/", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveLearningResources(@RequestBody List<LearningResource> learningResources){
        learningResourceService.saveLearningResources(learningResources);
    }

    @DeleteMapping(value = "/learningresource/{id}")
    public void deleteLearningResource(@PathVariable int id){
        learningResourceService.deleteLearningResourceById(id);
    }
}
