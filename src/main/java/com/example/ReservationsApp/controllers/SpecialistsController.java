package com.example.ReservationsApp.controllers;

import com.example.ReservationsApp.dao.ReservationsDAO;
import com.example.ReservationsApp.dao.SpecialistsDAO;
import com.example.ReservationsApp.data.Reservations;
import com.example.ReservationsApp.data.Specialist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Comparator;
import java.util.List;

@Controller
public class SpecialistsController {

    @Autowired
    SpecialistsDAO specialistsDAO;

    @Autowired
    ReservationsDAO reservationsDAO;

    @RequestMapping("checkVisits")
    public String checkVisits(
            @RequestParam(name = "specialistId") Integer specialistId
    ) {
        return "redirect:/reservations?specialistId=" + specialistId;
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/reservations")
    public ModelAndView reservations(@RequestParam(name = "specialistId") Integer specialistId) {
        ModelAndView mav = new ModelAndView("reservations");
        Specialist specialist = specialistsDAO.getOne(specialistId);
        specialist.getReservationsList().sort(Comparator.comparing(Reservations::getVisitDate));
        if (specialist != null) {
            mav.addObject("specialist", specialist);
            mav.addObject("list", specialist.getReservationsList());
            return mav;
        } else {
            return new ModelAndView("redirect:/");
        }
    }

    @RequestMapping("startVisit")
    public String startVisit(
            @RequestParam(name = "reservationCode") String reservationCode
    ) {
        Reservations reservation = reservationsDAO.getOne(reservationCode);
        Integer specialistId = reservation.getSpecialist().getId();

        List<Reservations> list = reservationsDAO.findAll();
        int counter = 0;
        for (Reservations reservations : list) {
            if (reservations.getReservationStarted() == true) {
                counter = 1;
            }
        }
        System.out.println(counter);
        if (counter == 0) {
            reservation.setReservationStarted(true);
            reservationsDAO.save(reservation);
        }
        return "redirect:/reservations?specialistId=" + specialistId;
    }

    @RequestMapping("endVisit")
    public String endVisit(
            @RequestParam(name = "reservationCode") String reservationCode
    ) {
        Reservations reservation = reservationsDAO.getOne(reservationCode);
        Integer specialistId = reservation.getSpecialist().getId();

        reservation.setReservationStarted(false);
        reservation.setReservationEnded(true);
        reservationsDAO.deleteById(reservation.getReservationCode());

        return "redirect:/reservations?specialistId=" + specialistId;
    }

}
