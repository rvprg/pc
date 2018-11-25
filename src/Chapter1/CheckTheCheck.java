import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CheckTheCheck {
    private static final BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));
    private static final int BOARD_SIZE = 8;

    private static final int[][] king = new int[][] {
            { -1, -1 }, { 0, -1 }, { 1, -1 }, { -1, 0 }, { 1, 0 }, { -1, 1 },
            { 0, 1 }, { 1, 1 }
    };
    private static final int[][] knight = new int[][] {
            { -2, -1 }, { -1, -2 }, { 1, -2 }, { 2, -1 }, { 2, 1 }, { 1, 2 },
            { -1, 2 }, { -2, 1 }
    };
    private static final int[][] bishop = new int[][] {
            { 1, 1 }, { -1, -1 }, { -1, 1 }, { 1, -1 }
    };
    private static final int[][] rook = new int[][] {
            { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 }
    };
    private static final int[][] queen = new int[][] {
            { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 }, { 1, 1 }, { -1, -1 },
            { -1, 1 }, { 1, -1 }
    };
    private static final int[][] white_pawn = new int[][] {
            { -1, -1 }, { -1, 1 }
    };
    private static final int[][] black_pawn = new int[][] {
            { 1, -1 }, { 1, 1 }
    };

    private static boolean isWithinBounds(int d, int v) {
        if (d == 0) {
            return true;
        }
        return d > 0 ? (v < BOARD_SIZE) : (v >= 0);
    }

    private static void check(int di, int dj, int i, int j, int[][] board,
            int[][] attackBoard) {
        int c = j + dj;
        int r = i + di;
        while (isWithinBounds(dj, c) && isWithinBounds(di, r)) {
            attackBoard[r][c] = 1;
            if (board[r][c] != '.')
                break;
            r += di;
            c += dj;
        }
    }

    private static void check(int[][] d, int i, int j, int[][] board,
            int[][] attackBoard) {
        if (d == king || d == knight || d == black_pawn || d == white_pawn) {
            for (int k = 0; k < d.length; ++k) {
                if ((i + d[k][0] >= 0 && i + d[k][0] < BOARD_SIZE) &&
                        (j + d[k][1] >= 0 && j + d[k][1] < BOARD_SIZE)) {
                    attackBoard[i + d[k][0]][j + d[k][1]] = 1;
                }
            }
            return;
        }

        for (int k = 0; k < d.length; ++k) {
            check(d[k][0], d[k][1], i, j, board, attackBoard);
        }
    }

    private static int[] locate(int v, int[][] board) {
        for (int i = 0; i < BOARD_SIZE; ++i) {
            for (int j = 0; j < BOARD_SIZE; ++j) {
                if (board[i][j] == v) {
                    return new int[] { i, j };
                }
            }
        }
        return null;
    }

    private static String checkTheCheck(int[][] board) {
        int[][] attackBoardWhites = new int[BOARD_SIZE][BOARD_SIZE];
        int[][] attackBoardBlacks = new int[BOARD_SIZE][BOARD_SIZE];

        for (int i = 0; i < BOARD_SIZE; ++i) {
            for (int j = 0; j < BOARD_SIZE; ++j) {
                if (board[i][j] == 'R' || board[i][j] == 'r') {
                    check(rook, i, j, board, board[i][j] == 'R'
                            ? attackBoardWhites : attackBoardBlacks);
                }
                if (board[i][j] == 'B' || board[i][j] == 'b') {
                    check(bishop, i, j, board, board[i][j] == 'B'
                            ? attackBoardWhites : attackBoardBlacks);
                }
                if (board[i][j] == 'K' || board[i][j] == 'k') {
                    check(king, i, j, board, board[i][j] == 'K'
                            ? attackBoardWhites : attackBoardBlacks);
                }
                if (board[i][j] == 'N' || board[i][j] == 'n') {
                    check(knight, i, j, board, board[i][j] == 'N'
                            ? attackBoardWhites : attackBoardBlacks);
                }
                if (board[i][j] == 'Q' || board[i][j] == 'q') {
                    check(queen, i, j, board, board[i][j] == 'Q'
                            ? attackBoardWhites : attackBoardBlacks);
                }
                if (board[i][j] == 'P' || board[i][j] == 'p') {
                    boolean isWhite = board[i][j] == 'P';
                    check(isWhite ? white_pawn : black_pawn, i, j, board,
                            isWhite ? attackBoardWhites : attackBoardBlacks);
                }
            }
        }

        int[] wk = locate('K', board);
        int[] bk = locate('k', board);

        boolean bkCheck = (attackBoardWhites[bk[0]][bk[1]] == 1);
        boolean wkCheck = (attackBoardBlacks[wk[0]][wk[1]] == 1);

        if (wkCheck) {
            return "white king is in check.";
        }
        if (bkCheck) {
            return "black king is in check.";
        }

        return "no king is in check.";
    }

    public static void main(String[] args) throws IOException {
        boolean empty = true;
        int game = 1;
        do {
            int[][] board = new int[BOARD_SIZE][BOARD_SIZE];
            empty = true;
            for (int i = 0; i < BOARD_SIZE; ++i) {
                String currentLine = reader.readLine();
                for (int j = 0; j < BOARD_SIZE; ++j) {
                    board[i][j] = currentLine.charAt(j);
                    empty = empty && board[i][j] == '.';
                }
            }
            if (empty) {
                break;
            }
            System.out.println("Game #" + game + ": " + checkTheCheck(board));
            game++;
        } while (reader.readLine().trim().equals(""));
    }
}

