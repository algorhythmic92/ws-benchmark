package com.algorhythmic92.ws.benchmark.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "exercises")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
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
    @JsonFormat(pattern = "MM/dd/yyyy")
    private LocalDate dateAchieved;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Exercise exercise = (Exercise) o;
        return Objects.equals(name, exercise.name) &&
                Objects.equals(variation, exercise.variation) &&
                Objects.equals(reps, exercise.reps) &&
                Objects.equals(weight, exercise.weight) &&
                Objects.equals(dateAchieved, exercise.dateAchieved);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, variation, reps, weight, dateAchieved);
    }
}
