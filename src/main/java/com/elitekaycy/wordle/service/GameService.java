package com.elitekaycy.wordle.service;

import com.elitekaycy.wordle.model.Game;
import java.util.List;
import java.util.Random;
import org.springframework.stereotype.Service;

@Service
public class GameService {

  private static final List<String> WORDS =
      List.of(
          "crane", "plant", "shine", "globe", "trace", "flame", "brick", "apple", "table", "chair",
          "light", "house", "bread", "cloud", "stone", "river", "glass", "water", "paper", "mouse",
          "heart", "music", "dream", "grape", "lemon", "peach", "spice", "sugar", "green", "speak",
          "voice");

  private final Random random = new Random();

  public Game startNewGame() {
    String word = WORDS.get(random.nextInt(WORDS.size()));
    return new Game(word, 0, false);
  }

  public String checkGuess(String guess, String wordToGuess) {
    guess = guess.toLowerCase();
    char[] feedback = new char[5];
    char[] wordChars = wordToGuess.toCharArray();
    boolean[] matched = new boolean[5];

    for (int i = 0; i < 5; i++) {
      if (guess.charAt(i) == wordChars[i]) {
        feedback[i] = 'G';
        matched[i] = true;
      } else {
        feedback[i] = 'B';
      }
    }

    for (int i = 0; i < 5; i++) {
      if (feedback[i] == 'G') continue;

      for (int j = 0; j < 5; j++) {
        if (!matched[j] && guess.charAt(i) == wordChars[j]) {
          feedback[i] = 'Y';
          matched[j] = true;
          break;
        }
      }
    }

    return new String(feedback);
  }
}
