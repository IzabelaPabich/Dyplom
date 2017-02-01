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
    private String operation12String;
    private String operation23String;
    private String operation31String;

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
        this.operation12String = operation12.toString();
        this.operation23String = operation23.toString();
        this.operation31String = operation31.toString();
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

    public String getOperation12String() {
        return operation12String;
    }

    public void setOperation12String(String operation12String) {
        this.operation12String = operation12String;
    }

    public String getOperation23String() {
        return operation23String;
    }

    public void setOperation23String(String operation23String) {
        this.operation23String = operation23String;
    }

    public String getOperation31String() {
        return operation31String;
    }

    public void setOperation31String(String operation31String) {
        this.operation31String = operation31String;
    }
}
