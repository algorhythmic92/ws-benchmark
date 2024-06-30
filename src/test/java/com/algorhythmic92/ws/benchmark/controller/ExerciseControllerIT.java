package com.algorhythmic92.ws.benchmark.controller;

import com.algorhythmic92.ws.benchmark.dto.Exercise;
import com.algorhythmic92.ws.benchmark.repository.ExerciseRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@ActiveProfiles("IT")
@Sql(scripts = "/sql/schema.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class ExerciseControllerIT {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ExerciseRepository repository;

    private static final List<Exercise> ALL_EXERCISES = List.of(
        Exercise.builder()
                .id(1L)
                .name("Flat Bench Press")
                .variation("Barbell")
                .reps(10)
                .weight(185)
                .dateAchieved(LocalDate.of(2024, 6, 1))
                .build(),
        Exercise.builder()
                .id(2L)
                .name("Incline Chest Fly")
                .variation("Dumbbell")
                .reps(8)
                .weight(40)
                .dateAchieved(LocalDate.of(2024, 6, 2))
                .build(),
        Exercise.builder()
                .id(3L)
                .name("Low Rows")
                .variation("Cable")
                .reps(12)
                .weight(120)
                .dateAchieved(LocalDate.of(2024, 6, 3))
                .build()
    );

    @AfterEach
    void clearDB() {
        repository.deleteAll();
    }

    @Test
    public void getAllExercises() throws Exception {
        MvcResult result = mockMvc.perform(get("/exercise")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        List<Exercise> actualResultData = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {});
        assertEquals(200, result.getResponse().getStatus());
        assertTrue(actualResultData.containsAll(ALL_EXERCISES) && ALL_EXERCISES.containsAll(actualResultData));
    }
}
