package mk.ukim.finki.dashw.service.impl;

import mk.ukim.finki.dashw.model.Municipality;
import mk.ukim.finki.dashw.repository.MunicipalityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MunicipalityService implements mk.ukim.finki.dashw.service.MunicipalityService {
    private final MunicipalityRepository municipalityRepository;

    public MunicipalityService(MunicipalityRepository municipalityRepository) {
        this.municipalityRepository = municipalityRepository;
    }

    @Override
    public Municipality findByName(String name) {
        return this.municipalityRepository.findByName(name);
    }

    @Override
    public List<Municipality> findAll() {
        return this.municipalityRepository.findAll();
    }
}
