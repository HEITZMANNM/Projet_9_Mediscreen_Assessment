package com.Mediscreen.AppAssessment.service;

import com.Mediscreen.AppAssessment.model.PatientAssessment;

import java.util.List;

public interface PatientAssessmentService {
    boolean addPatientAssessment(PatientAssessment patientAssessment);

    List<PatientAssessment> getAllAssessments();

    List<PatientAssessment> getAssessmentByPatientId(int patientId);

    PatientAssessment getAssessmentById(String id);

    List<PatientAssessment> getByStatus(String status);

    void upDateAssessment(PatientAssessment patientAssessment);

    void deleteAssessment(String id);
}
