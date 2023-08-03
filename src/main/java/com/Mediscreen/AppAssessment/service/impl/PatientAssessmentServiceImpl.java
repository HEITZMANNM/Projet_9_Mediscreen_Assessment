package com.Mediscreen.AppAssessment.service.impl;

import com.Mediscreen.AppAssessment.model.PatientAssessment;
import com.Mediscreen.AppAssessment.repository.PatientAssessmentRepository;
import com.Mediscreen.AppAssessment.service.PatientAssessmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class PatientAssessmentServiceImpl implements PatientAssessmentService {

    @Autowired
    private PatientAssessmentRepository patientAssessmentRepository;

    @Override
    public boolean addPatientAssessment(PatientAssessment patientAssessment){

        boolean answer = false;
        try{
            patientAssessmentRepository.save(patientAssessment);
            answer = true;
            log.info("the assessment was saved!");
        }
        catch (Exception ex){
            log.error("Error to save assessment");
        }
        return answer;
    }
    @Override
    public List<PatientAssessment> getAllAssessments(){

        List<PatientAssessment> list = new ArrayList<>();
        try{
            list =patientAssessmentRepository.findAll();
            log.info("The list of assessment was fetching!");
        }
        catch (Exception ex){
            log.error("Error fetching the list of assessments");
        }
        return list;
    }

    @Override
    public List<PatientAssessment> getAssessmentByPatientId(int patientId) {
        List<PatientAssessment> list = new ArrayList<>();
        try{
            list = patientAssessmentRepository.findByPatientId(patientId);
            log.info("The patient assessment's was find!");
        }
        catch (Exception ex){
            log.error("Error to get the Patient's assessments!");
        }

        return list;
    }

    @Override
    public PatientAssessment getAssessmentById(String id){

        PatientAssessment patientAssessment = new PatientAssessment();
        try{
            patientAssessment = patientAssessmentRepository.findById(id).get();
            log.info("The assessment was find!");
        }
        catch (Exception ex){
            log.error("Error to get the assessment!");
        }

        return patientAssessment;
    }

    @Override
    public List<PatientAssessment> getByStatus(String status){

        List<PatientAssessment> list = new ArrayList<>();
        try{
            list = patientAssessmentRepository.findByStatus(status);
            log.info("The list of assessments was fetch!");
        }
        catch (Exception ex){
            log.error("Error to fetch the list of assessments");
        }
        return list;
    }

    @Override
    public void upDateAssessment(PatientAssessment patientAssessment){

        try{
            //get the existing assessment from the DB
            PatientAssessment patientAssessmentExpected = patientAssessmentRepository.findById(patientAssessment.getId()).get();

            //populate new value from the assessment to existing assessment object
            patientAssessmentExpected.setAssessment(patientAssessment.getAssessment());
            patientAssessmentExpected.setStatus(patientAssessment.getStatus());

            patientAssessmentRepository.save(patientAssessmentExpected);
            log.info("The patient's assessment was update");
        }
        catch (Exception ex){
            log.error("Error to update the patient's assessment");
        }

    }

    @Override
    public void deleteAssessment(String id){

        try{
            patientAssessmentRepository.deleteById(id);
         log.info("The patient's assessment was deleted");
        }
        catch (Exception ex){
            log.error("Error to delete the patient's assessment!");
        }
    }
}
