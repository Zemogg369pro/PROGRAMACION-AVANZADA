package aloproo;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class punto2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of rows: ");
        int rows = scanner.nextInt();

        System.out.print("Enter the number of columns: ");
        int cols = scanner.nextInt();

        int x1 = 0;
        int y1 = 0;
        int x2 = 1;
        int y2 = 1;
        int steps = 50;
        char[][] grid = new char[rows][cols];
        String[] colors = {"\u001B[31m", "\u001B[32m", "\u001B[33m", "\u001B[34m", "\u001B[35m", "\u001B[36m"};
        String resetColor = "\u001B[0m";

        for (int i = 0; i < steps; i++) {
            // Clear the grid
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < cols; c++) {
                    grid[r][c] = ' ';
                }
            }

            // Place the dots in the grid
            if (x1 < cols && y1 < rows) {
                grid[y1][x1] = '.';
            }
            if (x2 < cols && y2 < rows) {
                grid[y2][x2] = '.';
            }

            // Print the grid with colors
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < cols; c++) {
                    if (grid[r][c] == '.') {
                        System.out.print(colors[i % colors.length] + grid[r][c] + resetColor);
                    } else {
                        System.out.print(grid[r][c]);
                    }
                }
                System.out.println();
            }

            // Move the dots diagonally
            x1 += 1;
            y1 += 1;
            x2 += 1;
            y2 += 1;

            try {
                TimeUnit.MILLISECONDS.sleep(500); // 500 milliseconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        scanner.close();
    }
}