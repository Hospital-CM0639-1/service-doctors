package hospital.servicedoctor.service;

import hospital.servicedoctor.model.MedicalProcedure;
import hospital.servicedoctor.model.dto.medicalprocedure.DetailMedicalProcedureDto;
import hospital.servicedoctor.model.dto.medicalprocedure.MedicalProcedureDto;
import hospital.servicedoctor.model.enums.EStaffRole;
import hospital.servicedoctor.repository.IMedicalProcedureRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/* Service CRUD Medical Procedure */
@Service
public class MedicalProcedureService {

    private static final Logger logger = LoggerFactory.getLogger(MedicalProcedureService.class);

    private final IMedicalProcedureRepository medicalProcedureRepository;
    private final ModelMapper modelMapper;

    public MedicalProcedureService(IMedicalProcedureRepository medicalProcedureRepository, ModelMapper modelMapper) {
        this.medicalProcedureRepository = medicalProcedureRepository;
        this.modelMapper = modelMapper;
    }

    /**
     * Get all pageable medical procedures
     */
    public Page<MedicalProcedureDto> getAllPageableMedicalProcedures(Pageable pageable) {
        return medicalProcedureRepository.findAll(pageable).map(this::convertToDto);
    }

    /**
     * Get all medical procedures for a doctor with a given id (WHAT HAS DONE A DOCTOR)
     */
    public List<DetailMedicalProcedureDto> getAllMedicalDoctorProcedures(Long doctorId, LocalDateTime startDate, LocalDateTime finishDate) {
        List<MedicalProcedure> medicalProcedureList = medicalProcedureRepository.findAllByStaff_IdAndStaff_RoleAndProcedureTimestampBetween(
                doctorId, EStaffRole.DOCTOR,  startDate, finishDate);
        return medicalProcedureList.stream()
                .map(this::convertToDetailedDto)
                .collect(Collectors.toList());
    }

    /**
     * Get patient's medical procedures
     */
    public Page<DetailMedicalProcedureDto> getPatientMedicalProcedures(Long patientId, LocalDateTime startDate, LocalDateTime finishDate, Pageable pageable) {
        Page<MedicalProcedure> medicalProcedures = null;
        if (startDate != null && finishDate != null) {
            medicalProcedures = medicalProcedureRepository.findAllByEmergencyVisit_Patient_IdAndProcedureTimestampBetween(
                    patientId, startDate, finishDate, pageable);
        } else {
            medicalProcedures = medicalProcedureRepository.findAllByEmergencyVisit_Patient_Id(patientId, pageable);
        }
        return medicalProcedures.map(this::convertToDetailedDto);
    }

    /**
     * Save medical procedure
     */
    public DetailMedicalProcedureDto saveMedicalProcedure(DetailMedicalProcedureDto medicalProcedureDto) {
        MedicalProcedure medicalProcedure = convertToDao(medicalProcedureDto);
        MedicalProcedure saveMedicalProcedure = medicalProcedureRepository.save(medicalProcedure);
        return convertToDetailedDto(saveMedicalProcedure);
    }

    /**
     * Update medical procedure
     */
    public DetailMedicalProcedureDto updateMedicalProcedure(DetailMedicalProcedureDto medicalProcedureDto, Long id) {
        boolean existsById = medicalProcedureRepository.existsById(id);
        if (!existsById) {
            logger.error("Medical procedure with id {} not found", id);
            throw new IllegalArgumentException("Medical procedure with id " + id + " not found");
        }
        MedicalProcedure medicalProcedure = convertToDao(medicalProcedureDto);
        medicalProcedure.setId(id);
        MedicalProcedure saveMedicalProcedure = medicalProcedureRepository.save(medicalProcedure);
        return convertToDetailedDto(saveMedicalProcedure);
    }

    /**
     * Delete medical procedure
     */
    public void deleteMedicalProcedure(Long id) {
        boolean existsById = medicalProcedureRepository.existsById(id);
        if (!existsById) {
            logger.error("Medical procedure with id {} not found", id);
            throw new IllegalArgumentException("Medical procedure with id " + id + " not found");
        }
        medicalProcedureRepository.deleteById(id);
    }

    // Converters
    private MedicalProcedureDto convertToDto(MedicalProcedure medicalProcedure) {
        return modelMapper.map(medicalProcedure, MedicalProcedureDto.class);
    }
    private DetailMedicalProcedureDto convertToDetailedDto(MedicalProcedure medicalProcedure) {
        return modelMapper.map(medicalProcedure, DetailMedicalProcedureDto.class);
    }
    private MedicalProcedure convertToDao(DetailMedicalProcedureDto medicalProcedureDto) {
        return modelMapper.map(medicalProcedureDto, MedicalProcedure.class);
    }


}
