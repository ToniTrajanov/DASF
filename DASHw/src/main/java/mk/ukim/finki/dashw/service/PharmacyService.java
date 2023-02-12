package mk.ukim.finki.dashw.service;

import mk.ukim.finki.dashw.model.Hospital;
import mk.ukim.finki.dashw.model.Pharmacy;

import java.io.IOException;
import java.util.List;

public interface PharmacyService {
    List<Pharmacy> findAll();
    List<Pharmacy> addPharmacy(String pharmaciesUrl) throws IOException;
    List<Pharmacy> findAllByMunicipality(String municipality);
    Pharmacy incrementSearchCount(String searchInput);
    List<Pharmacy> findMostSearchedHospitals();
    Pharmacy findById(Long id);
}
