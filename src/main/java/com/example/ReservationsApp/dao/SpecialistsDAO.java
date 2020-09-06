package com.example.ReservationsApp.dao;

import com.example.ReservationsApp.data.Specialist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecialistsDAO extends JpaRepository<Specialist, Integer> {

}
