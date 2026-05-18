package com.example.demo.modules.patient.utils;

import com.example.demo.modules.patient.Patient;
import com.example.demo.modules.patient.dtos.PatientDTO;
import com.example.demo.modules.patient.dtos.PatientFileDTO;

import java.util.ArrayList;
import java.util.List;

public class PatientUtils {

    public static PatientDTO convertToDTO(Patient p){
        PatientDTO dto = new PatientDTO();
        dto.setId(p.getId());
        dto.setName(p.getName());
        dto.setLastNames(p.getLastNames());
        dto.setPhone(p.getPhone());
        dto.setEmail(p.getEmail());
        dto.setBirthDate(p.getBirthDate());
        if(p.getFile() != null){
            PatientFileDTO fileDTO = new PatientFileDTO();
            fileDTO.setBloodType(p.getFile().getBloodType());
            fileDTO.setAllergies(p.getFile().getAllergies());
            dto.setFile(fileDTO);
        }
        return dto;
    }

    public static List<PatientDTO> convertToDTOList(List<Patient> patients){
        List<PatientDTO> dtos = new ArrayList<>();

        for (Patient p : patients) {
            dtos.add(convertToDTO(p));
        }
        return dtos;
    }

    public static Patient convertToEntity(PatientDTO dto){
        Patient p = new Patient();
        p.setId(dto.getId());
        p.setName(dto.getName());
        p.setLastNames(dto.getLastNames());
        p.setPhone(dto.getPhone());
        p.setEmail(dto.getEmail());
        p.setBirthDate(dto.getBirthDate());
        if(dto.getFile() != null){
            //p.setFile(PatientFileUtils.convertToEntity(dto.getFile()));
        }
        return p;
/*
        if(dto.getId() != null){
            if(p.getFile() != null){
                PatientFileDTO fileDTO = new PatientFileDTO();
                p.setBloodType(dto.getFile().getBloodType());
                p.setAllergies(dto.getFile().getAllergies());
                dto.setFile(p);
            }
        }
        return b;*/
    }


}
