package ru.neochess.core;

import ru.neochess.core.GeneratorsMove.IGeneratorMove;
import ru.neochess.core.Move.Move;

import java.util.ArrayList;
import java.util.List;

/**
 * Фигура на игровом поле
 * Created by diviz on 10.12.2016.
 */
public class CoreFigure {
    private List<IGeneratorMove> listGeneratorMove = new ArrayList<>();
    private TypeGamer typeGamer;
    private TypeFigure typeFigure;


    public CoreFigure(ArrayList<IGeneratorMove> generatorMove, TypeGamer typeGamer, TypeFigure typeFigure) {
        this.listGeneratorMove = generatorMove;
        this.typeGamer = typeGamer;
        this.typeFigure = typeFigure;
    }

    public CoreFigure(TypeGamer typeGamer, TypeFigure typeFigure) {
        this(null, typeGamer, typeFigure);
    }

    public TypeFigure getTypeFigure() {
        return typeFigure;
    }

    public void setTypeFigure(TypeFigure typeFigure) {
        this.typeFigure = typeFigure;
    }

    public CoreFigure(TypeGamer typeGamer) {
        this(null, typeGamer, null);
    }

    public TypeGamer getTypeGamer() {
        return typeGamer;
    }

    public void setTypeGamer(TypeGamer typeGamer) {
        this.typeGamer = typeGamer;
    }

    public ArrayList<Move> getMove(CellBoard cellBoard) {
        ArrayList<Move> list = new ArrayList<>();
        for (IGeneratorMove generatorMove : listGeneratorMove) {
            list.addAll(generatorMove.getMove(cellBoard, typeGamer));
        }
        return list;
    }

    /**
     * Getter for property 'listGeneratorMove'.
     *
     * @return Value for property 'listGeneratorMove'.
     */
    public List<IGeneratorMove> getListGeneratorMove() {
        return listGeneratorMove;
    }

    /**
     * Setter for property 'listGeneratorMove'.
     *
     * @param listGeneratorMove Value to set for property 'listGeneratorMove'.
     */
    public void setListGeneratorMove(ArrayList<IGeneratorMove> listGeneratorMove) {
        this.listGeneratorMove = listGeneratorMove;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CoreFigure coreFigure = (CoreFigure) o;

        if (getListGeneratorMove() != null ? !getListGeneratorMove().equals(coreFigure.getListGeneratorMove()) : coreFigure.getListGeneratorMove() != null)
            return false;
        if (getTypeGamer() != coreFigure.getTypeGamer()) return false;
        return getTypeFigure() == coreFigure.getTypeFigure();
    }

    @Override
    public int hashCode() {
        int result = getListGeneratorMove() != null ? getListGeneratorMove().hashCode() : 0;
        result = 31 * result + (getTypeGamer() != null ? getTypeGamer().hashCode() : 0);
        result = 31 * result + (getTypeFigure() != null ? getTypeFigure().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return typeFigure + ":"+ typeGamer;
    }
}
