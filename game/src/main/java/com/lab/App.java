package com.lab;

import java.util.Scanner;

public class App {
    static Minesweeper initMineField() {
        Minesweeper game = new Minesweeper(9, 9);
        game.setMineCell(0, 1);
        game.setMineCell(1, 5);
        game.setMineCell(1, 8);
        game.setMineCell(2, 4);
        game.setMineCell(3, 6);        
        game.setMineCell(4, 2);
        game.setMineCell(5, 4);
        game.setMineCell(6, 2);
        game.setMineCell(7, 2);
        game.setMineCell(8, 6);
        return game;
    }
    static Minesweeper initMineFieldFromFile(String minefieldFile) {
        return new Minesweeper(minefieldFile);
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select Minefield Template:");
        System.out.println("1. Default Minefield");
        System.out.println("2. Load Minefield from File");
        System.out.print("What do you want: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        Minesweeper game = null;
        if (choice == 2) {
            System.out.print("Enter filename: ");
            String filename = scanner.nextLine();
            game = initMineFieldFromFile(filename);
        } else {
            game = initMineField();
        }

        boolean gameOver = false;
        while (!gameOver) {
            game.displayField();
            System.out.print("Enter row (1-9): ");
            int x = scanner.nextInt()-1;
            System.out.print("Enter column (1-9): ");
            int y = scanner.nextInt()-1;
            
            gameOver = game.revealCell(x, y);
            
            if (game.isWin()) {
                game.displayField();
                System.out.println("Congratulations! You cleared the minefield!");
                break;
            }
        }

        scanner.close();
    }
}
