package com.Mediscreen.AppAssessment.model;

import org.junit.jupiter.api.Test;

import java.util.Date;

import javax.persistence.*;

import static org.junit.jupiter.api.Assertions.*;

public class PatientAssessmentModelTest {

    @Test
    public void testHashCode()
    {
        PatientAssessment patientAssessment = new PatientAssessment();
        patientAssessment.setPatientId(0);
        patientAssessment.setDate(new Date());
        patientAssessment.setAssessment("assessmentTest");

        assertNotNull(patientAssessment.hashCode());
    }

    @Test
    public void testEquals()
    {
        PatientAssessment patientAssessment = new PatientAssessment();
        patientAssessment.setPatientId(0);
        patientAssessment.setDate(new Date());
        patientAssessment.setAssessment("assessmentTest");

        PatientAssessment patientAssessment2 = new PatientAssessment();
        patientAssessment2.setPatientId(0);
        patientAssessment2.setDate(new Date());
        patientAssessment2.setAssessment("assessmentTest");

        PatientAssessment patientAssessment3 = new PatientAssessment();
        patientAssessment3.setPatientId(0);
        patientAssessment3.setDate(new Date());
        patientAssessment3.setAssessment("assessmentTest3");


        assertTrue(patientAssessment.equals(patientAssessment));

        assertTrue(patientAssessment.equals(patientAssessment2));

        patientAssessment2 = null;

        assertFalse(patientAssessment.equals(patientAssessment2));

        assertFalse((new PatientAssessment().equals(patientAssessment)));

        assertFalse(patientAssessment.equals(patientAssessment3));

        patientAssessment3.setAssessment("assessmentTest");

        patientAssessment3.setPatientId(11);

        assertFalse(patientAssessment.equals(patientAssessment3));
    }

    @Test
    public void testToString()
    {
        PatientAssessment patientAssessment = new PatientAssessment();
        patientAssessment.setPatientId(0);
        patientAssessment.setDate(new Date());
        patientAssessment.setAssessment("assessmentTest");

        assertNotNull(patientAssessment.toString());
    }
}
