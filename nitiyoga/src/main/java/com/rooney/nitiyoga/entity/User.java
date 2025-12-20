package com.rooney.nitiyoga.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.rooney.nitiyoga.enums.ActiveStatus;
import com.rooney.nitiyoga.enums.FeeStatus;
import com.rooney.nitiyoga.enums.Gender;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NonNull
    private String email;

    private String userName;

    private String contactNumber;

    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String medicalIssues;

    @Enumerated(EnumType.STRING)
    private FeeStatus feeStatus;

    @Enumerated(EnumType.STRING)
    private ActiveStatus active;

    private LocalDateTime createdAt;
}

