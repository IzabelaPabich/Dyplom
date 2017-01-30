package model.mathTables;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import model.sheet.Graph;
import model.sheet.GraphMark;

/**
 * Created by Alebazi on 2017-01-30.
 */
public class GraphTable extends Graph{

    private BooleanProperty firstColChecked;
    private BooleanProperty IColChecked;
    private BooleanProperty secondColChecked;
    private BooleanProperty IIColChecked;
    private BooleanProperty thirdColChecked;
    private BooleanProperty IIIColChecked;

    public GraphTable(String firstComp, GraphMark operation12, String secondComp, GraphMark operation23, String thirdComp,
                      GraphMark operation31, boolean firstColCheck, boolean IColCheck, boolean secondColCheck, boolean IIColCheck,
                      boolean thirdColCheck, boolean IIIColCheck) {
        this.firstComp = firstComp;
        this.operation12 = operation12;
        this.secondComp = secondComp;
        this.operation23 = operation23;
        this.thirdComp = thirdComp;
        this.operation31 = operation31;
        this.firstColChecked = new SimpleBooleanProperty(firstColCheck);
        this.IColChecked = new SimpleBooleanProperty(IColCheck);
        this.secondColChecked = new SimpleBooleanProperty(secondColCheck);
        this.IIColChecked = new SimpleBooleanProperty(IIColCheck);
        this.thirdColChecked = new SimpleBooleanProperty(thirdColCheck);
        this.IIIColChecked = new SimpleBooleanProperty(IIIColCheck);
    }

    public boolean getFirstColChecked() {
        return firstColChecked.get();
    }

    public BooleanProperty firstColCheckedProperty() {
        return firstColChecked;
    }

    public void setFirstColChecked(boolean firstColChecked) {
        this.firstColChecked.set(firstColChecked);
    }

    public boolean getIColChecked() {
        return IColChecked.get();
    }

    public BooleanProperty IColCheckedProperty() {
        return IColChecked;
    }

    public void setIColChecked(boolean IColChecked) {
        this.IColChecked.set(IColChecked);
    }

    public boolean getSecondColChecked() {
        return secondColChecked.get();
    }

    public BooleanProperty secondColCheckedProperty() {
        return secondColChecked;
    }

    public void setSecondColChecked(boolean secondColChecked) {
        this.secondColChecked.set(secondColChecked);
    }

    public boolean getIIColChecked() {
        return IIColChecked.get();
    }

    public BooleanProperty IIColCheckedProperty() {
        return IIColChecked;
    }

    public void setIIColChecked(boolean IIColChecked) {
        this.IIColChecked.set(IIColChecked);
    }

    public boolean getThirdColChecked() {
        return thirdColChecked.get();
    }

    public BooleanProperty thirdColCheckedProperty() {
        return thirdColChecked;
    }

    public void setThirdColChecked(boolean thirdColChecked) {
        this.thirdColChecked.set(thirdColChecked);
    }

    public boolean getIIIColChecked() {
        return IIIColChecked.get();
    }

    public BooleanProperty IIIColCheckedProperty() {
        return IIIColChecked;
    }

    public void setIIIColChecked(boolean IIIColChecked) {
        this.IIIColChecked.set(IIIColChecked);
    }
}
