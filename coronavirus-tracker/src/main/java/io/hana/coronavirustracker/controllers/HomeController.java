package io.hana.coronavirustracker.controllers;

import io.hana.coronavirustracker.models.LocationStats;
import io.hana.coronavirustracker.services.CoronaVirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Generated;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    CoronaVirusDataService coronaVirusDataService;
    @GetMapping("/")
    public String Home(Model model){
        List<LocationStats> allStats = coronaVirusDataService.getAllStats();
        int totalReportedCases = allStats.stream().mapToInt(stat->stat.getLatestTotalCases()).sum();
        int totalNewCases = allStats.stream().mapToInt(stat->stat.getDiffFromPrevDay()).sum();
        model.addAttribute("locationStats", allStats);
        model.addAttribute("totalReportedCases", totalReportedCases);
        model.addAttribute("totalNewCases", totalNewCases);
        return "home";
    }
}
