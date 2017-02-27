import java.util.Arrays;

public class Board {

  static int SIZE = 9;
  private final String EMPTY_CELL = " . ";
  private String[] cell;

  Board(int size) {
    SIZE = size;
    this.cell = new String[(int) Math.pow(9,2)];
    Arrays.fill(cell, EMPTY_CELL);
  }

  boolean placeSymbol(Symbol s, int row, int col) {
      try {
        if (!cell[(SIZE * row) + col].equals(EMPTY_CELL)) {
          System.out.printf("SORRY! (%d, %d) is already exist!, Please try again.\n", row + 1, col + 1);
        } else {
          cell[SIZE * row + col] = s.getValue();
          return true;
        }
      } catch (ArrayIndexOutOfBoundsException e) {
        System.out.printf("Please input a number between 1 -> %d, Please try again.\n", SIZE);
      }
      return  false;
  }

  String[] getCell() {
    return cell;
  }

  void displayBoard() {
    System.out.println(toString());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("0  ");
    for (int i = 0; i < SIZE ; i++) {
      sb.append(i+1).append("  ");
    }
    sb.append("\n");
    for (int i = 0; i < SIZE ; i++) {
      sb.append((i+1) + " ");
      for (int j = 0; j < SIZE ; j++) {
        sb.append(cell[(i * SIZE) + j]);
      }
      sb.append("\n");
    }
    return sb.toString();
  }
}
