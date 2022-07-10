package com.tgt.rysetii.learningresourcesapiyamini_vithanala.entity;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;

@Entity
@Table(name="Learningresources")
public class LearningResource implements Serializable {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "cost_price")
    private Double costPrice;

    @Column(name = "selling_price")
    private Double sellingPrice;

    @Column(name = "learning_resource_status")
    @Enumerated(EnumType.STRING)
    private LearningResourceStatus learningResourceStatus;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Column(name = "published_date")
    private LocalDate publishedDate;

    @Column(name = "retired_date")
    private LocalDate retiredDate;

    public LearningResource() {
    }

    public LearningResource(Integer id, String name, Double costPrice, Double sellingPrice, LearningResourceStatus learningResourceStatus, LocalDate createdDate, LocalDate publishedDate, LocalDate retiredDate) {
        this.id = id;
        this.name = name;
        this.costPrice = costPrice;
        this.sellingPrice = sellingPrice;
        this.learningResourceStatus = learningResourceStatus;
        this.createdDate = createdDate;
        this.publishedDate = publishedDate;
        this.retiredDate = retiredDate;
    }

    public Integer getid() {
        return id;
    }

    public void setid(Integer id) {
        this.id = id;
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public Double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(Double costPrice) {
        this.costPrice = costPrice;
    }

    public Double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(Double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public LearningResourceStatus getLearningResourceStatus() {
        return learningResourceStatus;
    }

    public void setLearningResourceStatus(LearningResourceStatus learningResourceStatus) {
        this.learningResourceStatus = learningResourceStatus;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDate getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(LocalDate publishedDate) {
        this.publishedDate = publishedDate;
    }

    public LocalDate getRetiredDate() {
        return retiredDate;
    }

    public void setRetiredDate(LocalDate retiredDate) {
        this.retiredDate = retiredDate;
    }

    @Override
    public String toString() {
        return "LearningResource{" +
                "id=" + id +
                ",name='" + name + '\'' +
                ", costPrice=" + costPrice +
                ", sellingPrice=" + sellingPrice +
                ", learningResourceStatus=" + learningResourceStatus +
                ", createdDate=" + createdDate +
                ", publishedDate=" + publishedDate +
                ", retiredDate=" + retiredDate +
                '}';
    }
}



