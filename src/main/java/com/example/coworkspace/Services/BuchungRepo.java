package com.example.coworkspace.Services;

import com.example.coworkspace.Model.Buchung;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface BuchungRepo extends CrudRepository<Buchung,Long> {
}
