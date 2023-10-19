package oit.is.z2174.kaizi.janken.controller;

import java.security.Principal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import oit.is.z2174.kaizi.janken.model.Janken;
import oit.is.z2174.kaizi.janken.model.Entry;
import oit.is.z2174.kaizi.janken.model.User;
import oit.is.z2174.kaizi.janken.model.UserMapper;
import oit.is.z2174.kaizi.janken.model.Match;
import oit.is.z2174.kaizi.janken.model.MatchMapper;

@Controller
public class JankenController {
  @Autowired
  Entry room;
  User users;
  // @GetMapping("/janken")
  // public String janken() {
  // return "janken.html";
  // }

  @Autowired
  UserMapper userMapper;

  @Autowired
  MatchMapper matchMapper;

  // @PostMapping("/janken")
  // public String janken(@RequestParam String name, ModelMap model) {

  //   model.addAttribute("name", name);
  //   room.addUser(name);
  //   return "janken.html";
  // }

  @GetMapping("/janken")
  public String entryUser(ModelMap model) {
    ArrayList<User> users = userMapper.selectAllUsers();
    ArrayList<Match> matches = matchMapper.selectAllMatches();
    model.addAttribute("matches", matches);
    model.addAttribute("users", users);

    return "janken.html";
  }

  // @GetMapping("/step5")
  // public String step5() {
  // return "janken.html";
  // }

  // @PostMapping("/select")
  // public String selsect(@RequestParam String userName, ModelMap model) {
  //   ArrayList<User> users5 = userMapper.selectAllByUserName(userName);
  //   model.addAttribute("users5", users5);
  //   return "janken.html";
  // }

  @GetMapping("/match")
  public String match(@RequestParam Integer id, ModelMap model) {
    User match = userMapper.selectAllById(id);
    model.addAttribute("match", match);
    return "match.html";
  }

  @GetMapping("/fight")
  public String katimake(@RequestParam Integer n, @RequestParam Integer id, ModelMap model, Principal prin) {
    String loginyouser = prin.getName();
    User user = userMapper.selectByUserName(loginyouser);
    Match matches = new Match();
    matches.setUser1(user.getId());
    matches.setUser2(id);
    matches.setUser2Hand("Gu");

    if (n == 1) {
      matches.setUser1Hand("Gu");
    } else if (n == 2) {
      matches.setUser1Hand("Cyoki");
    } else {
      matches.setUser1Hand("Pa");
    }

    // int c = (int) Math.random() * 3;
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

    User match = userMapper.selectAllById(id);
    model.addAttribute("match", match);
    matchMapper.insertMatch(matches);

    return "match.html";
  }
}
