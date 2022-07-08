package com.tgt.rysetii.learningresourcesapiyamini_vithanala.repository;

import com.tgt.rysetii.learningresourcesapiyamini_vithanala.entity.LearningResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LearningResourceRepository extends JpaRepository<LearningResource, Integer> {
}