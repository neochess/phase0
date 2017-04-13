package ru.neochess.core;

import java.util.Iterator;

/**
 * Created by diviz on 10.12.2016.
 */
public class IteratorCell implements Iterator<CellBoard> {

    private CellBoard currentCellBoard;
    private AdjacentCell adjacentCell;

    public IteratorCell(CellBoard currentCellBoard, AdjacentCell adjacentCell) {
        this.currentCellBoard = currentCellBoard;
        this.adjacentCell = adjacentCell;

        this.currentCellBoard = onlyNext();

    }

    /**
     * Getter for property 'AdjacentCell'.
     *
     * @return Value for property 'AdjacentCell'.
     */
    public AdjacentCell getAdjacentCell() {
        return adjacentCell;
    }

    public boolean hasNext() {
        return currentCellBoard != null;
    }

     private CellBoard onlyNext() {
        if(currentCellBoard != null) {
            switch (adjacentCell) {
                case Left:
                    return currentCellBoard.getLeft();
                case Right:
                    return currentCellBoard.getRight();
                case Up:
                    return currentCellBoard.getUp();
                case Down:
                    return currentCellBoard.getDown();
                case LeftDown:
                    return currentCellBoard.getLeftDown();
                case RightDown:
                    return currentCellBoard.getRightDown();
                case LeftUp:
                    return currentCellBoard.getLeftUp();
                case RightUp:
                    return currentCellBoard.getRightUp();
            }
        }

        return null;
    }

    public CellBoard next() {
        CellBoard returnCellBoard = currentCellBoard;
        currentCellBoard = onlyNext();
        return returnCellBoard;
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }
}
