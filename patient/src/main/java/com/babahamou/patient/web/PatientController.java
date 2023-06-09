package com.babahamou.patient.web;

import com.babahamou.patient.entities.Patient;
import com.babahamou.patient.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class PatientController {
    @Autowired
    public PatientRepository patientRepository;

    /**
     * La méthode Qui fait appel à la vue de la page accueil
     * @return
     */
    @GetMapping(path="/index")
    public String test(){
        return "index";
    }

    /**
     * La méthode de recherche par numero de patient
     * @param model pour stocker les donnée envoyer à la vue
     * @param num numéro de patient
     * @param page pour à afficher
     * @param size le nombre d'enregistrement à afficher
     * @return
     */
    @GetMapping(path = "patientNumero")
    public String patientNumero(Model model,
                                @RequestParam(name = "num", defaultValue = "0") int  num,
                                @RequestParam(name="page", defaultValue = "0") int page,
                                @RequestParam(name="size", defaultValue = "6") int size
                                ){

        Page<Patient> patientPage = patientRepository.findByNumero((int)num, PageRequest.of(page, size));
        model.addAttribute("patients", patientPage);
        model.addAttribute("pages", new int[patientPage.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("size", size);
        model.addAttribute("num", num);
        return "patient";

    }

    /**
     * La méthode pour récupérer les donnée et la chercher par le nom de patient
     * @param model
     * @param page
     * @param size
     * @param mc
     * @return
     */
    @GetMapping(path = "/patients")
    public  String list(Model model,
                        @RequestParam(name="page", defaultValue = "0") int page,
                        @RequestParam(name="size", defaultValue = "8") int size,
                        @RequestParam(name="keyword", defaultValue = "") String mc){
        Page<Patient> pagepatients = patientRepository.findPatientByNameContains(mc,PageRequest.of(page,size));
        model.addAttribute("patients", pagepatients);
        model.addAttribute("pages", new int[pagepatients.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("size", size);
        model.addAttribute("keyword",mc);

        return "patient";
    }

    /**
     * La méthode de suppression d'un patient
     * @param id
     * @param page
     * @param size
     * @param keyword
     * @return
     */
    @GetMapping(path="/deletePatient")
    public String deletePatient(Long id,int page, int size, String keyword){
        patientRepository.deleteById(id);

        return "redirect:/patients?page="+page+"&size="+size+"&keyword="+keyword;

    }

    /**
     * la méthode pour retourner la vue de j'ajout et le modification d'un patient
     * @param model
     * @return
     */

    @GetMapping(path = "/formPatient")
    public String formPatient(Model model){

        model.addAttribute("patient", new Patient());
        model.addAttribute("mode", "new");
        return "formPatient";
    }

    /**
     * La méthode savePatient pour l'ajout d'un patient
     * @param patient
     * @return
     */
    @PostMapping(path = "/savePatient")
    public String savePatient( Patient patient ){
        patientRepository.save(patient);
        return "confirmation";
    }

    /**
     * La méthode editPatient Pour la modification d'un patient
     * @param model
     * @param id
     * @return
     */


    @GetMapping(path = "/editPatient")
    public String editPatient(Model model, Long id){

        Patient patient = patientRepository.findById(id).get();
        patientRepository.save(patient);
        model.addAttribute("patient", patient);
        model.addAttribute("mode", "edit");
        return "formPatient";
    }


  /* @GetMapping(path = "/editPatient")
    public String editPatient(Model model, Long id){

        Patient p = patientRepository.findById(id).get();
        model.addAttribute("patient", p);
        model.addAttribute("mode", "edit");
        return "formPatient";
    }*/

}
