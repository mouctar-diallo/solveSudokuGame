public class SudokuSolver {
  
  private int[][] grid;
  
  public SudokuSolver(int[][] grid) {
    this.grid = grid;
  }
  
  public boolean solve() {
    for (int row = 0; row < 9; row++) {
      for (int col = 0; col < 9; col++) {
        if (grid[row][col] == 0) {
          for (int num = 1; num <= 9; num++) {
            if (isSafe(row, col, num)) {
              grid[row][col] = num;
              if (solve()) {
                return true;
              } else {
                grid[row][col] = 0;
              }
            }
          }
          return false;
        }
      }
    }
    return true;
  }
  
  private boolean isSafe(int row, int col, int num) {
    return !usedInRow(row, num) && !usedInCol(col, num) &&
      !usedInBox(row - row % 3, col - col % 3, num);
  }
  
  private boolean usedInRow(int row, int num) {
    for (int col = 0; col < 9; col++) {
      if (grid[row][col] == num) {
        return true;
      }
    }
    return false;
  }
  
  private boolean usedInCol(int col, int num) {
    for (int row = 0; row < 9; row++) {
      if (grid[row][col] == num) {
        return true;
      }
    }
    return false;
  }
  
  private boolean usedInBox(int boxStartRow, int boxStartCol, int num) {
    for (int row = 0; row < 3; row++) {
      for (int col = 0; col < 3; col++) {
        if (grid[row + boxStartRow][col + boxStartCol] == num) {
          return true;
        }
      }
    }
    return false;
  }
  
  public void printGrid() {
    for (int row = 0; row < 9; row++) {
      for (int col = 0; col < 9; col++) {
        System.out.print(grid[row][col] + " ");
      }
      System.out.println();
    }
  }
  
  public static void main(String[] args) {
    int[][] SudokuToSolve = {
                {5, 3, 0, 0, 7, 0, 0, 0, 0},
                {6, 0, 0, 1, 9, 5, 0, 0, 0},
                {0, 9, 8, 0, 0, 0, 0, 6, 0},
                {8, 0, 0, 0, 6, 0, 0, 0, 3},
                {4, 0, 0, 8, 0, 3, 0, 0, 1},
                {7, 0, 0, 0, 2, 0, 0, 0, 6},
                {0, 6, 0, 0, 0, 0, 2, 8, 0},
                {0, 0, 0, 4, 1, 9, 0, 0, 5},
                {0, 0, 0, 0, 8, 0, 0, 7, 9}
            };

            SudokuSolver solver = new SudokuSolver(SudokuToSolve);
            solver.solve();

            solver.printGrid();
  }
}