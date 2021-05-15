package com.kodtodya.practice.repository;

import com.kodtodya.practice.beans.Training;
import org.springframework.stereotype.Repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TrainingRepository implements EntryRepository{
    private static final Logger logger = LoggerFactory.getLogger(TrainingRepository.class);
    private List<Training> trainingList = new ArrayList<Training>();
    private int index = -1;

    @Override
    public String store(Training training) {
        logger.info("/inside the TrainingRepository.store()");
        training.setId(++index);
        trainingList.add(index, training);
        return training.toString();
    }

    @Override
    public String delete(int id) {
        logger.info("/inside the TrainingRepository.delete()");
        trainingList.remove(id);
        return "training removed";
    }

    @Override
    public List<Training> retrieve() {
        logger.info("/inside the TrainingRepository.retrieve()");
        return trainingList;
    }

    @Override
    public String search(int index) {
        logger.info("/inside the TrainingRepository.search()");
        return this.trainingList.get(index).toString();
    }
}