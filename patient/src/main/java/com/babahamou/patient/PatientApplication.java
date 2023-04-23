package com.babahamou.patient;

import com.babahamou.patient.entities.Patient;
import com.babahamou.patient.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class PatientApplication implements CommandLineRunner {
	@Autowired
	PatientRepository patientRepository;

	public static void main(String[] args) {
		SpringApplication.run(PatientApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		patientRepository.save(new Patient(null, "AHMED", new Date(), false));
		patientRepository.save(new Patient(null, "KHALID", new Date(), true));
		patientRepository.save(new Patient(null, "IHSSANE", new Date(), false));
		patientRepository.save(new Patient(null, "MOANADE", new Date(), true));
		patientRepository.save(new Patient(null, "INTESSARE", new Date(), false));

		patientRepository.findAll().forEach(p  ->{
			System.out.println(p.getName().toString());
		});



	}
}
