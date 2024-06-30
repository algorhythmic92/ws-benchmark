package com.algorhythmic92.ws.benchmark.service;

import com.algorhythmic92.ws.benchmark.dto.Exercise;
import com.algorhythmic92.ws.benchmark.repository.ExerciseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;

    public List<Exercise> getAllExercises() {
        return exerciseRepository.findAll();
    }

    public Optional<Exercise> getExerciseById(Long id) {
        return exerciseRepository.findById(id);
    }

    public Exercise createExercise(Exercise exercise) {
        return exerciseRepository.save(exercise);
    }

    public Optional<Exercise> updateExercise(Long id, Exercise exerciseDetails) {
        return exerciseRepository.findById(id).map(exercise -> {
            exercise.setName(exerciseDetails.getName());
            exercise.setVariation(exerciseDetails.getVariation());
            exercise.setReps(exerciseDetails.getReps());
            exercise.setWeight(exerciseDetails.getWeight());
            exercise.setDateAchieved(exerciseDetails.getDateAchieved());
            return exerciseRepository.save(exercise);
        });
    }

    public boolean deleteExercise(Long id) {
        return exerciseRepository.findById(id).map(exercise -> {
            exerciseRepository.delete(exercise);
            return true;
        }).orElse(false);
    }
}

