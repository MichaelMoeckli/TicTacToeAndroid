package application;

public class TicTacToeModel {
	int[] filledSquares = new int[9];
    int[] filledCircles = new int[5];
    int[] filledX = new int[5];

    int filledSquaresCounter = 0;
    int filledCirclesCounter = 0;
    int filledXCounter = 0;

    char winningTeam;

    final int[][] winningPositions = {
        {1, 5, 9},
        {3, 5, 7},
        {1, 2, 3},
        {4, 5, 6},
        {7, 8, 9},
        {1, 4, 7},
        {2, 5, 8},
        {3, 6, 9}
    };

    boolean allowMoves = true;

    boolean tie = false;
}
