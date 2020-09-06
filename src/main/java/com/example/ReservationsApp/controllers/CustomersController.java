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

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

@Controller
public class CustomersController {

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
        value.setReservationStarted(false);
        value.setReservationEnded(false);

        reservationsDAO.save(value);
        return "redirect:/registrationCompleted?genCode=" + genCode;
    }


    @RequestMapping("checkReservation")
    public String checkReservation(
            @RequestParam(name = "genCode") String genCode
    ) {
        return "redirect:/registrationCompleted?genCode=" + genCode;
    }

    @GetMapping("registrationCompleted")
    public ModelAndView registrationCompleted(@RequestParam(name = "genCode") String genCode) {
        ModelAndView mav = new ModelAndView("registrationCompleted");
        Reservations value = reservationsDAO.getOne(genCode);
        Specialist waitingLine = specialistsDAO.getOne(value.getSpecialist().getId());
        waitingLine.getReservationsList().sort(Comparator.comparing(Reservations::getVisitDate));

        int inLine = waitingLine.getReservationsList().indexOf(value);

        Date date = new Date();
        long different = value.getVisitDate().getTime() - date.getTime();

        mav.addObject("value", value);
        mav.addObject("different", different);
        mav.addObject("inLine", inLine);

        return mav;
    }

    @RequestMapping(value = "reservationCancel", method = {RequestMethod.GET, RequestMethod.POST})
    public String reservationCancel(@RequestParam(name = "reservationCode") String reservationCode) {
        reservationsDAO.deleteById(reservationCode);
        return "redirect:/reservationCancelled";
    }

    @GetMapping("/reservationCancelled")
    public ModelAndView reservationCancelled() {
        ModelAndView mav = new ModelAndView("reservationCancelled");
        return mav;
    }

}
