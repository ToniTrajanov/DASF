package mk.ukim.finki.dashw.service.impl;

import mk.ukim.finki.dashw.model.Hospital;
import mk.ukim.finki.dashw.model.exceptions.ProblemSolvingError;
import mk.ukim.finki.dashw.repository.HospitalRepository;
import mk.ukim.finki.dashw.service.HospitalService;
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
import java.util.*;

@Service
public class HospitalServiceImpl implements HospitalService {

    private final HospitalRepository hospitalRepository;

    public HospitalServiceImpl(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }


    @Override
    public List<Hospital> findAll() {
        return hospitalRepository.findAll();
    }

    @Override
    public List<Hospital> addHospital(String hospitalsUrl) throws IOException {
        //"F:\\DasHomework\\hospitals_data.xlsx"
        Workbook workbook = new XSSFWorkbook(hospitalsUrl);
        List<Hospital> hospitals = new ArrayList<>();
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
            Hospital hospital = new Hospital();

            int cellIdx = 0;
            while (cellsInRow.hasNext()) {
                Cell currentCell = cellsInRow.next();

                // each cell case
                switch (cellIdx) {
                    case 0 -> hospital.setName(currentCell.getStringCellValue());
                    case 1 -> hospital.setRating(Double.parseDouble(currentCell.getStringCellValue()));
                    case 2 -> hospital.setNumberOfReviews(Long.parseLong(currentCell.getStringCellValue()));
                    case 3 -> hospital.setHospitalType(currentCell.getStringCellValue());
                    case 4 -> hospital.setAddress(currentCell.getStringCellValue());
                    case 5 -> hospital.setHref(currentCell.getStringCellValue());
                    case 6 -> hospital.setWorkingTime(currentCell.getStringCellValue());
                    case 7 -> hospital.setMunicipality(currentCell.getStringCellValue());
                    case 8 -> hospital.setPhonen(currentCell.getStringCellValue());
                    default -> {
                    }
                }

                cellIdx++;


            }
            hospitals.add(hospital);
        }
        workbook.close();

        this.hospitalRepository.saveAll(hospitals);
        return hospitals;
    }


    @Override
    public Hospital findById(Long id) {
        return hospitalRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Wrong id"));
    }

    @Override
    public List<Hospital> findAllByMunicipality(String municipality) {
        return this.hospitalRepository.findAllByMunicipality(municipality);
    }

    @Override
    public Optional<Hospital> findByName(String name) {
        return Optional.of(this.hospitalRepository.findByNameIsLike(name));
    }

    @Override
    public Hospital incrementSearchCount(String searchInput) {
        Hospital hospital = this.hospitalRepository.findByNameIsLike(searchInput);
        if (hospital == null) {
            throw new ProblemSolvingError();
        }
        hospital.setSearchCount(hospital.getSearchCount() + 1);
        this.hospitalRepository.save(hospital);
        return hospital;
    }

    @Override
    public List<Hospital> findMostSearchedHospitals() {
        List <Hospital> hospitals =  this.hospitalRepository.findBySearchCount();
        hospitals = hospitals.subList(0,10);
        return hospitals;
    }
}