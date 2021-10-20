package info.stepanoff.trsis.samples.service;

import info.stepanoff.trsis.samples.db.dao.BatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import info.stepanoff.trsis.samples.db.model.Batch;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BatchServiceImpl implements BatchService {

    @Autowired
    private BatchRepository batchRepository;

    @Override
    public Iterable<Batch> findBySchool(Integer school) {
        return batchRepository.findBySchool(school);
    }

    @Override
    public void save(Batch batch) {
        batchRepository.save(batch);
    }

}
