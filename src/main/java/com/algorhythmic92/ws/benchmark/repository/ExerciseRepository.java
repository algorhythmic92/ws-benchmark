package com.algorhythmic92.ws.benchmark.repository;

import com.algorhythmic92.ws.benchmark.dto.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

}

