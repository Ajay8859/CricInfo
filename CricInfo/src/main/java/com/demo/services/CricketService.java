package com.demo.services;

import java.util.List;

import com.demo.entities.Match;


//To make structure loosely coupled
public interface CricketService {

	List<Match> getLiveMatchScores();

	List<Match> getAllMatches();

}
