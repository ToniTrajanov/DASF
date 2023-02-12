package mk.ukim.finki.dashw.service.impl;


import mk.ukim.finki.dashw.model.Hospital;
import mk.ukim.finki.dashw.model.Pharmacy;
import mk.ukim.finki.dashw.model.exceptions.ProblemSolvingError;
import mk.ukim.finki.dashw.repository.PharmacyRepository;
import mk.ukim.finki.dashw.service.PharmacyService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class PharmacyServiceImpl implements PharmacyService {

    private final PharmacyRepository pharmacyRepository;

    public PharmacyServiceImpl(PharmacyRepository pharmacyRepository) {
        this.pharmacyRepository = pharmacyRepository;
    }

    @Override
    public List<Pharmacy> findAll() {
        return this.pharmacyRepository.findAll();
    }

    @Override
    public List<Pharmacy> addPharmacy(String pharmaciesUrl) throws IOException {
        //"F:\\DasHomework\\pharmacies_data.xlsx"
        Workbook workbook = new XSSFWorkbook(pharmaciesUrl);
        List<Pharmacy> pharmacies = new ArrayList<>();
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rows = sheet.iterator();

        int rowNumber = 0;
        while (rows.hasNext()) {
            Row currentRow = rows.next();
            if (rowNumber == 0) {
                rowNumber++;
                continue;
            }


            Iterator<Cell> cellsInRow = currentRow.iterator();
            Pharmacy pharmacy = new Pharmacy();

            int cellIdx = 0;
            while (cellsInRow.hasNext()) {
                Cell currentCell = cellsInRow.next();

                // each cell case
                switch (cellIdx) {
                    case 0 -> pharmacy.setName(currentCell.getStringCellValue());
                    case 1 -> pharmacy.setRating(Double.parseDouble(currentCell.getStringCellValue()));
                    case 2 -> pharmacy.setNumberOfReviews((long)currentCell.getNumericCellValue());
                    case 3 -> pharmacy.setHospitalType(currentCell.getStringCellValue());
                    case 4 -> pharmacy.setAddress(currentCell.getStringCellValue());
                    case 5 -> pharmacy.setHref(currentCell.getStringCellValue());
                    case 6 -> pharmacy.setWorkingTime(currentCell.getStringCellValue());
                    case 7 -> pharmacy.setMunicipality(currentCell.getStringCellValue());
                    case 8 -> pharmacy.setPhonen(currentCell.getStringCellValue());
                    default -> {
                    }
                }

                cellIdx++;


            }
            pharmacies.add(pharmacy);
        }
        workbook.close();
        this.pharmacyRepository.saveAll(pharmacies);
        return pharmacies;
    }

    @Override
    public List<Pharmacy> findAllByMunicipality(String municipality) {
        return this.pharmacyRepository.findAllByMunicipalityIsLike(municipality);
    }

    @Override
    public Pharmacy incrementSearchCount(String searchInput) {
        Pharmacy pharmacy = this.pharmacyRepository.findByNameIsLike(searchInput);
        if (pharmacy == null) {
            throw new ProblemSolvingError();
        }
        pharmacy.setSearchCount(pharmacy.getSearchCount() + 1);
        this.pharmacyRepository.save(pharmacy);
        return pharmacy;
    }

    @Override
    public List<Pharmacy> findMostSearchedHospitals() {
        List <Pharmacy> pharmacies =  this.pharmacyRepository.findBySearchCount();
        pharmacies = pharmacies.subList(0,10);
        return pharmacies;
    }

    @Override
    public Pharmacy findById(Long id) {
        return this.pharmacyRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("No pharmacy found with given id."));
    }
}
