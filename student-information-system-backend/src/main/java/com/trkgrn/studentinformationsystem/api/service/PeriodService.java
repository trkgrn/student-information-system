package com.trkgrn.studentinformationsystem.api.service;

import com.trkgrn.studentinformationsystem.api.model.entity.Period;
import com.trkgrn.studentinformationsystem.api.repository.PeriodRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PeriodService {
    private final PeriodRepository periodRepository;

    public PeriodService(PeriodRepository periodRepository) {
        this.periodRepository = periodRepository;
    }

    public List<Period> getAllPeriods() {
        return periodRepository.findAll();
    }

    public Period savePeriod(Period period) {
        return periodRepository.save(period);
    }

    public Period getPeriodById(Long id) {
        return periodRepository.findById(id).orElse(null);
    }

    public void deletePeriodById(Long id) {
        periodRepository.deleteById(id);
    }

    public Period updatePeriod(Period period, Long id) {
        Optional<Period> periodOptional = periodRepository.findById(id);
        if (periodOptional.isPresent()) {
            Period updatedPeriod = periodOptional.get();
            updatedPeriod.setPeriodId(id);
            updatedPeriod.setName(period.getName());
            return periodRepository.save(updatedPeriod);
        }
        return null;
    }
}
