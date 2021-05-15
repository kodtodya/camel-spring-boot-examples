package com.kodtodya.practice.repository;

import com.kodtodya.practice.beans.Training;

import java.util.List;

public interface EntryRepository {

        public String store(Training t);

        public List<Training> retrieve();

        public String search(int id);

        public String delete(int id);
}