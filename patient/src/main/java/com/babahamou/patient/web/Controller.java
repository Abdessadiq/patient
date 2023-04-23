package com.babahamou.patient.web;

import com.babahamou.patient.entities.Patient;
import com.babahamou.patient.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    public PatientRepository patientRepository;
    @GetMapping(path="/test")
    public String test(){
        return "test";
    }
    @GetMapping(path = "/patients")
    public  String list(Model model){
        List<Patient> patients = patientRepository.findAll();
        model.addAttribute("patients", patients);

        return "patient";
    }
}
