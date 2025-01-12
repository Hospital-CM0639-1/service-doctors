package hospital.servicedoctor.service;

import hospital.servicedoctor.model.PatientVital;
import hospital.servicedoctor.model.dto.patientvital.DetailPatientVitalDto;
import hospital.servicedoctor.model.dto.patientvital.PatientVitalDto;
import hospital.servicedoctor.model.enums.EStaffRole;
import hospital.servicedoctor.repository.IPatientVitalRepository;
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
public class PatientVitalService {

    private static final Logger logger = LoggerFactory.getLogger(PatientVitalService.class);

    private final IPatientVitalRepository patientVitalRepository;
    private final ModelMapper modelMapper;

    public PatientVitalService(IPatientVitalRepository patientVitalRepository, ModelMapper modelMapper) {
        this.patientVitalRepository = patientVitalRepository;
        this.modelMapper = modelMapper;
    }

    /**
     * Get all pageable patient vitals
     */
    public Page<PatientVitalDto> getAllPageablePatientVitals(Pageable pageable) {
        return patientVitalRepository.findAll(pageable).map(this::convertToDto);
    }

    /**
     * Get all patient vitals made by doctor with a given id (WHAT HAS DONE A DOCTOR)
     */
    public List<DetailPatientVitalDto> getAllDoctorPatientVitals(Long doctorId, LocalDateTime startDate, LocalDateTime finishDate) {
        List<PatientVital> patientVitalList = patientVitalRepository.findAllByStaff_IdAndStaff_RoleAndRecordedAtBetween(
                doctorId, EStaffRole.DOCTOR,  startDate, finishDate);
        return patientVitalList.stream()
                .map(this::convertToDetailedDto)
                .collect(Collectors.toList());
    }

    /**
     * Get patient vitals of patient
     */
    public Page<DetailPatientVitalDto> getPatientVitalsOfPatient(Long patientId, LocalDateTime startDate, LocalDateTime finishDate, Pageable pageable) {
        Page<PatientVital> patientVitalList = null;
        if (startDate != null && finishDate != null) {
            patientVitalList = patientVitalRepository.findAllByEmergencyVisit_Patient_IdAndRecordedAtBetween(
                    patientId, startDate, finishDate, pageable);
        } else {
            patientVitalList = patientVitalRepository.findAllByEmergencyVisit_Patient_Id(patientId, pageable);
        }
        return patientVitalList.map(this::convertToDetailedDto);
    }

    /**
     * Save patient vital
     */
    public DetailPatientVitalDto savePatientVital(DetailPatientVitalDto patientVitalDto) {
        PatientVital patientVital = convertToDao(patientVitalDto);
        PatientVital savePatientVital  = patientVitalRepository.save(patientVital);
        return convertToDetailedDto(savePatientVital);
    }

    /**
     * Update patient vital
     */
    public DetailPatientVitalDto updatePatientVital(DetailPatientVitalDto patientVitalDto, Long id) {
        boolean existsById = patientVitalRepository.existsById(id);
        if (!existsById) {
            logger.error("Patient vital with id {} not found", id);
            throw new IllegalArgumentException("Patient vital with id " + id + " not found");
        }
        PatientVital patientVital = convertToDao(patientVitalDto);
        patientVital.setId(id);
        PatientVital savePatientVital = patientVitalRepository.save(patientVital);
        return convertToDetailedDto(savePatientVital);
    }

    /**
     * Delete patient vital
     */
    public void deletePatientVital(Long id) {
        boolean existsById = patientVitalRepository.existsById(id);
        if (!existsById) {
            logger.error("Patient vital with id {} not found", id);
            throw new IllegalArgumentException("Patient vital with id " + id + " not found");
        }
        patientVitalRepository.deleteById(id);
    }

    // Converters
    private PatientVitalDto convertToDto(PatientVital patientVital) {
        return modelMapper.map(patientVital, PatientVitalDto.class);
    }
    private DetailPatientVitalDto convertToDetailedDto(PatientVital patientVital) {
        return modelMapper.map(patientVital, DetailPatientVitalDto.class);
    }
    private PatientVital convertToDao(DetailPatientVitalDto patientVitalDto) {
        return modelMapper.map(patientVitalDto, PatientVital.class);
    }


}
