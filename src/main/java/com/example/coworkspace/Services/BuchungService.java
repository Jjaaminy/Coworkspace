package com.example.coworkspace.Services;

import com.example.coworkspace.Model.Buchung;
import com.example.coworkspace.Model.Role;
import com.example.coworkspace.Model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuchungService {
    BuchungRepo repo;
    public BuchungService(BuchungRepo brepo) {
        this.repo = brepo;
    }

    public List<Buchung> getAllBookings() {
        return (List<Buchung>) repo.findAll();
    }
}
