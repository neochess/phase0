package ru.neochess.core;

/**
 * Created by diviz on 22.12.2016.
 */
public class Cell {

    public static final int length = 9;
    private int x, y;
    private String[] convertX= {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"};
    private String[] convertY= {"10", "9", "8", "7", "6", "5", "4", "3", "2", "1" };

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

//hjhjhj

    @Override
    public String toString()
    {
        if (x > length || y > length || x < 0 || y < 0){
            return "";
        }
        return convertX[x] + convertY[y];
    }

    public Integer getX()
    {
        if (x > length || y > length || x < 0 || y < 0){
            return null;
        }
        return x;
    }

    public Integer getY()
    {
        if (x > length || y > length || x < 0 || y < 0){
            return null;
        }
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cell cell = (Cell) o;

        if (x != cell.x) return false;
        return y == cell.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}

