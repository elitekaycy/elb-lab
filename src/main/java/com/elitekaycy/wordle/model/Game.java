package com.elitekaycy.wordle.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Game {
  private String wordToGuess;
  private int attempts;
  private boolean solved;

  public Game(String wordToGuess) {
    this.wordToGuess = wordToGuess;
    this.attempts = 0;
    this.solved = false;
  }
}
