package Soal3;

import java.util.Scanner;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LotreBoard board = new LotreBoard();
        board.generateBoard();

        System.out.println("=== SELAMAT DATANG DI LOTRE GOSOK BANG PAWWRY ===");

        while (!board.isGameOver()) {
            board.displayBoard();

            System.out.print("Masukkan baris (0-3): ");
            int row = sc.nextInt();
            System.out.print("Masukkan kolom (0-4): ");
            int col = sc.nextInt();

            if (row < 0 || row > 3 || col < 0 || col > 4) {
                System.out.println("Input baris/kolom tidak valid!");
                continue;
            }

            if (board.revealed[row][col]) {
                System.out.println("Kotak ini sudah dibuka.");
                continue;
            }

            boolean result = board.guess(row, col);

            if (!result) {
                System.out.println("BOOM! Kamu kena bom! Permainan berakhir.");
                board.displayBoard(true);
                return;
            }
        }

        System.out.println("Selamat! Kamu berhasil membuka semua kotak aman!");
        board.displayBoard(true);
    }
}

class LotreBoard {
    char[][] board;
    boolean[][] revealed;
    int[][] data;

    public LotreBoard() {
        board = new char[4][5];
        revealed = new boolean[4][5];
        data = new int[4][5];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                board[i][j] = '*';
                revealed[i][j] = false;
                data[i][j] = 0;
            }
        }
    }

    public void generateBoard() {
        Random rand = new Random();
        int bombsPlaced = 0;
        while (bombsPlaced < 2) {
            int row = rand.nextInt(4);
            int col = rand.nextInt(5);
            if (data[row][col] == 0) {
                data[row][col] = 1; // 1 = bom
                bombsPlaced++;
            }
        }
    }

    public void displayBoard() {
        displayBoard(false);
    }

    public void displayBoard(boolean revealAll) {
        System.out.println("\nPapan Lotre:");
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                if (revealAll) {
                    if (data[i][j] == 1) {
                        System.out.print("X ");
                    } else {
                        System.out.print("O ");
                    }
                } else {
                    if (revealed[i][j]) {
                        if (data[i][j] == 0) {
                            System.out.print("O ");
                        } else {
                            System.out.print("X ");
                        }
                    } else {
                        System.out.print("* ");
                    }
                }
            }
            System.out.println();
        }
    }

    public boolean guess(int row, int col) {
        revealed[row][col] = true;
        if (data[row][col] == 1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean isGameOver() {
        int openedSafe = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                if (revealed[i][j] && data[i][j] == 0) {
                    openedSafe++;
                }
                if (revealed[i][j] && data[i][j] == 1) {
                    return true; // terkena bom
                }
            }
        }
        return openedSafe == 18;
    }
}

