package com.algorhythmic92.ws.benchmark.controller;

import com.algorhythmic92.ws.benchmark.dto.Exercise;
import com.algorhythmic92.ws.benchmark.service.ExerciseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/exercise")
@RequiredArgsConstructor
public class ExerciseController {

    private final ExerciseService exerciseService;

    @GetMapping
    public List<Exercise> getAllExercises() {
        return exerciseService.getAllExercises();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getExerciseById(@PathVariable Long id) {
        Optional<Exercise> exercise = exerciseService.getExerciseById(id);
        return exercise.isPresent() ? ResponseEntity.ok(exercise)
                : ResponseEntity.status(404).body("Exercise with ID " + id + " not found");
    }

    @PostMapping
    public ResponseEntity<Exercise> createExercise(@RequestBody Exercise exercise) {
        Exercise createdExercise = exerciseService.createExercise(exercise);
        return ResponseEntity.ok(createdExercise);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateExercise(@PathVariable Long id, @RequestBody Exercise exerciseDetails) {
        Optional<Exercise> updatedExercise = exerciseService.updateExercise(id, exerciseDetails);
        return updatedExercise.isPresent() ? ResponseEntity.ok(updatedExercise)
                : ResponseEntity.status(404).body("Exercise with ID " + id + " not found");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteExercise(@PathVariable Long id) {
        boolean isDeleted = exerciseService.deleteExercise(id);
        return isDeleted ? ResponseEntity.noContent().build()
                : ResponseEntity.status(404).body("Exercise with ID " + id + " not found");
    }
}
