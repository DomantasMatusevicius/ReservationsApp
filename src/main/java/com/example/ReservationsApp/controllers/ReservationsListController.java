package com.example.ReservationsApp.controllers;

import com.example.ReservationsApp.dao.ReservationsDAO;
import com.example.ReservationsApp.dao.SpecialistsDAO;
import com.example.ReservationsApp.data.Reservations;
import com.example.ReservationsApp.data.Specialist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
public class ReservationsListController {

    @Autowired
    SpecialistsDAO specialistsDAO;

    @Autowired
    ReservationsDAO reservationsDAO;

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

}
