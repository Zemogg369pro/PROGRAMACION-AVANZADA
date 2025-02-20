package aloproo;

import java.util.concurrent.TimeUnit;

public class punto {
    public static void main(String[] args) {
        int x1 = 0;
        int y1 = 0;
        int x2 = 1;
        int y2 = 1;
        int steps = 30;
        boolean direction1 = true;
        boolean direction2 = false;

        for (int i = 0; i < steps; i++) {
         
            for (int k = 0; k < 50; k++) {
                System.out.println();
            }

            for (int j = 0; j < y1; j++) {
                System.out.println();
            }
            for (int j = 0; j < x1; j++) {
                System.out.print(" ");
            }
            System.out.print(".\n");

            for (int j = 0; j < y2; j++) {
                System.out.println();
            }
            for (int j = 0; j < x2; j++) {
                System.out.print(" ");
            }
            System.out.print(".\n");

            if (direction1) {
                x1 += 1;
                y1 -= 1;
            } else {
                x1 += 1;
                y1 -= 1;
            }

            if (direction2) {
                x2 += 1;
                y2 += 1;
            } else {
                x2 += 1;
                y2 -= 1;
            }

            if (y1 == 0 || y1 == 10) {
                direction1 = !direction1;
            }
            if (y2 == 0 || y2 == 10) {
                direction2 = !direction2;
            }

            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}