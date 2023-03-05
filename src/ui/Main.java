package ui;

import  model.Controller;
import java.util.*;
public class Main {

    private Controller controller;
    public static Scanner sc = new Scanner(System.in);
    public Main(){
        controller = new Controller();
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.menu();
    }

    public void menu(){
        System.out.println("Select the option for what you wanna do:\n 1.Play\n 2.Exit ");
        int option = sc.nextInt();
        menu(option);
    }

    private void menu (int option){
        switch(option){
            case 1:
                play();
                break;
            case 2:
                //
                break;
        }
    }

    public void play(){
        System.out.print("Type the number of columns of the board: ");
        int columns = sc.nextInt();
        System.out.print("Type the number of fills of the board: ");
        int fills = sc.nextInt();
        System.out.print("Type the number of snakes of the board: ");
        int snakes = sc.nextInt();
        System.out.print("Type the number of ladders of the board: ");
        int ladders = sc.nextInt();
        System.out.println(controller.generateBoard(columns,fills,snakes,ladders));
    }

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }
}
