public class Player {

  private String name;
  private Symbol symbol;

  public Player(String name, Symbol symbol) {
    this.name = name;
    this.symbol = symbol;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Symbol getSymbol() {
    return symbol;
  }

  public void placeSymbol(Board b, int row, int col) {
    b.placeSymbol(symbol, row, col);
  }

  @Override
  public String toString() {
    return this.name;
  }
}
