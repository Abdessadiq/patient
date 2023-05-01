package com.babahamou.patient.repository;

import com.babahamou.patient.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    Page<Patient> findPatientByNameContains(String mc, Pageable pageable);
    Page<Patient> findByNumero(int num, Pageable pageable);

    //Page<Patient> findPatientByNumeroContains(int numero, Pageable pageable);
}
