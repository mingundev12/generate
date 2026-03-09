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
public class MainController {
    private final GeneratorService generatorService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("list", generatorService.getGeneratorList());
        return "index";
    }
}
