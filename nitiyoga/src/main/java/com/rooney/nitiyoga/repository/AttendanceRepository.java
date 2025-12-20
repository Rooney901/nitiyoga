package com.rooney.nitiyoga.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rooney.nitiyoga.entity.Attendance;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    Optional<Attendance> findByUserIdAndAttendanceDate(
            Long userId, LocalDate attendanceDate);

    List<Attendance> findByAttendanceDate(LocalDate attendanceDate);
    

}
