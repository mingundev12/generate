package com.generate.control;

import com.generate.service.GenerationLogService;
import com.generate.service.GeneratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class GeneratorController {
    private final GenerationLogService generationLogService;
    private final GeneratorService generatorService;

    @GetMapping("/generator")
    public String generatorList(@RequestParam("id") long id, Model model) {
        model.addAttribute("generatorName", generatorService.getGeneratorName(id));
        model.addAttribute("list", generatorService.getSourceList(id));

        return "generator/list";
    }
}
