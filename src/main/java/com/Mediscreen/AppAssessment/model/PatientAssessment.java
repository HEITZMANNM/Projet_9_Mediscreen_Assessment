package com.Mediscreen.AppAssessment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.Date;
import java.util.Objects;

@Document(collection = "patientAssessment")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientAssessment {

    @Id
    private String id;

    private int patientId;

    private Date date;

    private String assessment;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PatientAssessment that = (PatientAssessment) o;
        return id == that.id && Objects.equals(patientId, that.patientId) && Objects.equals(date, that.date)
                && Objects.equals(assessment, that.assessment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, patientId, date, assessment);
    }
}
