import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Game {

    public static final int PLAYERONE = 1;
    public static final int PLAYERTWO = 2;

    public static final int SIZE = 3;

    // the field is populated as follow:
    // int[y][x]
    // [0,0,0],
    // [1,0,0],
    // [0,0,0]
    // the playerTwo is located at [1][0]

    int[][] gamefield = new int[SIZE][SIZE];

    private int loopCounter = 0;

    boolean isFinished = false;

    public Game() {
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                this.gamefield[i][j] = 0;
    }

    public int getActualPlayer() {
        return (this.loopCounter % 2) + 1;
    }

    public boolean setMove(int y, int x, int player) {
        boolean ret = false;
        if (y >= SIZE || x >= SIZE) {
            System.out.println("Eingegebene Koordinaten ungültig");
            return false;
        }

        if (this.gamefield[y][x] == 0) {
            // this field is free
            this.gamefield[y][x] = player;
            this.loopCounter++;
            ret = true;
        }
        return ret;
    }

    public int checkForFinish() {
        for (int i = 0; i < SIZE; i++) {
            // first check for row
            List<Integer> row = new ArrayList<Integer>();
            for (int j = 0; j < SIZE; j++) {
                row.add(this.gamefield[i][j]);
            }
            // player one has won because of a row
            if (allTheSame(row, PLAYERONE)) {
                this.isFinished = true;
                return PLAYERONE;
            }
            // player two has won because of a row
            if (allTheSame(row, PLAYERTWO)) {
                this.isFinished = true;
                return PLAYERTWO;
            }
            // check for column
            List<Integer> column = new ArrayList<Integer>();
            for (int j = 0; j < SIZE; j++) {
                column.add(this.gamefield[j][i]);
            }
            // player one has won because of a row
            if (allTheSame(column, PLAYERONE)) {
                this.isFinished = true;
                return PLAYERONE;
            }
            // player two has won because of a row
            if (allTheSame(column, PLAYERTWO)) {
                this.isFinished = true;
                return PLAYERTWO;
            }
        }
        // check for diagonal
        List<Integer> leftTopToBottom = new ArrayList<Integer>();
        List<Integer> leftBottomToTop = new ArrayList<Integer>();
        for (int i = 0; i < SIZE; i++) {
            leftTopToBottom.add(this.gamefield[i][i]);
            leftBottomToTop.add(this.gamefield[SIZE - 1 - i][i]);
        }
        if (allTheSame(leftTopToBottom, PLAYERONE)) {
            this.isFinished = true;
            return PLAYERONE;
        }
        if (allTheSame(leftTopToBottom, PLAYERTWO)) {
            this.isFinished = true;
            return PLAYERTWO;
        }

        if (allTheSame(leftBottomToTop, PLAYERONE)) {
            this.isFinished = true;
            return PLAYERONE;
        }
        if (allTheSame(leftBottomToTop, PLAYERTWO)) {
            this.isFinished = true;
            return PLAYERTWO;
        }

        return 0;
    }

    private boolean allTheSame(List<Integer> listToCheck, int valueToCheck) {
        return listToCheck.stream().filter(value -> value == valueToCheck).count() == listToCheck.size();
    }

    public void print() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE && i != 0; j++)
                System.out.print("--------");
            System.out.println();

            for (int j = 0; j < SIZE; j++) {
                if (j == 0)
                    System.out.print("\t");
                System.out.print(gamefield[i][j] == 1 ? "O" : (gamefield[i][j] == 2 ? "X" : " "));
                if (j != SIZE - 1)
                    System.out.print("\t|\t");
                else
                    System.out.println();
            }

        }

    }

}
