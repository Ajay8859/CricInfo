package com.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.entities.Match;
import com.demo.services.CricketService;


@Controller
public class CricketController {

    private CricketService cricketService;

    public CricketController(CricketService cricketService) {
        this.cricketService = cricketService;
    }

    // api for getting live matches
    @GetMapping("/live")
    public String getLiveMatchScores(Model model)  {
        System.out.println("getting live match");
        List<Match> allMatches = cricketService.getAllMatches();
        model.addAttribute("allmatch",allMatches);
        return "all_matches";
        
    }

    

    @RequestMapping("/get-matches")
    public String getAllMatches(Model model) {
    	
    	List<Match> liveMatches = cricketService.getLiveMatchScores();
    	model.addAttribute("matches", liveMatches);

    	return "matches"; // This is the name of your JSP file without the extension
       }
}
