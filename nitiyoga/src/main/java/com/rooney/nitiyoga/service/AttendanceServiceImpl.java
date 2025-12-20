package com.rooney.nitiyoga.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.rooney.nitiyoga.entity.Attendance;
import com.rooney.nitiyoga.entity.User;
import com.rooney.nitiyoga.enums.Admins;
import com.rooney.nitiyoga.enums.AttendanceStatus;
import com.rooney.nitiyoga.repository.AttendanceRepository;
import com.rooney.nitiyoga.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final UserRepository userRepository;

    @Transactional
    public void markAttendance(
        LocalDate date,
        List<Long> presentUserIds,
        Admins adminUser) {

    List<User> users = userRepository.findAllById(presentUserIds);

    for (User user : users) {

        // Idempotent check
        boolean alreadyMarked =
            attendanceRepository
                .findByUserIdAndAttendanceDate(user.getId(), date)
                .isPresent();

        if (alreadyMarked) {
            continue; // DO NOTHING
        }

        Attendance attendance = new Attendance();
        attendance.setUser(user);
        attendance.setAttendanceDate(date);
        attendance.setStatus(AttendanceStatus.PRESENT);
        attendance.setMarkedAt(LocalDateTime.now());
        attendance.setMarkedBy(adminUser);

        attendanceRepository.save(attendance);
    }
}


    @Override
    public List<User> searchUsers(String userName) {
        return userRepository.findByUserNameContainingIgnoreCase(userName);
    }

    @Override
    public List<User> suggestUsers(String keyword) {
    return userRepository
        .findTop10ByUserNameContainingIgnoreCase(keyword);
}

}

