package com.tcaulk.notable.controller;

import com.tcaulk.notable.model.request.notableapi.GetRecommendationsRequest;
import com.tcaulk.notable.model.request.notableapi.base.BaseNotableRequest;
import com.tcaulk.notable.model.response.httpbase.BaseResponse;
import com.tcaulk.notable.model.response.notableapi.base.BaseNotableResponse;
import com.tcaulk.notable.model.response.spotifyapi.GetSpotifyRecommendationsResponse;
import com.tcaulk.notable.service.spotifyapi.SpotifyAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class NotableController {

    private SpotifyAPI spotifyAPI;

	@Autowired
	public NotableController(SpotifyAPI spotifyAPI) {
        this.spotifyAPI = spotifyAPI;
    }

    @RequestMapping(value="/recommendations", method=RequestMethod.POST)
	public @ResponseBody BaseNotableResponse<GetSpotifyRecommendationsResponse> getRecommendations(@RequestBody BaseNotableRequest<GetRecommendationsRequest> request) {
        return spotifyAPI.getSpotifyRecommendations(request);
	}
}