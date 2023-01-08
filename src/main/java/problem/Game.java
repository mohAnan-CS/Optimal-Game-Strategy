package problem;

import java.util.Arrays;
import java.util.List;

public class Game {

    private int exceptedResult;
    private int[][] dbTable;
    private List<Integer> coins ;

    public Game(int exceptedResult, int[][] dbTable, List<Integer> coins) {
        this.exceptedResult = exceptedResult;
        this.dbTable = dbTable;
        this.coins = coins;
    }

    public int getExceptedResult() {
        return exceptedResult;
    }

    public void setExceptedResult(int exceptedResult) {
        this.exceptedResult = exceptedResult;
    }

    public int[][] getDbTable() {
        return dbTable;
    }

    public void setDbTable(int[][] dbTable) {
        this.dbTable = dbTable;
    }

    public List<Integer> getCoins() {
        return coins;
    }

    public void setCoins(List<Integer> coins) {
        this.coins = coins;
    }

    @Override
    public String toString() {
        return "Game{" +
                "exceptedResult=" + exceptedResult +
                ", dbTable=" + Arrays.toString(dbTable) +
                ", coins=" + coins +
                '}';
    }
}
