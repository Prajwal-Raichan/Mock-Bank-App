package com.merin.cibilService.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TB_Cibil_Details")
@Entity
public class CibilEntity {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CIBIL_ID")
    private Long cibilId;

    @Column(name = "PAN_NUMBER", nullable = false, unique = true)
    private String panNumber;

    @Column(name = "CIBIL_SCORE", nullable = false)
    private int cibilScore;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "Last_Updated_Date", nullable = false)
    private LocalDate lastUpdatedDate;

    @Column(name = "Cibil_Status", nullable = false)
    private String cibilStatus;
}
