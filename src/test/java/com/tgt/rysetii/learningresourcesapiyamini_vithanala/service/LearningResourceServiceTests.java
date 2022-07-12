package com.tgt.rysetii.learningresourcesapiyamini_vithanala.service;

import com.tgt.rysetii.learningresourcesapiyamini_vithanala.entity.LearningResource;
import com.tgt.rysetii.learningresourcesapiyamini_vithanala.entity.LearningResourceStatus;
import com.tgt.rysetii.learningresourcesapiyamini_vithanala.repository.LearningResourceRepository;
import com.tgt.rysetii.learningresourcesapiyamini_vithanala.services.LearningResourceService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class LearningResourceServiceTests {

    @Mock
    LearningResourceRepository learningResourceRepository;

    @InjectMocks
    LearningResourceService learningResourceService;

    @Test
    public void getProfitMarginOfAllTheAvailableLearningResources(){
        List<LearningResource> learningResources = new ArrayList<>();
        LearningResource learningResource1 = new LearningResource(1311,"Java full stack ",200.0,300.0, LearningResourceStatus.LIVE, LocalDate.now(), LocalDate.now().plusMonths(5), LocalDate.now().plusYears(2));
        LearningResource learningResource2 = new LearningResource(1312,"web development ",500.0,800.0, LearningResourceStatus.PUBLISHED, LocalDate.now(), LocalDate.now().plusMonths(7), LocalDate.now().plusYears(3));

        learningResources.add(learningResource1);
        learningResources.add(learningResource2);

        List<Double> expectedProfitMargins = learningResources.stream()
                .map(lr -> ((lr.getSellingPrice() - lr.getCostPrice())/lr.getSellingPrice()))
                .collect(toList());

        when(learningResourceRepository.findAll()).thenReturn(learningResources);

        List<Double> actualProfitMargins = learningResourceService.getProfitMargin();
        assertEquals(expectedProfitMargins, actualProfitMargins, "Wrong profit margins");
    }

    @Test
    public void sortTheLearningResourceBasedOnProfitMarginInNonIncreasingOrder(){
        List<LearningResource> learningResources = new ArrayList<>();
        LearningResource learningResource1 = new LearningResource(1311,"Java full stack ",200.0,300.0, LearningResourceStatus.LIVE, LocalDate.now(), LocalDate.now().plusMonths(5), LocalDate.now().plusYears(2));
        LearningResource learningResource2 = new LearningResource(1312,"web development ",500.0,800.0, LearningResourceStatus.PUBLISHED, LocalDate.now(), LocalDate.now().plusMonths(7), LocalDate.now().plusYears(3));

        learningResources.add(learningResource1);
        learningResources.add(learningResource2);

        learningResources.sort((lr1, lr2) -> {
            Double profitMargin1 = (lr1.getSellingPrice() - lr1.getCostPrice())/lr1.getSellingPrice();
            Double profitMargin2 = (lr2.getSellingPrice() - lr2.getCostPrice())/lr2.getSellingPrice();

            return profitMargin2.compareTo(profitMargin1) ;
        });

        when(learningResourceRepository.findAll()).thenReturn(learningResources);

        List<LearningResource> learningResourcesSorted = learningResourceService.sortLearningResourcesByProfitMargin();

        assertEquals(learningResources, learningResourcesSorted, "The learning resources are not sorted by profit margin");
    }

    @Test
    public void saveTheLearningResources(){
        List<LearningResource> learningResources = new ArrayList<>();
        LearningResource learningResource1 = new LearningResource(1311,"Java full stack ",200.0,300.0, LearningResourceStatus.LIVE, LocalDate.now(), LocalDate.now().plusMonths(5), LocalDate.now().plusYears(2));
        LearningResource learningResource2 = new LearningResource(1312,"web development ",500.0,800.0, LearningResourceStatus.PUBLISHED, LocalDate.now(), LocalDate.now().plusMonths(7), LocalDate.now().plusYears(3));
        LearningResource learningResource3 = new LearningResource(1313,"spring boot full course",600.0,700.0, LearningResourceStatus.LIVE, LocalDate.now(), LocalDate.now().plusMonths(5), LocalDate.now().plusYears(2));
        learningResources.add(learningResource1);
        learningResources.add(learningResource2);
        learningResources.add(learningResource3);

        learningResourceService.saveLearningResources(learningResources);

        verify(learningResourceRepository, times(learningResources.size())).save(any(LearningResource.class));
    }

    @Test
    public void deleteLearningResourceById(){
        int id = 5678;
        learningResourceService.deleteLearningResourceById(id);

        verify(learningResourceRepository, times(1)).deleteById(id);
    }
}
