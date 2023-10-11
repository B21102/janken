package oit.is.z2174.kaizi.janken.controller;

import java.security.Principal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import oit.is.z2174.kaizi.janken.model.Janken;
import oit.is.z2174.kaizi.janken.model.Entry;

@Controller
public class JankenController {
  @Autowired
  private Entry room;
  // @GetMapping("/janken")
  // public String janken() {
  // return "janken.html";
  // }

  @PostMapping("/janken")
  public String janken(@RequestParam String name, ModelMap model) {
    model.addAttribute("name", name);
    room.addUser(name);
    return "janken.html";
  }

  @GetMapping("/janken")
  public String sample38(Principal prin, ModelMap model) {
    String loginUser = prin.getName();
    this.room.addUser(loginUser);
    model.addAttribute("room", this.room);

    return "janken.html";
  }

  @GetMapping("/game")
  public String katimake(@RequestParam int n, ModelMap model) {
    int c = 1;
    Janken player = new Janken(n, c, null);
    if (n == 1) {
      model.addAttribute("player", "Gu");
    } else if (n == 2) {
      model.addAttribute("player", "Cyoki");
    } else {
      model.addAttribute("player", "Pa");
    }
    if (c == 1) {
      model.addAttribute("cpu", "Gu");
    } else if (c == 2) {
      model.addAttribute("cpu", "Cyoki");
    } else {
      model.addAttribute("cpu", "Pa");
    }

    if (player.getkatimake() == -1) {
      model.addAttribute("hantei", "You Lose!");
    } else if (player.getkatimake() == 1) {
      model.addAttribute("hantei", "You Win!");
    } else {
      model.addAttribute("hantei", "Aiko");
    }

    return "janken.html";
  }
}
