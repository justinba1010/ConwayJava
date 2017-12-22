import java.util.*;

public class ConwayGrid {
  private boolean[][] grid;
  private int gen;
  private char spotFalse = ' ';
  private char spotTrue = '*';
  private int neighbors = 3;
  public ConwayGrid(int size) {
    init(size);
  }//ConwayGrid Constructor
  public ConwayGrid() {
    init(10);
  }//ConwayGrid Default Constructor
  private void init(int size) {
    gen = 0;
    grid = new boolean[size][size];
    blanket();
  }//init

  public void modify(int x, int y, boolean a) {
    if(legal(x,y)) {
      grid[x][y] = a;
    }
  }//modify

  private void blanket() {//Felt it could be useful
    for(boolean[] row : grid) {
      for(boolean spot : row) {
        spot = false;
      }
    }
  }

  public void nextGen() {
    //Lists so we can go through and not overwrite our grid while we view it
    ArrayList<Integer[]> newalive = new ArrayList<Integer[]>();
    ArrayList<Integer[]> newdead = new ArrayList<Integer[]>();
    for(int x = 0; x < grid.length; x++) {
      for(int y = 0; y < grid[x].length; y++) {
        boolean alive = neighborsAlive(x,y,neighbors);//Check new state of grid spot
        if(alive != grid[x][y]) {//Change the state of spot
          Integer[] spot = {x,y};
          if(alive == true) {
            newalive.add(spot);
          } else {
            newdead.add(spot);
          }
        }
      }//y
    }//x
    //Change our grid now
    for(Integer[] alive : newalive) {
      if(alive.length == 2) {
        modify(alive[0],alive[1], true);
      }
    }
    for(Integer[] dead : newdead) {
      if(dead.length == 2) {
        modify(dead[0],dead[1], false);
      }
    }
    gen++;
  }//nextGen

  private boolean neighborsAlive(int x, int y, int z) {
    int counter = 0;
    for(int i = -1; i <= 1; i++) {
      for(int j = -1; j <= 1; j++) {
        if(legal(x+i,y+j)) {
          counter += (grid[x+i][y+j]) ? 1 : 0;
        }//if legal
      }//for j
    }//for i
    return counter == z;
  }

  private boolean legal(int x, int y) {
    return x >= 0 && y >= 0 && x < grid.length && y < grid[x].length;
  }

  public String toString() {
    String s = "";
    for(boolean[] row : grid) {
      for(boolean spot : row) {
        s += (spot) ? spotTrue : spotFalse;
      }
      s += "\n";
    }
    s+= "Generation #"+gen;
    return s;
  }

  public Integer[] getDimensions() {
    Integer[] s = {grid.length, grid[0].length};
    return s;
  }
}//ConwayGrid
