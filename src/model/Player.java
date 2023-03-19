package model;


public class Player {
    private String symbol;
    private double score;
    private Player left;
    private Player right;


    public Player( String symbol) {
        this.symbol = symbol;
        this.score=0;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public Player getLeft() {
        return left;
    }

    public void setLeft(Player left) {
        this.left = left;
    }

    public Player getRight() {
        return right;
    }

    public void setRight(Player right) {
        this.right = right;
    }
}
