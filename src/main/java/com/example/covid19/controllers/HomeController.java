package com.example.covid19.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.covid19.services.covid19DataService;
import com.example.covid19.models.LocationStats;

import java.util.List;


@Controller
public class HomeController {
   
	@Autowired
	covid19DataService covid19DataService;
	
	
	@GetMapping("/")
	public String home(Model model) {
		List<LocationStats> allStats = covid19DataService.getAllStats();
	    int totalReportedCases = allStats.stream().mapToInt(stat -> stat.getConfirmed()).sum();
        //int totalNewCases = allStats.stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum();

        model.addAttribute("locationStats", allStats);
        model.addAttribute("totalReportedCases", totalReportedCases);
        //model.addAttribute("totalNewCases", totalNewCases);
 
        
    return "home";
   }
}

