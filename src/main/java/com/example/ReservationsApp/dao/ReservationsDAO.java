package com.example.ReservationsApp.dao;

import com.example.ReservationsApp.data.Reservations;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationsDAO extends JpaRepository<Reservations, String> {

}
