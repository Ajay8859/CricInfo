package com.demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.entities.Match;

public interface MatchRepo extends JpaRepository<Match,Integer> {

	//Match fetch karna chahta hu provide kar de team ka naam
    Optional<Match> findByTeamHeading(String teamHeading);

}
