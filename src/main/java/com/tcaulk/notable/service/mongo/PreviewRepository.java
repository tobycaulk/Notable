package com.tcaulk.notable.service.mongo;

import com.tcaulk.notable.model.preview.Preview;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PreviewRepository extends MongoRepository<Preview, String> {
}
