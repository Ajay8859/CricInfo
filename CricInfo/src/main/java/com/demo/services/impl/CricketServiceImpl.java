package com.demo.services.impl;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.demo.entities.Match;
import com.demo.repositories.MatchRepo;
import com.demo.services.CricketService;

@Service
public class CricketServiceImpl implements CricketService {

	private MatchRepo matchRepo;

	public CricketServiceImpl(MatchRepo matchRepo) {
		this.matchRepo = matchRepo;
	}

	@Override
	public List<Match> getLiveMatchScores() {

		List<Match> matches = new ArrayList<>();

		try {
			String url = "https://www.cricbuzz.com/cricket-match/live-scores";

			// is using the Jsoup library in Java to connect to a specified URL (web
			// address) and retrieve the content of that web page.
			Document document = Jsoup.connect(url).get();

			//we select this div because it repeat for all matches
			Elements liveScoreElements = document.select("div.cb-mtch-lst.cb-tms-itm");

			for (Element match : liveScoreElements) {

				//HashMap<String, String> liveMatchInfo = new LinkedHashMap<>();
				String teamsHeading = match.select("h3.cb-lv-scr-mtch-hdr").select("a").text();
				String matchNumberVenue = match.select("span").text();
				Elements matchBatTeamInfo = match.select("div.cb-hmscg-bat-txt");
				String battingTeam = matchBatTeamInfo.select("div.cb-hmscg-tm-nm").text();
				String score = matchBatTeamInfo.select("div.cb-hmscg-tm-nm+div").text();
				Elements bowlTeamInfo = match.select("div.cb-hmscg-bwl-txt");
				String bowlTeam = bowlTeamInfo.select("div.cb-hmscg-tm-nm").text();
				String bowlTeamScore = bowlTeamInfo.select("div.cb-hmscg-tm-nm+div").text();
				String textLive = match.select("div.cb-text-live").text();
				String textComplete = match.select("div.cb-text-complete").text();

				// getting match link
				String matchLink = match.select("a.cb-lv-scrs-well.cb-lv-scrs-well-live").attr("href").toString();

				Match match1 = new Match();

				match1.setTeamHeading(teamsHeading);
				match1.setMatchNumberVenue(matchNumberVenue);
				match1.setBattingTeam(battingTeam);
				match1.setBattingTeamScore(score);
				match1.setBowlTeam(bowlTeam);
				match1.setBowlTeamScore(bowlTeamScore);
				match1.setLiveText(textLive);
				match1.setMatchLink(matchLink);
				match1.setTextComplete(textComplete);
				match1.setMatchStatus();

				matches.add(match1);

                //Update the match in database

				updateMatch(match1);

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return matches;
	}

	private void updateMatch(Match match1) {

		Match match = this.matchRepo.findByTeamHeading(match1.getTeamHeading()).orElse(null);
		if (match == null) {
			this.matchRepo.save(match1);
		} else {

			match1.setMatchId(match.getMatchId());
			this.matchRepo.save(match1);
		}

	}

	@Override
	public List<Match> getAllMatches() {
		return this.matchRepo.findAll();
	}
}
