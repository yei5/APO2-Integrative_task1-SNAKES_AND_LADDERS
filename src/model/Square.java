package model;

public class Square {

    private Square next;
    private Square previous;
    private int value;
    private String id;
    private Player player1;
    private Player player2;
    private Player player3;
    private String message;
    private boolean isHead;
    private boolean isBottom;


    public Square(int value){
        this.value = value;
        this.message=value+"";
        this.id="";
        this.isHead=false;
    }

    public Square getNext() {
        return next;
    }

    public void setNext(Square next) {
        this.next = next;
    }

    public Square getPrevious() {
        return previous;
    }

    public void setPrevious(Square previous) {
        this.previous = previous;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean getIsHead() {
        return isHead;
    }

    public void setIsHead(boolean head) {
        isHead = head;
    }

    public boolean isHead() {
        return isHead;
    }

    public void setHead(boolean head) {
        isHead = head;
    }

    public boolean getIsBottom() {
        return isBottom;
    }

    public void setIsBottom(boolean bottom) {
        isBottom = bottom;
    }
}
