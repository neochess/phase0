package ru.neochess.core;

/**
 * Клетка доски. Содержит ссылки на соседние клеткки и на фигуру.
 * Created by diviz on 10.12.2016.
 */
public class CellBoard {

    private CoreBoard coreBoard;
    private CoreFigure coreFigure;
    private final Cell cell;
    private CellBoard left,
            right,
            up,
            down,
            leftDown,
            rightDown,
            leftUp,
            rightUp;

    public CellBoard(int x, int y, CoreBoard coreBoard) {
        this(x, y, coreBoard, null);
                //this(x, y, null, null, null, null, null, null, null, null, null);
    }

    public CellBoard(int x, int y, CoreBoard coreBoard, CoreFigure coreFigure) {
        cell = new Cell(x, y);
        this.coreFigure = coreFigure;
        this.coreBoard = coreBoard;
        //this(x, y, figure, null, null, null, null, null, null, null, null);
    }

   /* public CellBoard(int x, int y, CellBoard left, CellBoard right, CellBoard up, CellBoard down, CellBoard leftDown, CellBoard rightDown, CellBoard leftUp, CellBoard rightUp) {
        this(x, y, null, left, right, up, down, leftDown, rightDown, leftUp, rightUp);

    }*/


    /*public CellBoard(int x, int y, Figure figure, CellBoard left, CellBoard right, CellBoard up, CellBoard down, CellBoard leftDown, CellBoard rightDown, CellBoard leftUp, CellBoard rightUp) {
        this.figure = figure;
        this.left = left;
        this.right = right;
        this.up = up;
        this.down = down;
        this.leftDown = leftDown;
        this.rightDown = rightDown;
        this.leftUp = leftUp;
        this.rightUp = rightUp;
        cell = new Cell(x, y);

    }*/

    public Cell getCell() {
        return cell;
    }

    @Override
    public String toString() {
        return cell.toString();
    }

    public IteratorCell getIterator(AdjacentCell adjacentCell) {
        return new IteratorCell(this, adjacentCell);
    }



    /**
     * Getter for property 'figure'.
     *
     * @return Value for property 'figure'.
     */
    public CoreFigure getCoreFigure() {
        return coreFigure;
    }

    /**
     * Устанавливаем в клетку фигуру,
     *
     * @param coreFigure - фигура
     */
    public void setCoreFigure(CoreFigure coreFigure) {
        this.coreFigure = coreFigure;
    }

    /**
     * Getter for property 'left'.
     *
     * @return Value for property 'left'.
     */
    public CellBoard getLeft() {

            if (this.cell.getX() > 0)
                left = coreBoard.getCellByIndex(this.cell.getX() - 1, this.cell.getY());
            else left = null;

        return left;
    }



    /**
     * Getter for property 'right'.
     *
     * @return Value for property 'right'.
     */
    public CellBoard getRight() {
        if (this.cell.getX() < 9)
            right = coreBoard.getCellByIndex(this.cell.getX() + 1, this.cell.getY());
        else right = null;

        return right;
    }



    /**
     * Getter for property 'up'.
     *
     * @return Value for property 'up'.
     */
    public CellBoard getUp() {
        if (this.cell.getY() > 0)
            up = coreBoard.getCellByIndex(this.cell.getX(), this.cell.getY() - 1);
        else up = null;

        return up;
        // Y идет сверху вниз (тогда ка кнумирация на доске снизу вверх) b9 (1,1) b8 (1,2)
    }



    /**
     * Getter for property 'down'.
     *
     * @return Value for property 'down'.
     */
    public CellBoard getDown() {

        if (this.cell.getY() < 9)
            down = coreBoard.getCellByIndex(this.cell.getX(), this.cell.getY() + 1);
        else down = null;

        return down;
        // Y идет сверху вниз (тогда ка кнумирация на доске снизу вверх) b9 (1,1) b8 (1,2)
    }


    /**
     * Getter for property 'leftDown'.
     *
     * @return Value for property 'leftDown'.
     */
    public CellBoard getLeftDown() {

        if ((this.cell.getY() < 9) && (this.cell.getX() > 0))
        {
        leftDown = coreBoard.getCellByIndex(this.cell.getX() - 1, this.cell.getY() + 1);}
        else leftDown = null;

        return leftDown;
        // Y идет сверху вниз (тогда ка кнумирация на доске снизу вверх) b9 (1,1) b8 (1,2)
    }



    /**
     * Getter for property 'rightDown'.
     *
     * @return Value for property 'rightDown'.
     */
    public CellBoard getRightDown() {

        if ((this.cell.getY() < 9) && (this.cell.getX() < 9))
        {
            rightDown = coreBoard.getCellByIndex(this.cell.getX() + 1, this.cell.getY() + 1);}
        else rightDown = null;
        return rightDown;
    }

    /**
     * Getter for property 'leftUp'.
     *
     * @return Value for property 'leftUp'.
     */
    public CellBoard getLeftUp() {
        if ((this.cell.getY() > 0) && (this.cell.getX() > 0))
        {
            leftUp = coreBoard.getCellByIndex(this.cell.getX() - 1, this.cell.getY() - 1);}
        else leftUp = null;

        return leftUp;
        // Y идет сверху вниз (тогда ка кнумирация на доске снизу вверх)
    }



    /**
     * Getter for property 'rightUp'.
     *
     * @return Value for property 'rightUp'.
     */
    public CellBoard getRightUp() {
        if ((this.cell.getY() > 0) && (this.cell.getX() < 9))
        {
            rightUp = coreBoard.getCellByIndex(this.cell.getX() + 1, this.cell.getY() - 1);}
        else rightUp = null;
        return rightUp;
        // Y идет сверху вниз (тогда ка кнумирация на доске снизу вверх)
    }


}
