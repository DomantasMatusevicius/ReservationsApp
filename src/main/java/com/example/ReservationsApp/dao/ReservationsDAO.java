package com.example.ReservationsApp.dao;

import com.example.ReservationsApp.data.Reservations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReservationsDAO extends JpaRepository<Reservations, String> {

}
