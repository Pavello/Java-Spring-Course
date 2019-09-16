package pl.javastart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.javastart.data.TimeDataRepository;
import pl.javastart.model.TimeData;

import java.util.List;

@Controller
public class TimeController {

    private TimeDataRepository timeDataRepository;

    @Autowired
    public TimeController(TimeDataRepository timeDataRepository){
        this.timeDataRepository = timeDataRepository;
    }

    @PostMapping("/save")
    public String saveScore(@ModelAttribute TimeData timeData){
        timeDataRepository.save(timeData);
        return "redirect:/";
    }

    @GetMapping("/show")
    public String showAll(Model model){
        List<TimeData> timeDataList = timeDataRepository.findAll();
        model.addAttribute("timeDatalist", timeDataList);
        return "scores";
    }
}
