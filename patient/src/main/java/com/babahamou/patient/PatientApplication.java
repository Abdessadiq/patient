package com.babahamou.patient;

import com.babahamou.patient.entities.Patient;
import com.babahamou.patient.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

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

		/* patientRepository.save(new Patient(null, "BENRAJJI", new Date(),(int) (Math.random()*100) , true));
		patientRepository.save(new Patient(null, "OBAHHA", new Date(),(int) (Math.random()*100) , true));
		patientRepository.save(new Patient(null, "JARBAOUI", new Date(),(int) (Math.random()*100) , true));
		patientRepository.save(new Patient(null, "HELFAOUI", new Date(),(int) (Math.random()*100) , true));
		patientRepository.save(new Patient(null, "AIT QARIB", new Date(),(int) (Math.random()*100) , true));
		patientRepository.save(new Patient(null, "TABARANI", new Date(),(int) (Math.random()*100) , true));
		patientRepository.save(new Patient(null, "EL-OUAZZANI", new Date(),(int) (Math.random()*100) , true));
		patientRepository.save(new Patient(null, "BELQADIR", new Date(),(int) (Math.random()*100) , true));

		 */

		patientRepository.findAll().forEach(p  ->{
			//System.out.println(p.getName().toString());
		});




	}
}
