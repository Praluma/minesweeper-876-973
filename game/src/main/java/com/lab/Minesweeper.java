package com.lab;

import java.util.Scanner;
import java.io.InputStream;

public class Minesweeper {
    static final char SAFE_CELL = '.';
    static final char MINE_CELL = 'X' ;
    static final int IS_SAFE = 0;
    static final int IS_MINE = 1;
    int fieldX, fieldY;
    int[][] cells;
    String fieldFileName;
    boolean[][] revealed;

    public Minesweeper(String fieldFile) {
        this.fieldFileName = fieldFile;
        initFromFile(fieldFileName);
    }

    public Minesweeper(int fieldX, int fieldY) {
        this.fieldX = fieldX;
        this.fieldY = fieldY;
        this.cells = new int[fieldX][fieldY];
        this.revealed = new boolean[fieldX][fieldY]; 

        for (int i = 0; i < fieldX; i++) {
            for (int j = 0; j < fieldY; j++) {
                cells[i][j] = IS_SAFE;
                revealed[i][j] = false;
            }
        }
    }
    public void displayField() {
        System.out.println("Current Board:");
        for (int i = 0; i < fieldX; i++) {
            for (int j = 0; j < fieldY; j++) {
                if (!revealed[i][j]) {
                    System.out.print("? "); 
                } else if (cells[i][j] == IS_MINE) {
                    System.out.print(MINE_CELL + " "); 
                } else {
                    System.out.print(SAFE_CELL + " "); 
                }
            }
            System.out.println();
        }
    }

    public int countAdjacentMines(int x, int y) {
        int count = 0;
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                int nx = x + dx, ny = y + dy;
                if (nx >= 0 && nx < fieldX && ny >= 0 && ny < fieldY && cells[nx][ny] == IS_MINE) {
                    count++;
                }
            }
        }
        return count;
    }
    void setMineCell(int x, int y) {
        cells[x][y] = IS_MINE;
    }

    void initFromFile(String mineFieldFile) {
        InputStream is = getClass().getClassLoader().getResourceAsStream(mineFieldFile);
        
        Scanner scanner = new Scanner(is); 
            fieldX = scanner.nextInt();
            fieldY = scanner.nextInt();
            cells = new int[fieldX][fieldY];
            scanner.nextLine();
            for (int i = 0; i < fieldX; i++) {
                String line = scanner.nextLine();
                for (int j = 0; j < fieldY; j++) {
                    cells[i][j] = (line.charAt(j) == MINE_CELL) ? IS_MINE : IS_SAFE;
                }
            }
    }
    public boolean revealCell(int x, int y) {
        if (x < 0 || x >= fieldX || y < 0 || y >= fieldY || revealed[x][y]) {
            System.out.println("Invalid move! Try again.");
            return false;
        }
    
        revealed[x][y] = true; 
        if (cells[x][y] == IS_MINE) {
            System.out.println("BOOM! You hit a mine. Game Over!");
            displayField(); 
            return true; 
        }
    
        return false; 
    }
    
    
     public boolean isWin() {
        for (int i = 0; i < fieldX; i++) {
            for (int j = 0; j < fieldY; j++) {
                if (cells[i][j] == IS_SAFE && !revealed[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
