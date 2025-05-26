package com.elitekaycy.wordle.home;

import java.time.LocalDateTime;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

  @GetMapping("/home")
  public String home(Model model) {
    model.addAttribute("time", LocalDateTime.now().toString());
    return "index";
  }
}
