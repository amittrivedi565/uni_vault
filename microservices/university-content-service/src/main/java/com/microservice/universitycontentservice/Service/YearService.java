package com.microservice.universitycontentservice.Service;

import com.microservice.universitycontentservice.DTO.YearDTO;
import com.microservice.universitycontentservice.DTO.Mapper.YearMapper;
import com.microservice.universitycontentservice.Entity.Year;
import com.microservice.universitycontentservice.Exceptions.Subject.SubjectServiceException;
import com.microservice.universitycontentservice.Exceptions.Year.YearServiceException;
import com.microservice.universitycontentservice.Repository.BranchRepository;
import com.microservice.universitycontentservice.Repository.YearRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class YearService {
    private static final Logger logger = LoggerFactory.getLogger(YearService.class);

    private final YearRepository yearRepo;
    private final BranchRepository branchRepo;

    @Autowired
    public YearService(YearRepository yearRepo, BranchRepository branchRepo) {
        this.yearRepo = yearRepo;
        this.branchRepo = branchRepo;
    }

    public List<YearDTO> getAllYears() {
        try {
            List<Year> years = yearRepo.findAll();
            return years.stream()
                    .map(YearMapper::toDTO)
                    .collect(Collectors.toList());
        }catch (YearServiceException ex) {
            throw ex;
        } catch (Exception e) {
            logger.error("Error in getAllYears: {}", e.getMessage());
            throw new YearServiceException("An error occurred while fetching all years. Please try again later.");
        }
    }

    public YearDTO createYear(YearDTO yearDTO) {
        try {
            Optional<Year> existingYear = yearRepo.findByNameAndBranchId(yearDTO.getName(),yearDTO.getBranchId());
            if (existingYear.isPresent()) {
                throw new YearServiceException("Year already exists with this name.");
            }

            branchRepo.findById(yearDTO.getBranchId())
                    .orElseThrow(() -> new YearServiceException("Branch not found with this id."));

            Year yearEntity = YearMapper.toEntity(yearDTO);

            Year savedYear = yearRepo.save(yearEntity);

            return YearMapper.toDTO(savedYear);

        } catch (YearServiceException ex) {
            throw ex;
        } catch (Exception e) {
            logger.error("Error in createYear", e);
            throw new YearServiceException("An error occurred while creating the year. Please try again later.");
        }
    }

    @Transactional
    public void deleteYear(UUID yearId) {
        try {
            yearRepo.findById(yearId)
                    .orElseThrow(() -> new YearServiceException("Year with Id " + yearId + " not found."));

            yearRepo.deleteById(yearId);

        } catch (SubjectServiceException ex) {
            throw ex;
        } catch (Exception e) {
            logger.error("Error in deleteYear: {}", e.getMessage());
            throw new YearServiceException("An error occurred while deleting the year. Please try again later.");
        }
    }

    @Transactional
    public YearDTO updateYear(UUID yearId, Year updatedYearData) {
        try {
            Year existingYear = yearRepo.findById(yearId)
                    .orElseThrow(() -> new YearServiceException("Year with Id " + yearId + " not found."));

            existingYear.setName(updatedYearData.getName());
            existingYear.setCode(updatedYearData.getCode());
            Year updatedYear = yearRepo.save(existingYear);
            return YearMapper.toDTO(updatedYear);

        } catch (YearServiceException ex) {
            throw ex;
        } catch (Exception e) {
            logger.error("Error in updateYear: {}", e.getMessage());
            throw new YearServiceException("An error occurred while updating the year. Please try again later.");
        }
    }
}
