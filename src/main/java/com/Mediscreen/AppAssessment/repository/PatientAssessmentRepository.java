package com.Mediscreen.AppAssessment.repository;

import com.Mediscreen.AppAssessment.model.PatientAssessment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientAssessmentRepository extends MongoRepository<PatientAssessment, String> {


    List<PatientAssessment> findByPatientId(int patientId);

}
