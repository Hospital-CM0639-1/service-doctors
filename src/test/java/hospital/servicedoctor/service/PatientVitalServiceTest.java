package hospital.servicedoctor.service;

import hospital.servicedoctor.model.PatientVital;
import hospital.servicedoctor.model.dto.patientvital.DetailPatientVitalDto;
import hospital.servicedoctor.model.dto.patientvital.PatientVitalDto;
import hospital.servicedoctor.model.enums.EStaffRole;
import hospital.servicedoctor.repository.IPatientVitalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PatientVitalServiceTest {

    @InjectMocks
    private PatientVitalService patientVitalService;

    @Mock
    private IPatientVitalRepository patientVitalRepository;

    @Mock
    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllPageablePatientVitals_ShouldReturnPageOfPatientVitalDto() {
        Pageable pageable = PageRequest.of(0, 10);
        PatientVital patientVital = new PatientVital();
        PatientVitalDto patientVitalDto = new PatientVitalDto();

        when(patientVitalRepository.findAll(pageable)).thenReturn(new PageImpl<>(Collections.singletonList(patientVital)));
        when(modelMapper.map(patientVital, PatientVitalDto.class)).thenReturn(patientVitalDto);

        Page<PatientVitalDto> result = patientVitalService.getAllPageablePatientVitals(pageable);

        assertNotNull(result);
        assertEquals(1, result.getContent().size());
        verify(patientVitalRepository, times(1)).findAll(pageable);
    }

    @Test
    void getAllDoctorPatientVitals_ShouldReturnListOfDetailPatientVitalDto() {
        Long doctorId = 1L;
        LocalDateTime startDate = LocalDateTime.now().minusDays(1);
        LocalDateTime finishDate = LocalDateTime.now();
        PatientVital patientVital = new PatientVital();
        DetailPatientVitalDto detailDto = new DetailPatientVitalDto();

        when(patientVitalRepository.findAllByStaff_IdAndStaff_RoleAndRecordedAtBetween(
                doctorId, EStaffRole.DOCTOR, startDate, finishDate)).thenReturn(Collections.singletonList(patientVital));
        when(modelMapper.map(patientVital, DetailPatientVitalDto.class)).thenReturn(detailDto);

        List<DetailPatientVitalDto> result = patientVitalService.getAllDoctorPatientVitals(doctorId, startDate, finishDate);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(patientVitalRepository, times(1)).findAllByStaff_IdAndStaff_RoleAndRecordedAtBetween(
                doctorId, EStaffRole.DOCTOR, startDate, finishDate);
    }

    @Test
    void getPatientVitalsOfPatient_ShouldReturnListOfDetailPatientVitalDto() {
        Long patientId = 1L;
        LocalDateTime startDate = LocalDateTime.now().minusDays(1);
        LocalDateTime finishDate = LocalDateTime.now();
        PatientVital patientVital = new PatientVital();
        DetailPatientVitalDto detailDto = new DetailPatientVitalDto();

        when(patientVitalRepository.findAllByEmergencyVisit_Patient_IdAndRecordedAtBetween(
                patientId, startDate, finishDate)).thenReturn(Collections.singletonList(patientVital));
        when(modelMapper.map(patientVital, DetailPatientVitalDto.class)).thenReturn(detailDto);

        List<DetailPatientVitalDto> result = patientVitalService.getPatientVitalsOfPatient(patientId, startDate, finishDate);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(patientVitalRepository, times(1)).findAllByEmergencyVisit_Patient_IdAndRecordedAtBetween(
                patientId, startDate, finishDate);
    }

    @Test
    void savePatientVital_ShouldSaveAndReturnDetailPatientVitalDto() {
        DetailPatientVitalDto detailDto = new DetailPatientVitalDto();
        PatientVital patientVital = new PatientVital();

        when(modelMapper.map(detailDto, PatientVital.class)).thenReturn(patientVital);
        when(patientVitalRepository.save(patientVital)).thenReturn(patientVital);
        when(modelMapper.map(patientVital, DetailPatientVitalDto.class)).thenReturn(detailDto);

        DetailPatientVitalDto result = patientVitalService.savePatientVital(detailDto);

        assertNotNull(result);
        verify(patientVitalRepository, times(1)).save(patientVital);
    }

    @Test
    void updatePatientVital_ShouldUpdateAndReturnDetailPatientVitalDto() {
        Long id = 1L;
        DetailPatientVitalDto detailDto = new DetailPatientVitalDto();
        PatientVital patientVital = new PatientVital();

        when(patientVitalRepository.existsById(id)).thenReturn(true);
        when(modelMapper.map(detailDto, PatientVital.class)).thenReturn(patientVital);
        when(patientVitalRepository.save(patientVital)).thenReturn(patientVital);
        when(modelMapper.map(patientVital, DetailPatientVitalDto.class)).thenReturn(detailDto);

        DetailPatientVitalDto result = patientVitalService.updatePatientVital(detailDto, id);

        assertNotNull(result);
        verify(patientVitalRepository, times(1)).existsById(id);
        verify(patientVitalRepository, times(1)).save(patientVital);
    }

    @Test
    void updatePatientVital_ShouldThrowExceptionIfNotFound() {
        Long id = 1L;
        DetailPatientVitalDto detailDto = new DetailPatientVitalDto();

        when(patientVitalRepository.existsById(id)).thenReturn(false);

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                patientVitalService.updatePatientVital(detailDto, id));

        assertEquals("Patient vital with id 1 not found", exception.getMessage());
        verify(patientVitalRepository, times(1)).existsById(id);
    }

    @Test
    void deletePatientVital_ShouldDeleteIfExists() {
        Long id = 1L;

        when(patientVitalRepository.existsById(id)).thenReturn(true);

        patientVitalService.deletePatientVital(id);

        verify(patientVitalRepository, times(1)).existsById(id);
        verify(patientVitalRepository, times(1)).deleteById(id);
    }

    @Test
    void deletePatientVital_ShouldThrowExceptionIfNotFound() {
        Long id = 1L;

        when(patientVitalRepository.existsById(id)).thenReturn(false);

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                patientVitalService.deletePatientVital(id));

        assertEquals("Patient vital with id 1 not found", exception.getMessage());
        verify(patientVitalRepository, times(1)).existsById(id);
    }
}
