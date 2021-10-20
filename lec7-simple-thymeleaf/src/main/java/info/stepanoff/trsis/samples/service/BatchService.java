package info.stepanoff.trsis.samples.service;

import info.stepanoff.trsis.samples.db.model.Batch;

public interface BatchService {

    Iterable<Batch> findBySchool(Integer school);

    void save(Batch batch);
}
