package com.algorhythmic92.ws.benchmark.enumeration;

import lombok.Getter;

@Getter
public enum ExerciseVariation {
    BARBELL("Barbell"),
    CABLE("Cable"),
    DUMBBELL("Dumbbell");

    final String value;

    ExerciseVariation(String value) {
        this.value = value;
    }

    private static ExerciseVariation getByValue(String value) {
        for (ExerciseVariation variation : ExerciseVariation.values()) {
            if (variation.value.equals(value)) {
                return variation;
            }
        }
        return null;
    }
}
