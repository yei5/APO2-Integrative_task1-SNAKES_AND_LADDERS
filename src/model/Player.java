package model;

public class Player {
    private String id;
    private double score;


    public Player(String id) {
        this.id = id;
        this.score=0;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
