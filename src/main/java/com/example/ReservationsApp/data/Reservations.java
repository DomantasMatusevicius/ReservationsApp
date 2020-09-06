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
    @Column(name = "reservation_started")
    private Boolean reservationStarted;
    @Column(name = "reservation_ended")
    private Boolean reservationEnded;


    public Reservations() {
    }

    public Reservations(Specialist specialist, Date visitDate, String reservationCode, Boolean reservationStarted, Boolean reservationEnded) {
        this.specialist = specialist;
        this.visitDate = visitDate;
        this.reservationCode = reservationCode;
        this.reservationStarted = reservationStarted;
        this.reservationEnded = reservationEnded;
    }

    public Boolean getReservationStarted() {
        return reservationStarted;
    }

    public void setReservationStarted(Boolean reservationStarted) {
        this.reservationStarted = reservationStarted;
    }

    public Boolean getReservationEnded() {
        return reservationEnded;
    }

    public void setReservationEnded(Boolean reservationEnded) {
        this.reservationEnded = reservationEnded;
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
                "specialist=" + specialist +
                ", visitDate=" + visitDate +
                ", reservationCode='" + reservationCode + '\'' +
                ", reservationStarted=" + reservationStarted +
                ", reservationEnded=" + reservationEnded +
                '}';
    }
}
