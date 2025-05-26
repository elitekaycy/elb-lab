package com.elitekaycy.wordle.controller;

import com.elitekaycy.wordle.model.Game;
import com.elitekaycy.wordle.model.Guess;
import com.elitekaycy.wordle.service.GameService;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
public class GameController {

  private final GameService gameService;

  private Game currentGame;
  private final List<Guess> guessHistory = new ArrayList<>();

  public GameController(GameService gameService) {
    this.gameService = gameService;
    this.currentGame = gameService.startNewGame();
  }

  @GetMapping("/")
  public String home(Model model) {
    model.addAttribute("guesses", guessHistory);
    model.addAttribute("solved", currentGame.isSolved());
    model.addAttribute("maxAttempts", 5);
    model.addAttribute("attempts", currentGame.getAttempts());
    model.addAttribute("wordToGuess", currentGame.getWordToGuess());
    return "index";
  }

  @PostMapping("/guess")
  public String guess(@RequestParam String guess) {
    if (guess == null
        || guess.length() != 5
        || currentGame.isSolved()
        || currentGame.getAttempts() >= 6) {
      return "redirect:/";
    }

    String feedback = gameService.checkGuess(guess.toLowerCase(), currentGame.getWordToGuess());
    guessHistory.add(new Guess(guess.toUpperCase(), feedback));

    if ("GGGGG".equals(feedback)) {
      currentGame.setSolved(true);
    }

    currentGame.setAttempts(currentGame.getAttempts() + 1);

    return "redirect:/";
  }

  @PostMapping("/restart")
  public String restart() {
    this.currentGame = gameService.startNewGame();
    guessHistory.clear();
    return "redirect:/";
  }
}
