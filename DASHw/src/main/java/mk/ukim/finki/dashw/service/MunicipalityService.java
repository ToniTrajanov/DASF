package mk.ukim.finki.dashw.service;

import mk.ukim.finki.dashw.model.Municipality;

import java.util.List;
public interface MunicipalityService {
    Municipality findByName(String name);
    List<Municipality> findAll();
}
