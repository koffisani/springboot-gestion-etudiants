package com.example.demo;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.example.demo.dao.EtudiantRepository;
import com.example.demo.entities.Etudiant;

@SpringBootApplication
public class TpSpringMvcApplication {

	public static void main(String[] args) throws ParseException {
		ApplicationContext ctx = 
				SpringApplication.run(TpSpringMvcApplication.class, args);
		EtudiantRepository etudiantRepository = 
				ctx.getBean(EtudiantRepository.class);
		DateFormat df = new SimpleDateFormat("yyyy-M-dd");
		/*etudiantRepository.save(
				new Etudiant("Koffi", df.parse("1990-10-26"), 
						"koffisani@gmail.com", "koffi.jpg"));
		
		etudiantRepository.save(
				new Etudiant("Edem", df.parse("1989-07-25"), 
						"edemkagbeti@gmail.com", "edem.jpg"));
		
		etudiantRepository.save(
				new Etudiant("Mawuli", df.parse("1990-10-26"), 
						"segla58@gmail.com", "mawuli.jpg"));*/
		
		Page<Etudiant> etds = etudiantRepository.findAll(new PageRequest(0, 5)); 
		
		etds.forEach(e->System.out.println(e.getNom()));
		
		System.out.println("Nouvelle recherche");
		etds = etudiantRepository.chercherEtudiants("%i%", new PageRequest(0, 5));
		etds.forEach(e->System.out.println(e.getId() + " - " + e.getNom()));
	}
}
