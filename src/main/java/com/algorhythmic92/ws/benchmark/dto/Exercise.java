package com.algorhythmic92.ws.benchmark.dto;

import com.algorhythmic92.ws.benchmark.enumeration.ExerciseVariation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;
    private ExerciseVariation variation;
    private Integer weight;
    private Integer reps;
    private LocalDate dateAchieved;
}