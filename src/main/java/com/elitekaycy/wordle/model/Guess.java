package com.elitekaycy.wordle.model;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Guess {
  private String word;
  private String feedback;

  public String getColorForPosition(int i) {
    char c = feedback.charAt(i);
    if (c == 'G') return "green";
    if (c == 'Y') return "yellow";
    return "gray";
  }

  public List<String> getColors() {
    List<String> colors = new ArrayList<>();
    for (int i = 0; i < feedback.length(); i++) {
      char c = feedback.charAt(i);
      if (c == 'G') colors.add("green");
      else if (c == 'Y') colors.add("yellow");
      else colors.add("gray");
    }
    return colors;
  }
}
