package com.Mediscreen.AppAssessment.controller;

import com.Mediscreen.AppAssessment.model.PatientAssessment;
import com.Mediscreen.AppAssessment.service.PatientAssessmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
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
    public ResponseEntity updateAssessment(@RequestBody PatientAssessment patientAssessmentToUpdate){

        PatientAssessment patientAssessmentExpected = patientAssessmentService.getAssessmentById(patientAssessmentToUpdate.getId());

        if(patientAssessmentExpected.getAssessment() != null)
        {
            patientAssessmentService.upDateAssessment(patientAssessmentToUpdate);
            return  new ResponseEntity(HttpStatus.OK);
        }
        else{
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/assessment/all")
    public ResponseEntity getAllPatientAssessments(){

        List<PatientAssessment> list = patientAssessmentService.getAllAssessments();
        if (list.size() != 0)
        {
            return new ResponseEntity(list, HttpStatus.OK);
        }
        else{
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/assessment/getById")
    public ResponseEntity getPatientAssessmentById(@RequestParam(name = "id") String id){

        PatientAssessment patientAssessment = patientAssessmentService.getAssessmentById(id);

        if(patientAssessment.getAssessment() != null)
        {
            return new ResponseEntity<>(patientAssessment, HttpStatus.OK);
        }
       else {
           return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/assessment/getByPatientId")
    public ResponseEntity getPatientAssessmentByPatientId(@RequestParam(name = "patientId") int patientId){

        List<PatientAssessment> list = patientAssessmentService.getAssessmentByPatientId(patientId);

        if(list.size() != 0)
        {
            return new ResponseEntity(list, HttpStatus.OK);
        }
        else{
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/assessment/delete")
    public ResponseEntity deleteAssessment(@RequestParam(name = "id") String id){

        PatientAssessment patientAssessment = patientAssessmentService.getAssessmentById(id);

        if (patientAssessment != null)
        {
            patientAssessmentService.deleteAssessment(id);
            return new ResponseEntity(HttpStatus.OK);
        }
        else{
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
