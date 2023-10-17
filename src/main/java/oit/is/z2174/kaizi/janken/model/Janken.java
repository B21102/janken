package oit.is.z2174.kaizi.janken.model;

public class Janken {

  int player;
  int cpu;
  int katimake;

  String name;

  // p1グーp2チョキp3パー

  public Janken(int p, int c) {
    katimake = this.hantei(p, c);
  }

  public Janken(String n) {
    this.name = n;
  }

  public Janken(int p, int c, String n) {
    katimake = this.hantei(p, c);
    this.name = n;
  }

  public int hantei(int p, int c) {
    if (p == 1) {
      if (c == 1) {
        return 0;
      } else if (c == 2) {
        return 1;
      } else {
        return -1;
      }
    } else if (p == 2) {
      if (c == 1) {
        return -1;
      } else if (c == 2) {
        return 0;
      } else {
        return 1;
      }
    } else if (p == 3) {
      if (c == 1) {
        return 1;
      } else if (c == 2) {
        return -1;
      } else {
        return 0;
      }
    }
    return 0;
  }

  public int getkatimake() {
    return this.katimake;

  }

  public String getname() {
    return this.name;
  }

  public void setname(String name) {
    this.name = name;
  }
}
