package com.example.ReservationsApp.controllers;

import com.example.ReservationsApp.dao.ReservationsDAO;
import com.example.ReservationsApp.dao.SpecialistsDAO;
import com.example.ReservationsApp.data.Reservations;
import com.example.ReservationsApp.data.Specialist;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@Controller
public class ReservationsController {

    @Autowired
    SpecialistsDAO specialistsDAO;

    @Autowired
    ReservationsDAO reservationsDAO;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }

    @GetMapping("/")
    public ModelAndView specialistsList() {
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("list", specialistsDAO.findAll());
        return mav;
    }

    @GetMapping("/makeReservation")
    public ModelAndView makeReservation(@RequestParam(name = "specialistId", required = false) Integer specialistId) {
        ModelAndView mav = new ModelAndView("makeReservation");
        if (specialistId != null) {
            Specialist value = specialistsDAO.getOne(specialistId);
            mav.addObject("value", value);
        }
        return mav;
    }

    @PostMapping("saveReservation")
    public String saveReservation(
            @RequestParam(name = "id", required = false) Integer id,
            @RequestParam(name = "visit_date") Date visitDate,
            @RequestParam(name = "specialistId", required = false) Integer specialistId
    ) {
        Reservations value = new Reservations();
        Specialist specialist = specialistsDAO.getOne(specialistId);
        String genCode = RandomStringUtils.randomAlphanumeric(4);

        value.setSpecialist(specialist);
        value.setVisitDate(visitDate);
        value.setReservationCode(genCode);

        reservationsDAO.save(value);
        return "redirect:/registrationCompleted?genCode=" + genCode;
    }

    @GetMapping("registrationCompleted")
    public ModelAndView registrationCompleted(@RequestParam(name = "genCode") String genCode) {
        ModelAndView mav = new ModelAndView("registrationCompleted");
        Reservations value = reservationsDAO.getOne(genCode);

        Date date = new Date();
        long different  = value.getVisitDate().getTime() - date.getTime();

        mav.addObject("value", value);
        mav.addObject("different", different);

        return mav;
    }

    @RequestMapping(value = "reservationCancel", method = {RequestMethod.GET, RequestMethod.POST})
    public String reservationCancel(@RequestParam(name = "reservationCode") String reservationCode) {
        reservationsDAO.deleteById(reservationCode);
        return "redirect:/";
    }

}
