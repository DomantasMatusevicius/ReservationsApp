package com.example.ReservationsApp.data;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "reservations")
@NamedQueries({
        @NamedQuery(name = "Reservations.findAll", query = "SELECT t FROM Reservations t")})
public class Reservations implements Serializable {

    @JoinColumn(name = "specialist_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Specialist specialist;
    @Column(name = "visit_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    private Date visitDate;
    @Id
    @Column(name = "reservation_code")
    private String reservationCode;

    public Reservations() {
    }

    public Reservations(Specialist specialist, Date visitDate, String reservationCode) {
        this.specialist = specialist;
        this.visitDate = visitDate;
        this.reservationCode = reservationCode;
    }

    public String getReservationCode() {
        return reservationCode;
    }

    public void setReservationCode(String reservationCode) {
        this.reservationCode = reservationCode;
    }

    public Specialist getSpecialist() {
        return specialist;
    }

    public void setSpecialist(Specialist specialist) {
        this.specialist = specialist;
    }

    public Date getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
    }

    @Override
    public String toString() {
        return "Reservations{" +
                "specialist=" + specialist.toString() +
                ", visitDate=" + visitDate +
                ", reservationCode='" + reservationCode + '\'' +
                '}';
    }
}
