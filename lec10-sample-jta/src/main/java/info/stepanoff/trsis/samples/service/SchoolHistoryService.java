/*
 * this code is available under GNU GPL v3
 * https://www.gnu.org/licenses/gpl-3.0.en.html
 */
package info.stepanoff.trsis.samples.service;

import info.stepanoff.trsis.samples.db.dto.SchoolHistoryDTO;
import info.stepanoff.trsis.samples.db.model.SchoolHistory;
import java.util.List;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public interface SchoolHistoryService {

    SchoolHistory add(String userName, Integer schoolId, Integer number, String name, String operation);

    List<SchoolHistory> findAllByOrderBySchhIdDesc();

    SchoolHistoryDTO convertToDTO(SchoolHistory schoolHistory);

    List<SchoolHistoryDTO> convertToDTO(List<SchoolHistory> schoolHistories);

}
