/*
 * this code is available under GNU GPL v3
 * https://www.gnu.org/licenses/gpl-3.0.en.html
 */
package info.stepanoff.trsis.samples.service;

import info.stepanoff.trsis.samples.db.dao.BatchRepository;
import info.stepanoff.trsis.samples.db.dto.BatchDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import info.stepanoff.trsis.samples.db.model.Batch;
import info.stepanoff.trsis.samples.db.model.School;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;

@Service
public class BatchServiceImpl implements BatchService {    
    
    @Autowired
    private ModelMapper modelMapper;    

    @Autowired
    private BatchRepository batchRepository;

    @Override
    public Iterable<Batch> findBySchool(School school) {
        return batchRepository.findBySchool(school);
    }

    @Override
    public BatchDTO convertToDTO(Batch batch) {        
        BatchDTO batchDTO = modelMapper.map(batch, BatchDTO.class);        
        return batchDTO;
    }
    
    @Override
    public List<BatchDTO> convertToDTO(List<Batch> batches) {        
        return batches.stream().map(this::convertToDTO).collect(Collectors.toList());
    }    
    
}
