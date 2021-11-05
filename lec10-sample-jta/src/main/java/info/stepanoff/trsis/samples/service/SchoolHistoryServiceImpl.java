/*
 * this code is available under GNU GPL v3
 * https://www.gnu.org/licenses/gpl-3.0.en.html
 */
package info.stepanoff.trsis.samples.service;

import info.stepanoff.trsis.samples.db.dao.SchoolHistoryRepository;
import info.stepanoff.trsis.samples.db.dto.SchoolHistoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import info.stepanoff.trsis.samples.db.model.SchoolHistory;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;

@Service
public class SchoolHistoryServiceImpl implements SchoolHistoryService {    

    @Autowired
    private SchoolHistoryRepository schoolHistoryRepository;

     @Autowired
    private ModelMapper modelMapper;   

    @Override
    public SchoolHistory add(String userName, Integer schoolId, Integer number, String name, String operation) {
        return schoolHistoryRepository.save(new SchoolHistory(userName, schoolId, number, name, operation));
    }

    @Override
    public List<SchoolHistory> findAllByOrderBySchhIdDesc() {
        return schoolHistoryRepository.findAllByOrderBySchhIdDesc();
    }

    @Override
    public SchoolHistoryDTO convertToDTO(SchoolHistory schoolHistory) {        
        SchoolHistoryDTO schoolHistoryDTO = modelMapper.map(schoolHistory, SchoolHistoryDTO.class);        
        return schoolHistoryDTO;
    }
    
    @Override
    public List<SchoolHistoryDTO> convertToDTO(List<SchoolHistory> schoolHistories) {        
        return schoolHistories.stream().map(this::convertToDTO).collect(Collectors.toList());
    }    
    
}
