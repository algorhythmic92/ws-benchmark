package com.algorhythmic92.ws.benchmark.model;

import com.algorhythmic92.ws.benchmark.enumeration.ExerciseVariation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ExerciseIdentifier {
    private ExerciseVariation variation;
    private String name;
}
