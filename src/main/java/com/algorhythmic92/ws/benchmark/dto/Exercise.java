package com.algorhythmic92.ws.benchmark.dto;

import com.algorhythmic92.ws.benchmark.enumeration.ExerciseVariation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Exercise {
    private Long id;
    private String name;
    private ExerciseVariation variation;
    private Integer weight;
    private Integer reps;
    private LocalDate dateAchieved;
}