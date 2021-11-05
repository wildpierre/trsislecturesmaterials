/*
 * this code is available under GNU GPL v3
 * https://www.gnu.org/licenses/gpl-3.0.en.html
 */
package info.stepanoff.trsis.samples.controller;

import info.stepanoff.trsis.samples.service.BatchService;
import info.stepanoff.trsis.samples.service.SchoolHistoryService;
import info.stepanoff.trsis.samples.service.SchoolService;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HistoryController {

    @Autowired
    SchoolHistoryService schoolHistoryService;

    @RequestMapping(value = "/histories/{num}", method = RequestMethod.GET)
    public ModelAndView allHistories(@PathVariable("num") Integer num) {
        ModelAndView mav = new ModelAndView("histories");
        mav.addObject("histories", schoolHistoryService
                .convertToDTO(schoolHistoryService
                        .findAllByOrderBySchhIdDesc()
                        .stream()
                        .limit(num)
                        .collect(Collectors.toList())));
        return mav;
    }
    
    @RequestMapping(value = "/histories", method = RequestMethod.GET)
    public ModelAndView allHistories() {
        ModelAndView mav = new ModelAndView("histories");
        mav.addObject("histories", schoolHistoryService
                .convertToDTO(schoolHistoryService
                        .findAllByOrderBySchhIdDesc()
                        .stream()
                        .limit(100)
                        .collect(Collectors.toList())));
        return mav;
    }
}
