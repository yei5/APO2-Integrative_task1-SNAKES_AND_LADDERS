package model;

import java.util.Random;
import java.time.Duration;
import java.time.Instant;

public class GameBoard {
    public static Random r = new Random();

    private Square head;
    private Square tail;
    private int columns;
    private int rows;
    private int snakes;
    private int ladders;
    private int length;
    private Instant start;
    private Instant end;
    private Duration time;

    public GameBoard(int columns, int rows, int snakes, int ladders) {
        this.columns = columns;
        this.rows = rows;
        this.snakes = snakes;
        this.ladders = ladders;
        this.length = columns * rows;
    }

    public void generate() {
        generate(length);
        this.start = Instant.now();
    }

    public void generate(int lastPosition) {
        if (lastPosition == 0) {
            return;
        } else {
            Square aux = new Square(lastPosition);
            addFirst(aux);
            generate(lastPosition - 1);
        }
    }

    public void addFirst(Square n) {
        if (head == null && tail == null) {
            this.head = n;
            this.tail = n;

        } else {
            n.setNext(head);
            head.setPrevious(n);
            this.head = n;
        }
    }

    public String printBoard() {
        String msg = "";
        return printBoard(this.tail, msg);
    }

    public String printBoard(Square current, String msg) {
        if(current == null) return msg;
        if (head.getNext() == tail) {
            return getString(head);
        }
        if (current == head&&this.columns!=1) {
            msg += "";
            return msg;
        } else if (columns % 2 == 0) {
            if (current.getValue() % (columns * 2) == 0)
                msg += printInOrder(current) + "\n";
            else if (current.getValue() % columns == 0)
                msg += printReverse(current) + "\n";
        } else {
            if (current.getValue() % columns == 0) {
                if (current.getValue() % 2 == 0)
                    msg += printInOrder(current) + "\n";
                else
                    msg += printReverse(current) + "\n";
            }
        }
        return printBoard(current.getPrevious(), msg);
    }

    public String printReverse(Square current) {
        if(current==null) return "";
        if (current == head || current.getPrevious().getValue() % columns == 0) {
            return getString(current);
        } else {
            return printReverse(current.getPrevious()) + getString(current);
        }

    }

    public String printInOrder(Square current) {
        if(current==null) return "";
        if (current == head || current.getPrevious().getValue() % columns == 0) {
            return getString(current);
        } else {
            return getString(current) + printInOrder(current.getPrevious());
        }

    }

    private String getString(Square current) {
        if (current.getMessage().length() == 6) {
            return "[" + current.getMessage() + "]";
        }
        if (current.getMessage().length() == 5) {
            return "[ " + current.getMessage() + "]";
        }
        if (current.getMessage().length() == 4) {
            return "[ " + current.getMessage() + " ]";
        }
        if (current.getMessage().length() == 3) {
            return "[ " + current.getMessage() + "  ]";
        }
        if (current.getMessage().length() == 2) {
            return "[ " + current.getMessage() + "   ]";
        }
        if (current.getMessage().length() == 1) {
            return "[ " + current.getMessage() + "    ]";
        } else {
            return "[ " + current.getMessage() + "     ]";
        }
    }

    public Square searchSquare(int value) {
        return searchSquare(value, head);
    }

    private Square searchSquare(int value, Square current) {
        if (current == tail && current.getValue() != value)
            return null;
        if (current.getValue() == value)
            return current;
        return searchSquare(value, current.getNext());
    }

    public void generateSnakes() {
        generateSnakes(snakes, 65, 0);
    }

    public void generateSnakes(int snake, int ASCIIId, int auxId) {
        if (snake == 0) {
            return;
        }
        if (ASCIIId > 90) {
            ASCIIId = 65;
            auxId++;
        }
        int head = r.nextInt(length - 2) + 2;
        int tail = r.nextInt(head - 1) + 1;

        if (head == tail) {
            generateSnakes(snake, ASCIIId, auxId);
        }
        if (head < tail) {
            int aux = head;
            head = tail;
            tail = aux;
        }
        Square headSquare = searchSquare(head);
        Square tailSquare = searchSquare(tail);
        if (headSquare.getId().equals("") && tailSquare.getId().equals("")) {
            if (auxId == 0) {
                headSquare.setId((char) ASCIIId + "");
                headSquare.setIsHead(true);
                tailSquare.setId((char) ASCIIId + "");
            } else {
                headSquare.setId((char) ASCIIId + "" + auxId);
                headSquare.setIsHead(true);
                tailSquare.setId((char) ASCIIId + "" + auxId);
            }
            generateSnakes(snake - 1, ++ASCIIId, auxId);

        } else {
            generateSnakes(snake, ASCIIId, auxId);
        }
        return;
    }

    public void generateLadders() {
        generateLadders(ladders, 1);
    }

    public void generateLadders(int ladder, int valor) {
        if (ladder == 0) {
            return;
        }
        int top = r.nextInt(length - 2) + 3;
        int bottom = r.nextInt(top - 2) + 2;
        if (top == bottom) {
            generateLadders(ladder, valor);
        }
        if (top < bottom) {
            int aux = top;
            top = bottom;
            bottom = aux;
        }
        Square topSquare = searchSquare(top);
        Square bottomSquare = searchSquare(bottom);
        if (topSquare.getId().equals("") && bottomSquare.getId().equals("")) {
            topSquare.setId(valor + "");
            bottomSquare.setIsBottom(true);
            bottomSquare.setId(valor + "");
            generateLadders(--ladder, ++valor);
        } else {
            generateLadders(ladder, valor);
        }
        return;
    }

    public boolean movePlayer(int dice, Player player) {
        Square current = playerSquare(player);
        return movePlayer(dice, player, current);
    }

    public boolean movePlayer(int dice, Player player, Square current) {
        if (current == null) {
            this.end = Instant.now();
            this.time = Duration.between(start, end);
            return false;
        }
        if (current == this.tail) {
            this.end = Instant.now();
            this.time = Duration.between(start, end);
            return false;
        }
        if (current.getPlayer1() == player) {
            current.setPlayer1(null);
        }
        if (current.getPlayer2() == player) {
            current.setPlayer2(null);
        }
        if (current.getPlayer3() == player) {
            current.setPlayer3(null);
        }
        if (dice == 0) {
            if (!current.getId().equals("")) {
                if (current.getIsHead()) {
                    current = searchSnakeSquare(current.getId());
                } else if (current.getIsBottom()) {
                    current = searchLadderSquare(current.getId());
                }
            }
            if (current == tail) {
                this.end = Instant.now();
                this.time = Duration.between(start, end);
                return false;
            }
            if (current == null) {
                this.end = Instant.now();
                this.time = Duration.between(start, end);
                return false;
            }
            if (current.getPlayer1() == null) {
                current.setPlayer1(player);
                return true;
            } else if (current.getPlayer2() == null) {
                current.setPlayer2(player);
                return true;
            } else if (current.getPlayer3() == null) {
                current.setPlayer3(player);
            }
        }
        return movePlayer(--dice, player, current.getNext());
    }

    private Square playerSquare(Player player) {
        return playerSquare(player, head);
    }

    private Square playerSquare(Player player, Square current) {
        if (current == tail) {
            return tail;
        }
        if (current.getPlayer1() == player) {
            return current;
        }
        if (current.getPlayer2() == player) {
            return current;
        }
        if (current.getPlayer3() == player) {
            return current;
        }
        return playerSquare(player, current.getNext());
    }

    public void massageDefault() {
        messageDefault(head);
    }

    private void messageDefault(Square current) {
        if (current == null) {
            return;
        }
        current.setMessage(current.getValue() + "");
        if (current.getPlayer1() != null) {
            current.setMessage(current.getMessage() + current.getPlayer1().getSymbol());
        }
        if (current.getPlayer2() != null) {
            current.setMessage(current.getMessage() + current.getPlayer2().getSymbol());
        }
        if (current.getPlayer3() != null) {
            current.setMessage(current.getMessage() + current.getPlayer3().getSymbol());
        }
        messageDefault(current.getNext());
    }

    public void messageSnakeAndStairs() {
        messageSnakeAndStairs(head);
    }

    private void messageSnakeAndStairs(Square current) {
        if (current == tail) {
            tail.setMessage(tail.getId());
            return;
        }
        current.setMessage(current.getId());
        messageSnakeAndStairs(current.getNext());
    }

    public void registerPlayers(Player player1, Player player2, Player player3) {
        if (this.head != null) {
            this.head.setPlayer1(player1);
            this.head.setPlayer2(player2);
            this.head.setPlayer3(player3);
        }
    }

    private Square searchSnakeSquare(String id) {
        return searchSnakeSquare(id, head);
    }

    private Square searchSnakeSquare(String id, Square current) {
        if (current == tail) {
            return null;
        }
        if (current.getId().equals(id) && !current.getIsHead()) {
            return current;
        }
        return searchSnakeSquare(id, current.getNext());
    }

    private Square searchLadderSquare(String id) {
        return searchLadderSquare(id, head);
    }

    private Square searchLadderSquare(String id, Square current) {
        if (current == tail) {
            return null;
        }
        if (current.getId().equals(id) && !current.getIsBottom()) {
            return current;
        }
        return searchLadderSquare(id, current.getNext());
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

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
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

    public Instant getStart() {
        return start;
    }

    public void setStart(Instant start) {
        this.start = start;
    }

    public Instant getEnd() {
        return end;
    }

    public void setEnd(Instant end) {
        this.end = end;
    }

    public Duration getTime() {
        return time;
    }

    public void setTime(Duration time) {
        this.time = time;
    }
}
