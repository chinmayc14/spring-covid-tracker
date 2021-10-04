package com.example.covidtracker.controllers;

import com.example.covidtracker.services.CovidDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CovidDataController {
    @Autowired
    private CovidDataService covidDataService;
    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("locationStats", covidDataService.getAllStats());
        int totalReportedCases  = covidDataService.getAllStats().stream().mapToInt(stats->stats.getLatestTotalCases()).sum();
        int totalNewCases  = covidDataService.getAllStats().stream().mapToInt(stats->stats.getDiffFromPrevDay()).sum();
        model.addAttribute("totalReportedCases", totalReportedCases);
        model.addAttribute("totalNewCases",totalNewCases);

        return "home";
    }
}
