/*
 * this code is available under GNU GPL v3
 * https://www.gnu.org/licenses/gpl-3.0.en.html
 */
package info.stepanoff.trsis.samples.controller;

import info.stepanoff.trsis.samples.db.model.School;
import info.stepanoff.trsis.samples.service.BatchService;
import info.stepanoff.trsis.samples.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BatchController {

    @Autowired
    BatchService batchService;

    @Autowired
    SchoolService schoolService;

    @RequestMapping(value = "/batches/{id}", method = RequestMethod.GET)
    public ModelAndView batches(@PathVariable("id") Integer id) {
        ModelAndView mav = new ModelAndView("batches");
        mav.addObject("batches", batchService.convertToDTO(schoolService.findById(id).getBatchesList()));
        return mav;
    }
}
