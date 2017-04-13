package com.tcaulk.notable.controller;

import com.tcaulk.notable.model.authorization.AuthorizationToken;
import com.tcaulk.notable.model.response.base.BaseResponse;
import com.tcaulk.notable.model.response.spotifyapi.GetSpotifyRecommendationsResponse;
import com.tcaulk.notable.service.spotifyapi.SpotifyAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SpotifyAPIController {

    private SpotifyAPIService spotifyAPIService;

	@Autowired
	public SpotifyAPIController(SpotifyAPIService spotifyAPIService) {
        this.spotifyAPIService = spotifyAPIService;
    }

    @RequestMapping(value="/recommendations", method=RequestMethod.GET)
	public @ResponseBody BaseResponse<GetSpotifyRecommendationsResponse> getRecommendations() {
        return spotifyAPIService.getSpotifyRecommendations();
	}
}