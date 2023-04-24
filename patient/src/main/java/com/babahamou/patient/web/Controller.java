package com.babahamou.patient.web;

import com.babahamou.patient.entities.Patient;
import com.babahamou.patient.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public  String list(Model model,
                        @RequestParam(name="page", defaultValue = "0") int page,
                        @RequestParam(name="size", defaultValue = "6") int size,
                        @RequestParam(name="keyword", defaultValue = "") String mc){
        Page<Patient> pagepatients = patientRepository.findPatientByNameContains(mc,PageRequest.of(page,size));
        model.addAttribute("patients", pagepatients);
        model.addAttribute("pages", new int[pagepatients.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword",mc);


        return "patient";
    }
    @GetMapping(path="/deletePatient")
    public String deletePatient(Long id, String keyword){
        patientRepository.deleteById(id);
        return "redirect:/patients?keyword="+keyword;
    }
}
