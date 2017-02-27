import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Game {

  private Board board;
  private Player[] player;
  private BufferedReader reader;
  private static final int LIMIT = 5;

  private void initComponent() {
    board = new Board(9);
    player = new Player[]{
        new Player("player 1", new Symbol(" X ")),
        new Player("player 2", new Symbol(" O "))
    };
    reader = new BufferedReader(new InputStreamReader(System.in));
  }

  void start() throws IOException {
    initComponent();
    int[] coordinate = new int[2];
    for (int i = 0; true; i++) {
      Player p = player[i % 2];
      boolean donePlacing = false;
      while (!donePlacing) {
        System.out.printf("Player %s round!\n", p);
        coordinate = getCoordinate();
        donePlacing = board.placeSymbol(p.getSymbol(), coordinate[0], coordinate[1]);
      }
      board.displayBoard();
      if (isWin(p.getSymbol(), coordinate[0], coordinate[1])) {
        System.out.printf("Congratulation %s win !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!", p);
        break;
      }
    }
  }

  private int[] getCoordinate() throws IOException {
    int[] coordinate = new int[2];
    System.out.print("Select row: ");
    coordinate[0] = Integer.parseInt(reader.readLine()) - 1;
    System.out.print("Select column: ");
    coordinate[1] = Integer.parseInt(reader.readLine()) - 1;
    return coordinate;
  }

  private boolean isWin(Symbol s, int row, int col) {
    return checkSimilar(LineType.HORIZONTAL, s, row, col) ||
            checkSimilar(LineType.LEFT_DIAGONAL, s, row, col) ||
            checkSimilar(LineType.RIGHT_DIAGONAL, s, row, col) ||
            checkSimilar(LineType.VERTICAL, s, row, col);

  }

  private boolean checkSimilar(LineType type, Symbol s, int row, int col) {
    int size = board.SIZE,
        count = 0,
        originPoint = (row * size) + col;
    String[] cell = board.getCell();
    boolean condition;

    for (int i = 0; i < size; i++) {
      if (type == LineType.RIGHT_DIAGONAL) {
        try {
          int startPoint = originPoint - (row * (size - 1));
          condition = cell[startPoint + ((size - 1) * i)].equals(s.getValue());
        } catch (ArrayIndexOutOfBoundsException e) {
          break;
        }
      } else if (type == LineType.LEFT_DIAGONAL) {
        try {
          int startPoint = (originPoint % (size + 1));
          condition = cell[startPoint + ((size + 1) * i)].equals(s.getValue());
        } catch (ArrayIndexOutOfBoundsException e) {
          break;
        }
      } else if (type == LineType.HORIZONTAL) {
        condition = cell[row * size + i].equals(s.getValue());
      } else if (type == LineType.VERTICAL) {
        condition = cell[i * size + col].equals(s.getValue());
      } else {
        return false;
      }
      count = (condition) ? count + 1 : 0;
      System.out.println("c: " + count);
      if (count == LIMIT) {
        return true;
      }
    }
    return false;
  }

}
