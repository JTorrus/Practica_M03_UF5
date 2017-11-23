package model;

public class Player {
    private String name;
    private int pts;

    public Player(String name) {
        this.name = name;
        this.pts = 0;
    }

    public String getName() {
        return name;
    }

    public int getPts() {
        return pts;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPts(int pts) {
        this.pts = pts;
    }
}
