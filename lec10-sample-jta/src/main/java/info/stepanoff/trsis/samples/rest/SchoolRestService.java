/*
 * this code is available under GNU GPL v3
 * https://www.gnu.org/licenses/gpl-3.0.en.html
 */
package info.stepanoff.trsis.samples.rest;

import info.stepanoff.trsis.samples.db.model.School;
import info.stepanoff.trsis.samples.security.NeedRole;
import info.stepanoff.trsis.samples.service.LoginService;
import info.stepanoff.trsis.samples.service.MyLogin;
import info.stepanoff.trsis.samples.service.SchoolHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import info.stepanoff.trsis.samples.service.SchoolService;
import java.security.Principal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RestController
@RequestMapping("/")
public class SchoolRestService {

    @Autowired
    private SchoolService schoolService;

    @Autowired
    private SchoolHistoryService schoolHistoryService;

    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "/public/rest/schools", method = RequestMethod.GET)
    public ResponseEntity<Object> browse() {
        return ResponseEntity.ok(schoolService.convertToDTO(schoolService.listAll()));
    }

    @RequestMapping(value = "/public/rest/schools/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> browseOne(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(schoolService.convertToDTO(schoolService.findById(id)));
    }

    @Transactional
    @NeedRole(roleRegex = "ADMIN_USER")
    @RequestMapping(value = "/public/rest/schools/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> delete(@PathVariable("id") Integer id, Principal principal) {
        
        
        School school = schoolService.findById(id);
        if (school==null)
            return ResponseEntity.notFound().build();
            
        schoolService.delete(id);

        //MyLogin activeUser = (MyLogin) ((Authentication) principal).getPrincipal();
        User activeUser = (User) ((Authentication) principal).getPrincipal();
        String username = activeUser.getUsername();

        schoolHistoryService.add(username, school.getId(), school.getNumber(), school.getName(), "DELETE");
        
        return ResponseEntity.ok().build();
    }

    @Transactional
    @NeedRole(roleRegex = "ADMIN_USER")
    @RequestMapping(value = "/public/rest/schools/{number}/{name}", method = RequestMethod.POST)
    public ResponseEntity<Object> add(@PathVariable("number") Integer number, @PathVariable("name") String name, Principal principal) {
        School school = schoolService.add(number, name);

        log.info("School added");
        //MyLogin activeUser = (MyLogin) ((Authentication) principal).getPrincipal();
        User activeUser = (User) ((Authentication) principal).getPrincipal();
        String username = activeUser.getUsername();

        schoolHistoryService.add(username, school.getId(), school.getNumber(), school.getName(), "ADD");

        log.info("History record added");
        return ResponseEntity.ok(school);
    }

}
