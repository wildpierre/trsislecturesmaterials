/*
 * this code is available under GNU GPL v3
 * https://www.gnu.org/licenses/gpl-3.0.en.html
 */
package info.stepanoff.trsis.samples.controller;

import info.stepanoff.trsis.samples.db.model.Batch;
import info.stepanoff.trsis.samples.service.BatchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class BatchController {

    @Autowired
    BatchService batchService;

    @RequestMapping(value = "/batches/{id}", method = RequestMethod.GET)
    @ModelAttribute("batches")
    public ModelAndView batches(@PathVariable("id") Integer id, Model model) {

        if (!model.containsAttribute("batch")) {
            Batch newBatch = new Batch();
            newBatch.setSchool(id);
            model.addAttribute("batch", newBatch);
        }

//        if (!model.containsAttribute("school_id")) {
//            model.addAttribute("school_id", id);
//        }

        ModelAndView mav = new ModelAndView();
        mav.setViewName("batches");
        Iterable<Batch> batches = batchService.findBySchool(id);
        if (batches.iterator() != null) {
            log.info("for school_id" + id + " found " + (batches.iterator().hasNext() ? "non-empty  iterator" : "empty iterator"));
        } else {
            log.error("batches.iterator() is null! for school=" + id);
        }

        mav.addObject("batches", batches);
        mav.addObject("school_id", id);
        log.info("MaV contains school_id="+id);
        return mav;
    }

    @RequestMapping(value = "/batches/save", method = RequestMethod.GET)
    public String saveStudent(Model model) {
        model.addAttribute("batch", new Batch());
        return "batches";
    }

    @RequestMapping(value = "/batches", method = RequestMethod.POST)
    public ModelAndView saveStudent(@ModelAttribute Batch batch, BindingResult errors, Model model) {

        log.info("Saving batch " + batch);
        batchService.save(batch);
        return (batches(batch.getSchool(), model));
    }
}
