package com.hospital.spring.mvc.form;

import java.util.ArrayList;

import javax.validation.Valid;

import com.hospital.spring.extensions.ajax.AjaxUtils;
import com.hospital.spring.mvc.home.Data;
import com.hospital.spring.mvc.home.Disease;
import com.hospital.spring.mvc.home.Symptom;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/patientform")
@SessionAttributes("patientBean")
public class PatientController {

	// Invoked on every request

	@ModelAttribute
	public void ajaxAttribute(WebRequest request, Model model) {
		model.addAttribute("ajaxRequest", AjaxUtils.isAjaxRequest(request));
	}

	// Invoked initially to create the "form" attribute
	// Once created the "form" attribute comes from the HTTP session (see @SessionAttributes)

	@ModelAttribute("patientBean")
	public PatientBean createFormBean() {
		return new PatientBean();
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView form() {
		Data data = new Data();
		ArrayList<Symptom> symptoms = data.getUniqueSymptoms();
		ModelAndView mav = new ModelAndView("form");
		mav.addObject("symptomList", symptoms);
		return mav;
	}	
	
	@RequestMapping(value="/success", method=RequestMethod.POST)
	public String result(@RequestParam(value="symptomNames", required=false) String[] symptoms,
			@Valid PatientBean patient, BindingResult result, Model m) {
		if (result.hasErrors()) {
			Data data = new Data();
			ArrayList<Symptom> allSymptoms = data.getUniqueSymptoms();
			m.addAttribute("hasError", true);
			m.addAttribute("symptomList", allSymptoms);
			return "form";
		}
		ArrayList<Disease> userDiseases = DiagnosePatientUtil.findDiagnose(symptoms);
		m.addAttribute("symptomList", userDiseases);
		return "diagnose";
	}	
}
