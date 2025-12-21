package com.rooney.nitiyoga.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rooney.nitiyoga.dto.AttendanceRequest;
import com.rooney.nitiyoga.entity.User;
import com.rooney.nitiyoga.enums.Admins;
import com.rooney.nitiyoga.repository.UserRepository;
import com.rooney.nitiyoga.service.AttendanceService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/attendance")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;
    private final UserRepository userRepository;

    // Search users by username
    @GetMapping("/users")
    public List<User> searchUsers(
            @RequestParam String userName) {
        return attendanceService.searchUsers(userName);
    }

    // Mark attendance
    @PostMapping("/mark")
    public ResponseEntity<?> markAttendance(
        @RequestBody AttendanceRequest request ){

      Admins adminUser = request.getAdminUser();

    attendanceService.markAttendance(
        request.getDate(),
        request.getPresentUserIds(),
        adminUser);

    return ResponseEntity.ok("Attendance saved");
    }

    @GetMapping("/users/suggest")
    public List<User> suggestUsers(
        @RequestParam String keyword) {

    return userRepository
        .findTop10ByUserNameContainingIgnoreCase(keyword);
}


}

