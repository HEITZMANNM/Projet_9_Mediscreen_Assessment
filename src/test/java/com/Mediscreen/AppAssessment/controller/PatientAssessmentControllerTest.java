package com.Mediscreen.AppAssessment.controller;

import com.Mediscreen.AppAssessment.model.PatientAssessment;
import com.Mediscreen.AppAssessment.service.PatientAssessmentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Date;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PatientAssessmentControllerTest {

    @Autowired
    PatientAssessmentService patientAssessmentService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp()
    {
        PatientAssessment patientAssessment = new PatientAssessment();
        patientAssessment.setPatientId(0);
        patientAssessment.setDate(new Date());
        patientAssessment.setAssessment("assessmentTest");

        patientAssessmentService.addPatientAssessment(patientAssessment);
    }

   @AfterEach
    public void cleanDB(){
       List<PatientAssessment> patientAssessmentExpected = patientAssessmentService.getAssessmentByPatientId(0);
       patientAssessmentService.deleteAssessment(patientAssessmentExpected.get(0).getId());
    }

    @Test
    public void testToGetAllAssessments() throws Exception {

        this.mockMvc.perform(get("/assessment/all"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isNotEmpty());
    }

    @Test
    public void testToGetAssessmentById() throws Exception {

        PatientAssessment patientAssessmentExpected = patientAssessmentService.getAssessmentByPatientId(0).get(0);

        this.mockMvc.perform(get("/assessment/getById").param("id", patientAssessmentExpected.getId()))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.assessment").value("assessmentTest"));
    }

    @Test
    public void testToGetAssessmentByPatientId() throws Exception {


        this.mockMvc.perform(get("/assessment/getByPatientId").param("patientId", String.valueOf(0)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].assessment").value("assessmentTest"));
    }

    @Test
    public void testToDSaveNewAssessment() throws Exception {

        PatientAssessment patientAssessment = new PatientAssessment();
        patientAssessment.setPatientId(0);
        patientAssessment.setDate(new Date());
        patientAssessment.setAssessment("assessmentTestToSave");

        this.mockMvc.perform(post("/assessment/add").content(asJsonString(patientAssessment))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());


       PatientAssessment patientAssessmentExpected = patientAssessmentService.getAssessmentByPatientId(0).get(1);
        patientAssessmentService.deleteAssessment(patientAssessmentExpected.getId());
    }

    @Test
    public void testToUpDateAssessment() throws Exception {


        PatientAssessment patientAssessment = patientAssessmentService.getAssessmentByPatientId(0).get(0);
        patientAssessment.setAssessment("assessmentUpDated");

        this.mockMvc.perform(put("/assessment/upDate").content(asJsonString(patientAssessment))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testToDeleteAssessment() throws Exception {

        PatientAssessment patientAssessment = new PatientAssessment();
        patientAssessment.setPatientId(0);
        patientAssessment.setDate(new Date());
        patientAssessment.setAssessment("assessmentTestToDelete");

        patientAssessmentService.addPatientAssessment(patientAssessment);

        PatientAssessment patientAssessmentToDelete = patientAssessmentService.getAssessmentByPatientId(0).get(1);

        this.mockMvc.perform(delete("/assessment/delete").param("id", String.valueOf(patientAssessmentToDelete.getId())))
                .andExpect(status().isOk());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
