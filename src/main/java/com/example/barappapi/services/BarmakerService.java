package com.example.barappapi.services;

import com.example.barappapi.models.Barmaker;
import com.example.barappapi.repositories.BarmakerRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class BarmakerService {
    @Autowired
    private BarmakerRepository barmakerRepository;

    public Barmaker getCurrentBarmaker() {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        return barmakerRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("Barmaker not found with email: " + userEmail));
    }
}
