package com.algorhythmic92.ws.benchmark.dto;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "exercises")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String variation;

    @Column(nullable = false)
    private Integer reps;

    @Column(nullable = false)
    private Integer weight;

    @Column(nullable = false)
    private LocalDate dateAchieved;
}
