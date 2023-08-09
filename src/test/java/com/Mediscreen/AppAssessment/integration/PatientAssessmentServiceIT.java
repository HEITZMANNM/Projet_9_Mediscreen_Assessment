package com.Mediscreen.AppAssessment.integration;

import com.Mediscreen.AppAssessment.model.PatientAssessment;
import com.Mediscreen.AppAssessment.service.PatientAssessmentService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@SpringBootTest
public class PatientAssessmentServiceIT {

    @Autowired
    PatientAssessmentService patientAssessmentService;

    @BeforeEach
    public void setUp(){

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
    public void testToSaveAssessment(){

        PatientAssessment patientAssessment = new PatientAssessment();
        patientAssessment.setPatientId(0);
        patientAssessment.setDate(new Date());
        patientAssessment.setAssessment("assessmentTestToSave");

        patientAssessmentService.addPatientAssessment(patientAssessment);

        List<PatientAssessment> patientAssessmentExpected = patientAssessmentService.getAssessmentByPatientId(0);

        assertEquals("assessmentTestToSave", patientAssessmentExpected.get(1).getAssessment());

        patientAssessmentService.deleteAssessment(patientAssessmentExpected.get(1).getId());
    }

    @Test
    public void testToUpDateAssessment(){


       PatientAssessment patientAssessmentExpected = patientAssessmentService.getAssessmentByPatientId(0).get(0);

       patientAssessmentExpected.setAssessment("assessmentUpDated");

       patientAssessmentService.upDateAssessment(patientAssessmentExpected);

        PatientAssessment patientAssessmentExpectedAfterUpdate = patientAssessmentService.getAssessmentByPatientId(0).get(0);

        assertEquals("assessmentUpDated", patientAssessmentExpectedAfterUpdate.getAssessment());

    }
    @Test
    public void testToDeleteAssessment(){


        PatientAssessment patientAssessment = new PatientAssessment();
        patientAssessment.setPatientId(-1);
        patientAssessment.setDate(new Date());
        patientAssessment.setAssessment("assessmentTestToDelete");

        patientAssessmentService.addPatientAssessment(patientAssessment);

        PatientAssessment patientAssessmentExpected = patientAssessmentService.getAssessmentByPatientId(-1).get(0);

        patientAssessmentService.deleteAssessment(patientAssessmentExpected.getId());

        List<PatientAssessment> patientAssessmentsExpectedAfterDelete = patientAssessmentService.getAssessmentByPatientId(-1);

        assertEquals(0, patientAssessmentsExpectedAfterDelete.size());

    }
}
