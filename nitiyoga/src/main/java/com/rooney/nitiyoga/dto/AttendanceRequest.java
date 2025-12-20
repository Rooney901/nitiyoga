package com.rooney.nitiyoga.dto;

import java.time.LocalDate;
import java.util.List;

import com.rooney.nitiyoga.enums.Admins;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttendanceRequest {

    private LocalDate date;

    private List<Long> presentUserIds;

    private Admins adminUser; 
}

