package com.hospital.spring.mvc.home;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
	
	Data data;
	public HomeController() {
		this.data = new Data();
	}
	
	@RequestMapping("/")
	public String prepare(Model model) {
		Disease[] topThreeDiseases = this.data.getTopThreeDiseases();
		Symptom[] topThreeSymptoms = this.data.getTopThreeSymptoms();
		int uniqueSymptoms = this.data.getUniqueSymptomsCount();
		
		model.addAttribute("topDiseases", topThreeDiseases);
		model.addAttribute("topSymptoms", topThreeSymptoms);
		model.addAttribute("uniqueSymptoms", uniqueSymptoms);
		
		return "home";
	}
	
}
