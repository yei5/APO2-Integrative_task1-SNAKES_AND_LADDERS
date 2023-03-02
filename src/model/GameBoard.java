package model;
import java.util.Random;

public class GameBoard {
    public static Random r = new Random();

    private Square head;
    private Square tail;
    private int columns;
    private int filas;
    private int snakes;
    private int ladders;
    private int length;


    public GameBoard(int columns, int filas, int snakes, int ladders) {
        this.columns = columns;
        this.filas = filas;
        this.snakes = snakes;
        this.ladders = ladders;
        this.length= columns*filas;
    }

    public void generate(){
        generate(length);
    }

    public void generate(int lastPosition){
        if (lastPosition==0){
            return;
        }else{
            Square aux = new Square(lastPosition);
            addFirst(aux);
            generate(lastPosition-1);
        }
    }

    public void addFirst(Square n){
        if(head==null&&tail==null){
            this.head = n;
            this.tail = n;

        }else {
            n.setNext(head);
            head.setPrevious(n);
            this.head = n;
        }
    }

    public String printBoard(){
        String msg="";

        return printBoard(this.tail,msg);

    }

    public String printBoard(Square current, String msg){
        if (head.getNext()==tail){
            return getString(head);
        }
        if (current==head){
            msg+= "";
            return msg;
        }else if (columns%2==0){
            if (current.getValue()%(columns*2)==0) msg+= printInOrder(current)+"\n";
            else if(current.getValue()%columns==0)msg+=printReverse(current)+"\n";
        }else {
                if (current.getValue() % columns == 0) {
                    if (current.getValue() % 2 == 0)
                        msg += printInOrder(current) + "\n";

                    else msg += printReverse(current) + "\n";
                }
            }
        return printBoard(current.getPrevious(),msg);
    }

    public String printReverse(Square current){

        if (current==head||current.getPrevious().getValue()%columns==0){
            return getString(current);
        }else{
            return  printReverse(current.getPrevious())+getString(current);
        }

    }

    public String printInOrder(Square current){
        if (current==head||current.getPrevious().getValue()%columns==0){
            return getString(current);
        }else{
            return getString(current)+printInOrder(current.getPrevious()) ;
        }

    }

    //printMessage with the correct format with max length of 8
    private String getString(Square current){
        if(current.getMessage().length()==8){
            return "[ "+current.getMessage()+ " ] ";
        }
        if(current.getMessage().length()==7){
            return "[  "+current.getMessage()+ " ] ";
        }else if(current.getMessage().length()==6){
            return "[  "+current.getMessage()+ "  ] ";
        }else if(current.getMessage().length()==5){
            return "[  "+current.getMessage()+ "   ] ";
        }else if(current.getMessage().length()==4){
            return "[  "+current.getMessage()+ "    ] ";
        }else if(current.getMessage().length()==3){
            return "[  "+current.getMessage()+ "     ] ";
        }else if(current.getMessage().length()==2){
            return "[  "+current.getMessage()+ "      ] ";
        }else{
            return "[  "+current.getMessage()+ "       ] ";
        }
    }




    public Square searchSquare (int value){
        return searchSquare(value, head);
    }

    private Square searchSquare(int value, Square current){
        if (current ==tail&&current.getValue()!=value) return null;
        if (current.getValue()==value) return current;
        return searchSquare(value, current.getNext());
    }

    public Square getHead() {
        return head;
    }

    public void setHead(Square head) {
        this.head = head;
    }

    public Square getTail() {
        return tail;
    }

    public void setTail(Square tail) {
        this.tail = tail;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public int getFilas() {
        return filas;
    }

    public void setFilas(int filas) {
        this.filas = filas;
    }

    public int getSnakes() {
        return snakes;
    }

    public void setSnakes(int snakes) {
        this.snakes = snakes;
    }

    public int getLadders() {
        return ladders;
    }

    public void setLadders(int ladders) {
        this.ladders = ladders;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
