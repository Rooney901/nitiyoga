package com.rooney.nitiyoga.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rooney.nitiyoga.entity.User;
import com.rooney.nitiyoga.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findUserById(Long id) {
        if (id == null) {
            return null;
        }
        return userRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public User createUser(User user) {
        if (user == null) {
            return null;
        }
        if (user.getCreatedAt() == null) {
            user.setCreatedAt(LocalDateTime.now());
        }
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long id, User user) {
        if (id == null || user == null) {
            return null;
        }
        Optional<User> existingOpt = userRepository.findById(id);
        if (!existingOpt.isPresent()) {
            return null;
        }
        User existing = existingOpt.get();

        if (user.getEmail() != null) {
            existing.setEmail(user.getEmail());
        }
        if (user.getUserName() != null) {
            existing.setUserName(user.getUserName());
        }
        if (user.getContactNumber() != null) {
            existing.setContactNumber(user.getContactNumber());
        }
        if (user.getDateOfBirth() != null) {
            existing.setDateOfBirth(user.getDateOfBirth());
        }
        if (user.getGender() != null) {
            existing.setGender(user.getGender());
        }
        if (user.getMedicalIssues() != null) {
            existing.setMedicalIssues(user.getMedicalIssues());
        }
        if (user.getFeeStatus() != null) {
            existing.setFeeStatus(user.getFeeStatus());
        }
        // boolean primitive - update explicitly
        existing.setActive(user.getActive());

        return userRepository.save(existing);
    }

    @Override
    public void deleteUser(Long id) {
        if (id == null) {
            return;
        }
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        }
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<User> findUserByUserName(String userName) {
        if (userName == null) {
            return List.of();
        }
        return userRepository.searchByUserName(userName);
    }

}
