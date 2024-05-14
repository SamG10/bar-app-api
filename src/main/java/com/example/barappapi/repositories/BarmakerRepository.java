package com.example.barappapi.repositories;

import com.example.barappapi.models.Barmaker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BarmakerRepository extends JpaRepository<Barmaker, String> {
    Optional<Barmaker> findByEmail(String email);
}
