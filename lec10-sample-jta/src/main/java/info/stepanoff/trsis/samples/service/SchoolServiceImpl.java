/*
 * this code is available under GNU GPL v3
 * https://www.gnu.org/licenses/gpl-3.0.en.html
 */
package info.stepanoff.trsis.samples.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import info.stepanoff.trsis.samples.db.dao.SchoolRepository;
import info.stepanoff.trsis.samples.db.dto.SchoolDTO;
import info.stepanoff.trsis.samples.db.model.School;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;

@Service
public class SchoolServiceImpl implements SchoolService {

    @Autowired
    private ModelMapper modelMapper;

    private final static Logger log = Logger.getLogger(SchoolServiceImpl.class);

    @Autowired
    private SchoolRepository schoolRepository;

    @Override
    public List<School> listAll() {
        return schoolRepository.findAll();
    }

    @Override
    public void delete(Integer id) {
        schoolRepository.deleteById(id);
    }

    @Override
    public School add(Integer number, String name) {
        return schoolRepository.save(new School(number, name));
    }

    @Override
    public School findById(Integer id) {
        return schoolRepository.findById(id).orElse(null);
    }

    @Override
    public SchoolDTO convertToDTO(School school) {
        SchoolDTO schoolDTO = modelMapper.map(school, SchoolDTO.class);
        return schoolDTO;
    }

    @Override
    public List<SchoolDTO> convertToDTO(List<School> schools) {
        return schools.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

}
