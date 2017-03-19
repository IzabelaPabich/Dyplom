package model.mathTables;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import model.sheet.Equation;

/**
 * Created by Alebazi on 2017-01-30.
 */
public class EquationTable extends Equation {

    private BooleanProperty firstColChecked;
    private BooleanProperty IColChecked;
    private BooleanProperty secondColChecked;
    private BooleanProperty IIColChecked;
    private BooleanProperty thirdColChecked;

    public EquationTable() {

    }

    public EquationTable(String firstComp, String operation, String secondComp, String equationMark, String result,
                         boolean firstColCheck, boolean IColCheck, boolean secondColCheck, boolean IIColCheck, boolean thirdColCheck) {
        this.firstComp = firstComp;
        this.operation = operation;
        this.secondComp = secondComp;
        this.equationMark = equationMark;
        this.result = result;
        this.firstColChecked = new SimpleBooleanProperty(firstColCheck);
        this.IColChecked = new SimpleBooleanProperty(IColCheck);
        this.secondColChecked = new SimpleBooleanProperty(secondColCheck);
        this.IIColChecked = new SimpleBooleanProperty(IIColCheck);
        this.thirdColChecked = new SimpleBooleanProperty(thirdColCheck);
    }

    public boolean getFirstColChecked() {
        return firstColChecked.get();
    }

    public BooleanProperty firstColCheckedProperty() {
        return firstColChecked;
    }

    public boolean getIColChecked() {
        return IColChecked.get();
    }

    public BooleanProperty IColCheckedProperty() {
        return IColChecked;
    }

    public boolean getSecondColChecked() {
        return secondColChecked.get();
    }

    public BooleanProperty secondColCheckedProperty() {
        return secondColChecked;
    }

    public boolean getIIColChecked() {
        return IIColChecked.get();
    }

    public BooleanProperty IIColCheckedProperty() {
        return IIColChecked;
    }

    public boolean getThirdColChecked() {
        return thirdColChecked.get();
    }

    public BooleanProperty thirdColCheckedProperty() {
        return thirdColChecked;
    }

    public void setFirstColChecked(boolean firstColChecked) {
        this.firstColChecked.set(firstColChecked);
    }

    public void setIColChecked(boolean IColChecked) {
        this.IColChecked.set(IColChecked);
    }

    public void setSecondColChecked(boolean secondColChecked) {
        this.secondColChecked.set(secondColChecked);
    }

    public void setIIColChecked(boolean IIColChecked) {
        this.IIColChecked.set(IIColChecked);
    }

    public void setThirdColChecked(boolean thirdColChecked) {
        this.thirdColChecked.set(thirdColChecked);
    }
}
