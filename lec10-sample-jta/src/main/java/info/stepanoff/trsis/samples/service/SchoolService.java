/*
 * this code is available under GNU GPL v3
 * https://www.gnu.org/licenses/gpl-3.0.en.html
 */
package info.stepanoff.trsis.samples.service;

import info.stepanoff.trsis.samples.db.dto.SchoolDTO;
import info.stepanoff.trsis.samples.db.model.School;
import java.util.List;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public interface SchoolService {

    List<School> listAll();

    void delete(Integer id);
    
    School findById(Integer id);
    
    School add(Integer number, String name);
    
    SchoolDTO convertToDTO (School school);
    
    List<SchoolDTO> convertToDTO (List<School> schools);

}
