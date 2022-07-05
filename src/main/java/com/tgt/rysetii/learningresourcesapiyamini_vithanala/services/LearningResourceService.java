package com.tgt.rysetii.learningresourcesapiyamini_vithanala.services;


import com.tgt.rysetii.learningresourcesapiyamini_vithanala.entity.LearningResource;
import com.tgt.rysetii.learningresourcesapiyamini_vithanala.entity.LearningResourceStatus;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class LearningResourceService {

    public List<LearningResource> getLearningResources(){
        File learningResourcesFile  = new File("LearningResources.csv");
        List<LearningResource> learningResources = loadLearningResourceFromCsv(learningResourcesFile);
        return learningResources;
    }

  public List<Double> getProfitMargin(){
        List<LearningResource> learningResources = getLearningResources();

        List<Double> profitMargins = learningResources.stream().map(lr -> ((lr.getSellingPrice() - lr.getCostPrice())/lr.getSellingPrice())).collect(toList());

        return profitMargins;
    }

   public List<LearningResource> sortLearningResourcesByProfitMargin(){
        List<LearningResource> learningResources = getLearningResources();

        learningResources.sort((a, b) -> {
            Double pM1 = (a.getSellingPrice() - b.getCostPrice())/a.getSellingPrice();
            Double pM2 = (a.getSellingPrice() - b.getCostPrice())/b.getSellingPrice();

            return pM2.compareTo(pM1) ;
        });

        return learningResources;
    }

 private List<LearningResource> loadLearningResourceFromCsv(File csvFile){
        List<LearningResource> learningResources = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(csvFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line = null;
            line = bufferedReader.readLine();
            while(line!= null){
                String[] attributes = line.split(",");
                LearningResource learningResource = createLearningResource(attributes);
                learningResources.add(learningResource);
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return learningResources;
    }

   private void addLearningResourcesToCsv(List<LearningResource> learningResources){
              String sep = ",";

        try {
            File learningResourcesFile = new File("LearningResources.csv");
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(learningResourcesFile.getName(), true));
            for (LearningResource learningResource : learningResources) {
                bufferedWriter.newLine();
                StringBuffer Line = new StringBuffer();
                Line.append(learningResource.getid());
                Line.append(sep);
                Line.append(learningResource.getname());
                Line.append(sep);
                Line.append(learningResource.getCostPrice());
                Line.append(sep);
                Line.append(learningResource.getSellingPrice());
                Line.append(sep);
                Line.append(learningResource.getLearningResourceStatus());
                Line.append(sep);
                Line.append(learningResource.getCreatedDate());
                Line.append(sep);
                Line.append(learningResource.getPublishedDate());
                Line.append(sep);
                Line.append(learningResource.getRetiredDate());
                bufferedWriter.write(Line.toString());
            }
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

  private LearningResource createLearningResource(String[] attributes){
        Integer id = Integer.parseInt(attributes[0].replaceAll("\\D+", ""));
        String name = attributes[1];
        Double costPrice = Double.parseDouble(attributes[2]);
        Double sellingPrice = Double.parseDouble(attributes[3]);
        LearningResourceStatus learningResourceStatus = LearningResourceStatus.valueOf(attributes[4]);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate createdDate = LocalDate.parse(attributes[5], dateFormatter);
        LocalDate publishedDate = LocalDate.parse(attributes[6], dateFormatter);
        LocalDate retiredDate = LocalDate.parse(attributes[7], dateFormatter);

        LearningResource learningResource = new LearningResource(
                id,name, costPrice, sellingPrice, learningResourceStatus, createdDate, publishedDate, retiredDate
        );
        return learningResource;
    }


}
