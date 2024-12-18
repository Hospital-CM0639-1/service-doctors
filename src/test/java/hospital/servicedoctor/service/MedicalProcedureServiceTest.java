package hospital.servicedoctor.service;
import hospital.servicedoctor.model.MedicalProcedure;
import hospital.servicedoctor.model.dto.medicalprocedure.DetailMedicalProcedureDto;
import hospital.servicedoctor.model.dto.medicalprocedure.MedicalProcedureDto;
import hospital.servicedoctor.model.enums.EStaffRole;
import hospital.servicedoctor.repository.IMedicalProcedureRepository;
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
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MedicalProcedureServiceTest {

    @InjectMocks
    private MedicalProcedureService medicalProcedureService;

    @Mock
    private IMedicalProcedureRepository medicalProcedureRepository;

    @Mock
    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllPageableMedicalProcedures_ShouldReturnPageOfMedicalProcedureDto() {
        Pageable pageable = PageRequest.of(0, 10);
        MedicalProcedure medicalProcedure = new MedicalProcedure();
        MedicalProcedureDto medicalProcedureDto = new MedicalProcedureDto();

        when(medicalProcedureRepository.findAll(pageable)).thenReturn(new PageImpl<>(Collections.singletonList(medicalProcedure)));
        when(modelMapper.map(medicalProcedure, MedicalProcedureDto.class)).thenReturn(medicalProcedureDto);

        Page<MedicalProcedureDto> result = medicalProcedureService.getAllPageableMedicalProcedures(pageable);

        assertNotNull(result);
        assertEquals(1, result.getContent().size());
        verify(medicalProcedureRepository, times(1)).findAll(pageable);
    }

    @Test
    void getAllMedicalDoctorProcedures_ShouldReturnListOfDetailMedicalProcedureDto() {
        Long doctorId = 1L;
        LocalDateTime startDate = LocalDateTime.now().minusDays(1);
        LocalDateTime finishDate = LocalDateTime.now();
        MedicalProcedure medicalProcedure = new MedicalProcedure();
        DetailMedicalProcedureDto detailDto = new DetailMedicalProcedureDto();

        when(medicalProcedureRepository.findAllByStaff_IdAndStaff_RoleAndProcedureTimestampBetween(
                doctorId, EStaffRole.DOCTOR, startDate, finishDate)).thenReturn(Collections.singletonList(medicalProcedure));
        when(modelMapper.map(medicalProcedure, DetailMedicalProcedureDto.class)).thenReturn(detailDto);

        List<DetailMedicalProcedureDto> result = medicalProcedureService.getAllMedicalDoctorProcedures(doctorId, startDate, finishDate);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(medicalProcedureRepository, times(1)).findAllByStaff_IdAndStaff_RoleAndProcedureTimestampBetween(
                doctorId, EStaffRole.DOCTOR, startDate, finishDate);
    }

    @Test
    void getPatientMedicalProcedures_ShouldReturnListOfDetailMedicalProcedureDto() {
        Long patientId = 1L;
        LocalDateTime startDate = LocalDateTime.now().minusDays(1);
        LocalDateTime finishDate = LocalDateTime.now();
        MedicalProcedure medicalProcedure = new MedicalProcedure();
        DetailMedicalProcedureDto detailDto = new DetailMedicalProcedureDto();

        when(medicalProcedureRepository.findAllByEmergencyVisit_Patient_IdAndProcedureTimestampBetween(
                patientId, startDate, finishDate)).thenReturn(Collections.singletonList(medicalProcedure));
        when(modelMapper.map(medicalProcedure, DetailMedicalProcedureDto.class)).thenReturn(detailDto);

        List<DetailMedicalProcedureDto> result = medicalProcedureService.getPatientMedicalProcedures(patientId, startDate, finishDate);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(medicalProcedureRepository, times(1)).findAllByEmergencyVisit_Patient_IdAndProcedureTimestampBetween(
                patientId, startDate, finishDate);
    }

    @Test
    void saveMedicalProcedure_ShouldSaveAndReturnDetailMedicalProcedureDto() {
        DetailMedicalProcedureDto detailDto = new DetailMedicalProcedureDto();
        MedicalProcedure medicalProcedure = new MedicalProcedure();

        when(modelMapper.map(detailDto, MedicalProcedure.class)).thenReturn(medicalProcedure);
        when(medicalProcedureRepository.save(medicalProcedure)).thenReturn(medicalProcedure);
        when(modelMapper.map(medicalProcedure, DetailMedicalProcedureDto.class)).thenReturn(detailDto);

        DetailMedicalProcedureDto result = medicalProcedureService.saveMedicalProcedure(detailDto);

        assertNotNull(result);
        verify(medicalProcedureRepository, times(1)).save(medicalProcedure);
    }

    @Test
    void updateMedicalProcedure_ShouldUpdateAndReturnDetailMedicalProcedureDto() {
        Long id = 1L;
        DetailMedicalProcedureDto detailDto = new DetailMedicalProcedureDto();
        MedicalProcedure medicalProcedure = new MedicalProcedure();

        when(medicalProcedureRepository.existsById(id)).thenReturn(true);
        when(modelMapper.map(detailDto, MedicalProcedure.class)).thenReturn(medicalProcedure);
        when(medicalProcedureRepository.save(medicalProcedure)).thenReturn(medicalProcedure);
        when(modelMapper.map(medicalProcedure, DetailMedicalProcedureDto.class)).thenReturn(detailDto);

        DetailMedicalProcedureDto result = medicalProcedureService.updateMedicalProcedure(detailDto, id);

        assertNotNull(result);
        verify(medicalProcedureRepository, times(1)).existsById(id);
        verify(medicalProcedureRepository, times(1)).save(medicalProcedure);
    }

    @Test
    void updateMedicalProcedure_ShouldThrowExceptionIfNotFound() {
        Long id = 1L;
        DetailMedicalProcedureDto detailDto = new DetailMedicalProcedureDto();

        when(medicalProcedureRepository.existsById(id)).thenReturn(false);

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                medicalProcedureService.updateMedicalProcedure(detailDto, id));

        assertEquals("Medical procedure with id 1 not found", exception.getMessage());
        verify(medicalProcedureRepository, times(1)).existsById(id);
    }

    @Test
    void deleteMedicalProcedure_ShouldDeleteIfExists() {
        Long id = 1L;

        when(medicalProcedureRepository.existsById(id)).thenReturn(true);

        medicalProcedureService.deleteMedicalProcedure(id);

        verify(medicalProcedureRepository, times(1)).existsById(id);
        verify(medicalProcedureRepository, times(1)).deleteById(id);
    }

    @Test
    void deleteMedicalProcedure_ShouldThrowExceptionIfNotFound() {
        Long id = 1L;

        when(medicalProcedureRepository.existsById(id)).thenReturn(false);

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                medicalProcedureService.deleteMedicalProcedure(id));

        assertEquals("Medical procedure with id 1 not found", exception.getMessage());
        verify(medicalProcedureRepository, times(1)).existsById(id);
    }
}