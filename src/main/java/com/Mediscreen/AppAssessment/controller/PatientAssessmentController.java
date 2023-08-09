package com.Mediscreen.AppAssessment.controller;

import com.Mediscreen.AppAssessment.model.PatientAssessment;
import com.Mediscreen.AppAssessment.service.PatientAssessmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PatientAssessmentController {
    @Autowired
    private PatientAssessmentService patientAssessmentService;

    @PostMapping("/assessment/add")
    public ResponseEntity<HttpStatus> addNewAssessment(@RequestBody PatientAssessment patientAssessment){

        if (patientAssessmentService.addPatientAssessment(patientAssessment)){
            return new ResponseEntity<>(HttpStatus.CREATED);
        }

        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @PutMapping("/assessment/upDate")
    public void updateAssessment(@RequestBody PatientAssessment patientAssessmentToUpdate){

        patientAssessmentService.upDateAssessment(patientAssessmentToUpdate);
    }

    @GetMapping("/assessment/all")
    public List<PatientAssessment> getAllPatientAssessments(){

        return patientAssessmentService.getAllAssessments();
    }

    @GetMapping("/assessment/getById")
    public PatientAssessment getPatientAssessmentById(@RequestParam(name = "id") String id){

        return patientAssessmentService.getAssessmentById(id);
    }

    @GetMapping("/assessment/getByPatientId")
    public List<PatientAssessment> getPatientAssessmentByPatientId(@RequestParam(name = "patientId") int patientId){

        return patientAssessmentService.getAssessmentByPatientId(patientId);
    }


    @DeleteMapping("/assessment/delete")
    public void deleteAssessment(@RequestParam(name = "id") String id){

        patientAssessmentService.deleteAssessment(id);
    }
}
