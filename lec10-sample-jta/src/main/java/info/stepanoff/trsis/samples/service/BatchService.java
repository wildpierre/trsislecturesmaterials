/*
 * this code is available under GNU GPL v3
 * https://www.gnu.org/licenses/gpl-3.0.en.html
 */
package info.stepanoff.trsis.samples.service;

import info.stepanoff.trsis.samples.db.dto.BatchDTO;
import info.stepanoff.trsis.samples.db.model.Batch;
import info.stepanoff.trsis.samples.db.model.School;
import java.util.List;

public interface BatchService {

    Iterable<Batch> findBySchool(School school);

    public BatchDTO convertToDTO(Batch batch);

    public List<BatchDTO> convertToDTO(List<Batch> batches); 
}
