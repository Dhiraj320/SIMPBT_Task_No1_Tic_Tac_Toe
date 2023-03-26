import java.util.Random;
import java.util.Scanner;

class TicTacToe {
    static char[][] board;

    public TicTacToe() {
        board = new char[4][4];
        initBoard();
    }

    void initBoard() {
        for (int i = 1; i < board.length; i++) {
            for (int j = 1; j < board[i].length; j++) {
                board[i][j] = ' ';
            }
        }
    }

    static void displayBoard() {
        System.out.println("-------------");
        for (int i = 1; i < board.length; i++) {
            System.out.print("| ");
            for (int j = 1; j < board[i].length; j++) {
                System.out.print(board[i][j] + " | ");

            }
            System.out.println();
            System.out.println("-------------");
        }

    }

    static void placeValue(int row, int column, char value) {
        if (row >= 1 && row <= 3 && column >= 1 && column <= 3) {
            board[row][column] = value;
        } else {
            System.out.println("Invalid Position");
        }
    }

    static boolean checkColumnWin() {
        for (int j = 1; j <= 3; j++) {
            if (board[1][j] != ' ' && board[1][j] == board[2][j] && board[2][j] == board[3][j]) {
                return true;
            }
        }
        return false;
    }

    static boolean checkRowWin() {
        for (int i = 1; i <= 3; i++) {
            if (board[i][1] != ' ' && board[i][1] == board[i][2] && board[i][2] == board[i][3]) {
                return true;
            }
        }
        return false;
    }

    static boolean checkDiagonalWin() {
        if (board[1][1] != ' ' && board[1][1] == board[2][2] &&
                board[2][2] == board[3][3] || board[1][3] != ' ' &&
                board[1][3] == board[2][2] &&
                board[2][2] == board[3][1]
        ) {
            return true;
        } else {
            return false;
        }
    }

    static boolean checkTie() {
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

}

abstract class Player {
    String name;
    char place;

    abstract void makeMove();

    boolean isValidMove(int row, int column) {
        if (row >= 1 && row <= 3 && column >= 1 && column <= 3) {
            if (TicTacToe.board[row][column] == ' ') {
                return true;
            }
        }
        return false;

    }
}

class HumanPlayer extends Player {


    HumanPlayer(String name, char place) {
        this.name = name;
        this.place = place;
    }

    void makeMove() {
        Scanner sc = new Scanner(System.in);
        int row;
        int column;
        do {
            System.out.println("Enter the row and column like 1 1 this not 11");
            row = sc.nextInt();
            column = sc.nextInt();
        } while (!isValidMove(row, column));
        TicTacToe.placeValue(row, column, place);
    }


}




public class Main {
    public static void main(String[] args) {
// cp =current player
        TicTacToe t = new TicTacToe();
        HumanPlayer p1 = new HumanPlayer("Player X", 'X');
        HumanPlayer p2=new HumanPlayer("Player O",'O');


        Player cp;
        cp = p1;
        while (true) {
            System.out.println(cp.name + " chance ");
            cp.makeMove();
            TicTacToe.displayBoard();

            if (TicTacToe.checkColumnWin() || TicTacToe.checkRowWin() || TicTacToe.checkDiagonalWin()) {
                System.out.println(cp.name + " has Won");
                break;
            } else if (TicTacToe.checkTie()) {
                System.out.println("Game Tie");
                break;
            } else {
                if (cp == p1) {
                    cp = p2;

                }else{
                    cp=p1;
                }
            }

        }

    }
}