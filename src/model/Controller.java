package model;

public class Controller {
    private GameBoard board;

    public Controller(){

    }

    public String generateBoard(int m, int n,int s, int l){
        this.board=new GameBoard(m,n,s,l);
        board.generate();
        return board.printBoard();
    }

    public GameBoard getBoard() {
        return board;
    }

    public void setBoard(GameBoard board) {
        this.board = board;
    }
}
