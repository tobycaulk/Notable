package com.tcaulk.notable.service.notableapi;

import com.tcaulk.notable.model.authorization.AuthorizationCachePackage;
import com.tcaulk.notable.model.preview.Preview;
import com.tcaulk.notable.model.request.notableapi.AddPreviewRequest;
import com.tcaulk.notable.model.response.ResponseStatus;
import com.tcaulk.notable.model.response.notableapi.AddPreviewResponse;
import com.tcaulk.notable.service.mongo.PreviewRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class NotableAPI {
    private static final Logger log = Logger.getLogger(NotableAPI.class);

    private static final int PREVIEW_THREAD_COUNT = 20;

    private ScheduledExecutorService executorService;
    private PreviewRepository previewRepository;

    @Autowired
    public NotableAPI(PreviewRepository previewRepository) {
        this.previewRepository = previewRepository;
        this.executorService = Executors.newScheduledThreadPool(PREVIEW_THREAD_COUNT);
    }

    public AddPreviewResponse addPreview(AddPreviewRequest request, AuthorizationCachePackage authorizationCachePackage) {
        AddPreviewResponse response = new AddPreviewResponse();
        //Should validate that track is valid
        Preview insertedPreview = previewRepository.save(request.getPreview());
        if(insertedPreview != null) {
            log.debug("Inserted Preview with trackId[" + insertedPreview.getSpotifyTrackId() + "]");
            executorService.schedule(() -> {
                previewRepository.delete(insertedPreview);
                log.debug("Removed preview with trackId[" + insertedPreview.getSpotifyTrackId() + "]");
            }, 30, TimeUnit.SECONDS);
            response.setResponseStatus(ResponseStatus.SUCCESS);
        } else {
            log.error("Failed to insert Preview with trackId[" + insertedPreview.getSpotifyTrackId() + "]");
            response.setResponseStatus(ResponseStatus.FAILURE);
        }

        return response;
    }
}
