/*
 * this code is available under GNU GPL v3
 * https://www.gnu.org/licenses/gpl-3.0.en.html
 */
package info.stepanoff.trsis.samples.service;

import info.stepanoff.trsis.samples.db.dao.BatchRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import info.stepanoff.trsis.samples.db.model.Batch;

@Service
public class BatchServiceImpl implements BatchService {

    private final static Logger log = Logger.getLogger(SchoolServiceImpl.class);

    @Autowired
    private BatchRepository batchRepository;

    @Override
    public Iterable<Batch> findBySchool(Integer school) {
        return batchRepository.findBySchool(school);
    }

}
