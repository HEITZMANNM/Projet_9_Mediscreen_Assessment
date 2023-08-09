package com.Mediscreen.AppAssessment;

import com.Mediscreen.AppAssessment.model.PatientAssessment;
import com.Mediscreen.AppAssessment.repository.PatientAssessmentRepository;
import com.Mediscreen.AppAssessment.service.PatientAssessmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PatientAssessmentServiceTest {

    @Autowired
    PatientAssessmentService patientAssessmentService;

    @MockBean
    PatientAssessmentRepository patientAssessmentRepository;

    @BeforeEach
    public void setUp(){

        PatientAssessment patientAssessment = new PatientAssessment();
        patientAssessment.setId("idtest");
        patientAssessment.setPatientId(1);
        patientAssessment.setDate(new Date());
        patientAssessment.setAssessment("assessmentTest");

        PatientAssessment patientAssessment2 = new PatientAssessment();
        patientAssessment2.setId("idtest2");
        patientAssessment2.setPatientId(2);
        patientAssessment2.setDate(new Date());
        patientAssessment2.setAssessment("assessmentTest2");

        List<PatientAssessment> listOfAllAssessments = new ArrayList<>();
        listOfAllAssessments.add(patientAssessment);
        listOfAllAssessments.add(patientAssessment2);

        List<PatientAssessment> listByPatientId = new ArrayList<>();
        listByPatientId.add(patientAssessment);

        when(patientAssessmentRepository.findAll()).thenReturn(listOfAllAssessments);
        when(patientAssessmentRepository.findByPatientId(anyInt())).thenReturn(listByPatientId);
        when(patientAssessmentRepository.findById(anyString())).thenReturn(Optional.of(patientAssessment));

    }

    @Test
    public void testToGetAllAssessments(){

        List<PatientAssessment> listOfAllAssessments = patientAssessmentService.getAllAssessments();

        assertEquals(2, listOfAllAssessments.size());
    }

    @Test
    public void TestToGetAssessmentsByPatientId(){

        List<PatientAssessment> list = patientAssessmentService.getAssessmentByPatientId(2);

        assertEquals(1, list.get(0).getPatientId());
        assertEquals("assessmentTest", list.get(0).getAssessment());
    }

    @Test
    public void TestToGetAssessmentById(){

        PatientAssessment patientAssessment = patientAssessmentService.getAssessmentById("idtest");

        assertEquals(1, patientAssessment.getPatientId());
        assertEquals("assessmentTest", patientAssessment.getAssessment());
    }


}
