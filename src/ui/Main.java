package ui;

import  model.Controller;
import java.util.Scanner;
import java.util.Random;

public class Main {

    private Controller controller;
    public static Scanner sc = new Scanner(System.in);
    public Main(){
        controller = new Controller();
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.registerPlayers();
        main.menu();


    }

    public void menu(){
        System.out.println("Select the option for what you wanna do:\n 1.Play\n 2.Exit ");
        int option = sc.nextInt();
        sc.nextLine();
        menu(option);
    }

    public void registerPlayers(){
        String symbol1= obtainSymbol("");
        System.out.println("The symbol of the player is: "+symbol1);
        String symbol2= String.valueOf((obtainSymbol(symbol1)).charAt(1));
        System.out.println("The symbol of the player is: "+symbol2);
        String symbol3= String.valueOf(obtainSymbol(symbol1+symbol2).charAt(2));
        System.out.println("The symbol of the player is: "+symbol3);
        controller.registerPlayers(symbol1,symbol2,symbol3);
    }

    public String obtainSymbol(String symbols){
        System.out.print("Type the symbol of the player.Choose one of these: * ! O X % $ # + & ");
        String symbol = sc.nextLine();
        if(symbols.contains(symbol)||!symbol.matches("[*!O%X$#&+]")){
            System.out.println("unavailable symbol, try it again");
            obtainSymbol(symbols);
        }else{
            symbols+=symbol;
        }
        return symbols;
    }

    private void menu (int option){
        switch(option){
            case 1:
                generateBoard();
                break;
            case 2:
                System.out.println("Thanks for playing, the score of the players is: ");
                System.out.println(controller.printPlayerScore());
                System.out.println("Bye");
                break;
            default:
                System.out.println("Invalid option");
                break;
        }
        if(option!=2){
            menu();
        }

    }

    public void generateBoard(){
        System.out.println("The length of the board must be between 3 and 1936");
        System.out.print("Type the number of columns of the board: ");
        int columns = sc.nextInt();
        System.out.print("Type the number of rows of the board: ");
        int fills = sc.nextInt();
        if(columns*fills<3||columns*fills>1936){
            System.out.println("Invalid board size.Try it again");
            generateBoard();
        }
        System.out.println("The number of snakes and ladders must be less than 40% of the board");
        System.out.print("Type the number of snakes of the board: ");
        int snakes = sc.nextInt();
        System.out.print("Type the number of ladders of the board: ");
        int ladders = sc.nextInt();
        sc.nextLine();
        if(snakes+ladders>(2*columns*fills)/5){
            System.out.println("Invalid number of snakes and ladders.Try it again");
            generateBoard();
        }
        controller.generateBoard(columns,fills,snakes,ladders);
        System.out.println(controller.printBoard());
        play();
    }

    public void play(){

        System.out.println("player "+controller.getCurrentPlayer().getSymbol()+"'s turn");
        System.out.println("Choose an option :\n1.Roll the dice\n2.Show snakes and ladders.");
        int option = sc.nextInt();
        sc.nextLine();
        boolean playing;
        switch(option){
            case 1:

                int result = rollDice();
                System.out.println("You rolled a "+result);
                playing=controller.movePlayer(result);
                if (playing){
                    System.out.println(controller.printBoard());
                }
                else{
                    return;
                }
                break;
            case 2:
                System.out.println(controller.printSnakesAndLadders());
                break;
            default:
                System.out.println("Invalid option");
                break;
        }
        play();
    }

    public int rollDice(){
        Random dice = new Random();
        return dice.nextInt(6)+1;
    }
}
