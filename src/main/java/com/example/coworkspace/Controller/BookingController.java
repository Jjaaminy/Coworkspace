package com.example.coworkspace.Controller;

import com.example.coworkspace.Model.Buchung;
import com.example.coworkspace.Services.BuchungRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("/bookings")
public class BookingController {

    private BuchungRepo brepo;
    private ArrayList<Buchung> blist;

    @Autowired
    public BookingController(BuchungRepo repo) {
        this.brepo = repo;
    }

    @PostMapping
    public ResponseEntity<Buchung> createBooking(@RequestBody Buchung booking) {
        Buchung savedBooking = brepo.save(booking);
        return ResponseEntity.ok(savedBooking);
    }

    @GetMapping
    public ResponseEntity<List<Buchung>> getAllBookings() {
        List<Buchung> bookings = (List<Buchung>) brepo.findAll();
        return ResponseEntity.ok(bookings);
    }
}
