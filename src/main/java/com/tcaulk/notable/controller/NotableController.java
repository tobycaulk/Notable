package com.tcaulk.notable.controller;

import com.tcaulk.notable.model.request.notableapi.AddPreviewRequest;
import com.tcaulk.notable.model.request.notableapi.GetRecommendationsRequest;
import com.tcaulk.notable.model.request.notableapi.base.BaseNotableRequest;
import com.tcaulk.notable.model.response.notableapi.AddPreviewResponse;
import com.tcaulk.notable.model.response.notableapi.base.BaseNotableResponse;
import com.tcaulk.notable.model.response.spotifyapi.GetSpotifyRecommendationsResponse;
import com.tcaulk.notable.service.notableapi.NotableAPI;
import com.tcaulk.notable.service.spotifyapi.SpotifyAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class NotableController {

    private SpotifyAPI spotifyAPI;
    private NotableAPI notableAPI;
    private ControllerUtil controllerUtil;

	@Autowired
	public NotableController(SpotifyAPI spotifyAPI, NotableAPI notableAPI, ControllerUtil controllerUtil) {
        this.spotifyAPI = spotifyAPI;
        this.notableAPI = notableAPI;
        this.controllerUtil = controllerUtil;
    }

    @RequestMapping(value="/recommendations", method=RequestMethod.POST)
	public @ResponseBody BaseNotableResponse<GetSpotifyRecommendationsResponse> getRecommendations(@RequestBody BaseNotableRequest<GetRecommendationsRequest> request) {
	    return controllerUtil.processRequest(request, spotifyAPI::getSpotifyRecommendations);
	}

	@RequestMapping(value="/addPreview", method=RequestMethod.POST)
    public @ResponseBody BaseNotableResponse<AddPreviewResponse> addPreview(@RequestBody BaseNotableRequest<AddPreviewRequest> request) {
        return controllerUtil.processRequest(request, notableAPI::addPreview);
    }
}