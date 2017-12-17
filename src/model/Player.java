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

    public void setPts(int pts) {
        this.pts += pts;
    }

    public void setNegativePts (int pts) {
        if (this.pts - pts < 0) {
            this.pts = 0;
        } else {
            this.pts -= pts;
        }
    }
}
