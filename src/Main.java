import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {

        System.out.println(42);

        Game g = new Game();
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));


        while (!g.isFinished) {
            try {
                g.print();
                boolean moveAllowed = false;
                do {
                    System.out.print("Spalte:");
                    int x = Integer.parseInt(reader.readLine());
                    System.out.print("Zeile:");
                    int y = Integer.parseInt(reader.readLine());
                    moveAllowed = g.setMove(y, x, g.getActualPlayer());
                } while (!moveAllowed);

                if (g.checkForFinish() != 0) {
                    g.print();
                    System.out.println("Player " + g.checkForFinish() + " won the game!");
                }
            } catch (IOException e) {
            }
        }
    }
}