package model;

import java.util.Random;

public class Controller {
    private GameBoard board;
    private Player player1;
    private Player player2;
    private Player player3;
    private Player currentPlayer;
    private BinarySearchTree bst;

    public Controller(){
        this.bst=new BinarySearchTree();
    }

    public void generateBoard(int m, int n,int s, int l){
        this.currentPlayer=player1;
        this.board=new GameBoard(m,n,s,l);
        board.generate();
        board.generateSnakes();
        board.generateLadders();
        board.registerPlayers(this.player1,this.player2,this.player3);
    }

    public String printBoard(){
        board.massageDefault();
        return board.printBoard();
    }

    public void registerPlayers(String s1,String s2, String s3){
        Player p1 = new Player(s1);
        Player p2 = new Player(s2);
        Player p3 = new Player(s3);
        this.player1=p1;
        this.player2=p2;
        this.player3=p3;
        this.currentPlayer=p1;
    }
    public boolean movePlayer(int dice){
        boolean keep= board.movePlayer(dice,currentPlayer);
        if(!keep){
            double score=(600-board.getTime().getSeconds())/6;
            currentPlayer.setScore(currentPlayer.getScore()+score);
            this.bst.insert(currentPlayer);
            System.out.println("The player "+currentPlayer.getSymbol()+" has won the game with a score of "+score);
            return false;
        }
        if(currentPlayer==player1){
            currentPlayer=player2;
        }else if(currentPlayer==player2){
            currentPlayer=player3;
        }else if(currentPlayer==player3){
            currentPlayer=player1;
        }

        return true;
    }

    public String printPlayerScore(){
        return bst.printReverse();
    }
    public String printSnakesAndLadders(){
        board.messageSnakeAndStairs();
        return board.printBoard();
    }

    public GameBoard getBoard() {
        return board;
    }

    public void setBoard(GameBoard board) {
        this.board = board;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public Player getPlayer3() {
        return player3;
    }

    public void setPlayer3(Player player3) {
        this.player3 = player3;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public BinarySearchTree getBst() {
        return bst;
    }

    public void setBst(BinarySearchTree bst) {
        this.bst = bst;
    }
}
