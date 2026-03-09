package com.generate.control;

import com.generate.service.GenerateSourceService;
import com.generate.service.GenerationLogService;
import com.generate.service.GeneratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
@RequiredArgsConstructor
public class GeneratorController {
    private final GenerationLogService generationLogService;
    private final GenerateSourceService generateSourceService;
    private final GeneratorService generatorService;

    @GetMapping("/generator")
    public String generatorList(@RequestParam("id") long id, Model model) {
        model.addAttribute("generatorName", generatorService.getGeneratorName(id));
        model.addAttribute("list", generateSourceService.getSourceList(id));

        return "generator/list";
    }

    @GetMapping("/detail")
    public String showCalender(@RequestParam("id") long id, Model model) {
        model.addAttribute("addedDate", generateSourceService.getAddedDate(id));
        model.addAttribute("today", LocalDate.now());

        return "generator/detail";
    }

    @PostMapping("/detail")
    public String showDetail(@RequestParam("id") long id, @RequestParam("date") LocalDate date,
                             Model model) {
        model.addAttribute("dailyLog", generationLogService.getLogList(id, date));

        return "redirect:/detail?id=" + id;
    }
}
