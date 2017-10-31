package com.example.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dao.EtudiantRepository;
import com.example.demo.entities.Etudiant;

@Controller
@RequestMapping(value="/Etudiant")
public class EtudiantController {
	@Autowired
	private EtudiantRepository etudiantRepository;
	
	@RequestMapping(value="/Index")
	public String index(Model model, 
			@RequestParam(name="page", defaultValue="0") int page,
			@RequestParam(name="motCle", defaultValue="") String motCle) {
		Page<Etudiant> pageEtudiants = etudiantRepository.chercherEtudiants("%" + motCle + "%", new PageRequest(page, 5));
		
		int pagesCount = pageEtudiants.getTotalPages();
		int[] pages = new int[pagesCount];
		for (int i = 0; i < pagesCount; i++) pages[i] = i;
		
		model.addAttribute("pages", pages);
		model.addAttribute("pageEtudiants", pageEtudiants);
		
		model.addAttribute("pageCourante", page);
		model.addAttribute("motCle", motCle);
		return "etudiants";
	}

}
