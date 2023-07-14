package com.example.coworkspace.Controller;

import com.example.coworkspace.Model.Buchung;
import com.example.coworkspace.Model.Role;
import com.example.coworkspace.Model.User;
import com.example.coworkspace.Services.BuchungRepo;
import com.example.coworkspace.Services.BuchungService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("/bookings")
public class BookingController {

    private BuchungRepo brepo;
    private BuchungService service;
    public  ArrayList<Buchung> bookingreq;
    @Autowired
    public BookingController(BuchungRepo repo) {
        this.brepo = repo;
    }


    //MItglied kann Buchung erstellen
    @PostMapping
    public ResponseEntity<Buchung> createBooking(@RequestBody Buchung booking,User user) {
        Role.RoleType userRole = user.getRole().getName();

        if (userRole == Role.RoleType.REGULAR_USER) {
            Buchung savedBooking = brepo.save(booking);
            bookingreq.add(booking);
            return ResponseEntity.ok(savedBooking);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    //Admin kann Buchung akzeptieren oder ablehnen
    @PostMapping("/bookings/buchungstate")
    public ResponseEntity<Buchung> bookingstate(@RequestBody Buchung booking, User user) {
        Role.RoleType userRole = user.getRole().getName();
        if (userRole == Role.RoleType.ADMIN) {
            if (booking.getState() != null && booking.getState()) {
                booking.setState(true);
            } else {
                booking.setState(false);
            }
            Buchung savedBooking = brepo.save(booking);
            bookingreq.add(booking);
            return ResponseEntity.ok(savedBooking);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }
    //alle buchungen bekommen
    @GetMapping("/bookings")
    public ResponseEntity<List<Buchung>> getAllBookings() {
        List<Buchung> bookings =service.getAllBookings();
        return ResponseEntity.ok(bookings);
    }







}
