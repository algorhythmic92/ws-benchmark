package com.algorhythmic92.ws.benchmark.controller;

import com.algorhythmic92.ws.benchmark.enumeration.ExerciseVariation;
import com.algorhythmic92.ws.benchmark.repository.ExerciseRepository;
import com.algorhythmic92.ws.benchmark.util.JsonFileReader;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.algorhythmic92.ws.benchmark.dto.Exercise;
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
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@ActiveProfiles("IT")
@Transactional
@Sql(scripts = "/sql/schema.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class ExerciseControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ExerciseRepository repository;

    private static final Exercise FLAT_BENCH_PRESS = Exercise.builder()
            .id(1L)
            .name("Flat Bench Press")
            .variation(ExerciseVariation.BARBELL.getValue())
            .reps(10)
            .weight(185)
            .dateAchieved(LocalDate.parse("2024-06-01"))
            .build();
    private static final Exercise INCLINE_CHEST_FLY = Exercise.builder()
            .id(2L)
            .name("Incline Chest Fly")
            .variation(ExerciseVariation.DUMBBELL.getValue())
            .reps(8)
            .weight(40)
            .dateAchieved(LocalDate.parse("2024-06-02"))
            .build();
    private static final Exercise LOW_ROWS = Exercise.builder()
            .id(3L)
            .name("Low Rows")
            .variation(ExerciseVariation.CABLE.getValue())
            .reps(12)
            .weight(120)
            .build();
    private static final List<Exercise> INITIAL_EXERCISE_DB_STATE = List.of(
            FLAT_BENCH_PRESS, INCLINE_CHEST_FLY, LOW_ROWS);

    @Test
    void createExercise() throws Exception {
        Exercise expectedCreatedExercise = Exercise.builder()
                .id(4L)
                .name("Squat")
                .variation(ExerciseVariation.BARBELL.getValue())
                .reps(10)
                .weight(205)
                .dateAchieved(LocalDate.parse("2024-01-14"))
                .build();

        MvcResult actualResponse = mockMvc.perform(post("/exercise")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonFileReader.readJsonFileAsString(
                                "src/test/resources/request/createExercise.json")))
                .andExpect(status().isOk())
                .andReturn();

        assertEquals(expectedCreatedExercise, objectMapper.readValue(actualResponse.getResponse().getContentAsString(),
                Exercise.class));
        assertTrue(isExercisePresent(expectedCreatedExercise));
    }

    @Test
    void getExerciseById() throws Exception {
        MvcResult result = mockMvc.perform(get("/exercise/{id}", 1L)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        Exercise actualExercise = objectMapper.readValue(result.getResponse().getContentAsString(), Exercise.class);
        assertEquals(FLAT_BENCH_PRESS, actualExercise);
    }

    @Test
    public void getAllExercises() throws Exception {
        MvcResult result = mockMvc.perform(get("/exercise")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        List<Exercise> actualExercises = objectMapper.readValue(result.getResponse().getContentAsString(),
                new TypeReference<>() {});
        assertEquals(3, actualExercises.size());
    }

    @Test
    public void updateExercise() throws Exception {
        Exercise expectedExercise = Exercise.builder()
                .name("Incline Chest Fly")
                .variation(ExerciseVariation.DUMBBELL.getValue())
                .reps(12)
                .weight(70)
                .dateAchieved(LocalDate.parse("2024-11-20"))
                .build();

        MvcResult result = mockMvc.perform(put("/exercise/{id}", 2L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonFileReader.readJsonFileAsString("src/test/resources/request/updateExercise.json")))
                .andExpect(status().isOk())
                .andReturn();
        Exercise actualExercise = objectMapper.readValue(result.getResponse().getContentAsString(), Exercise.class);

        assertEquals(expectedExercise, actualExercise);
        assertTrue(isExercisePresent(expectedExercise));
    }

    @Test
    public void deleteExercise() throws Exception {
        mockMvc.perform(delete("/exercise/{id}", 1L))
                .andExpect(status().isNoContent());

        assertFalse(isExercisePresent(FLAT_BENCH_PRESS));
    }

    private boolean isExercisePresent(Exercise exerciseToCheck) {
        List<Exercise> allActualExercises = repository.findAll();
        Optional<Exercise> actualCreatedExercise = allActualExercises.stream()
                .filter(exercise -> exercise.getName().equalsIgnoreCase(exerciseToCheck.getName()) &&
                        exercise.getVariation().equals(exerciseToCheck.getVariation()) &&
                        Objects.equals(exercise.getReps(), exerciseToCheck.getReps()) &&
                        Objects.equals(exercise.getWeight(), exerciseToCheck.getWeight()) &&
                        exercise.getDateAchieved().toString()
                                .equalsIgnoreCase(exerciseToCheck.getDateAchieved().toString()))
                .findFirst();
        return actualCreatedExercise.isPresent();
    }
}
