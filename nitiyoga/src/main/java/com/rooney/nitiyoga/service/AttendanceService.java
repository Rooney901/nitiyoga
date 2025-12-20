package com.rooney.nitiyoga.service;

import java.time.LocalDate;
import java.util.List;

import com.rooney.nitiyoga.entity.User;
import com.rooney.nitiyoga.enums.Admins;

public interface AttendanceService {

    void markAttendance(LocalDate date, List<Long> presentUserIds, Admins adminUser);

    List<User> searchUsers(String userName);

    List<User> suggestUsers(String keyword);

}

