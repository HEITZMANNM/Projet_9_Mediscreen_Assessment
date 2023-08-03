package com.Mediscreen.AppAssessment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.Date;

@Document(collection = "patientAssessment")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientAssessment {

    @Id
    private String id;

    private int patientId;

    private Date date;

    private String status;

    private String assessment;
}
