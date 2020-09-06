package com.example.ReservationsApp.data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "specialists")
@NamedQueries({
        @NamedQuery(name = "Specialist.findAll", query = "SELECT t FROM Specialist t")})
public class Specialist implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "specialist")
    private List<Reservations> reservationsList;

    public Specialist() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Reservations> getReservationsList() {
        return reservationsList;
    }

    public void setReservationsList(List<Reservations> reservationsList) {
        this.reservationsList = reservationsList;
    }

    public Specialist(Integer id, String name, List<Reservations> reservationsList) {
        this.id = id;
        this.name = name;
        this.reservationsList = reservationsList;
    }
}
