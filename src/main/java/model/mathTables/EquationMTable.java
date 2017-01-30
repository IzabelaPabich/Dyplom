package model.mathTables;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import model.sheet.EquationM;

/**
 * Created by Alebazi on 2017-01-30.
 */
public class EquationMTable extends EquationM{

    private BooleanProperty firstCol1Checked;
    private BooleanProperty IColChecked;
    private BooleanProperty firstCol2Checked;
    private BooleanProperty IIIColChecked;
    private BooleanProperty secondCol1Checked;
    private BooleanProperty IIColChecked;
    private BooleanProperty secondCol2Checked;

    public EquationMTable(String firstComp1, String firstOperation, String firstComp2, String equationMark, String secondComp1,
                            String secondOperation, String secondComp2, boolean firstCol1Check, boolean IColCheck, boolean firstCol2Check,
                            boolean IIIColCheck, boolean secondCol1Check, boolean IIColCheck, boolean secondCol2Check) {
        this.firstComp1 = firstComp1;
        this.firstOperation = firstOperation;
        this.firstComp2 = firstComp2;
        this.equationMark = equationMark;
        this.secondComp1 = secondComp1;
        this.secondOperation = secondOperation;
        this.secondComp2 = secondComp2;
        this.firstCol1Checked = new SimpleBooleanProperty(firstCol1Check);
        this.IColChecked = new SimpleBooleanProperty(IColCheck);
        this.firstCol2Checked = new SimpleBooleanProperty(firstCol2Check);
        this.IIIColChecked = new SimpleBooleanProperty(IIIColCheck);
        this.secondCol1Checked = new SimpleBooleanProperty(secondCol1Check);
        this.IIColChecked = new SimpleBooleanProperty(IIColCheck);
        this.secondCol2Checked = new SimpleBooleanProperty(secondCol2Check);
    }

    public boolean getFirstCol1Checked() {
        return firstCol1Checked.get();
    }

    public BooleanProperty firstCol1CheckedProperty() {
        return firstCol1Checked;
    }

    public void setFirstCol1Checked(boolean firstCol1Checked) {
        this.firstCol1Checked.set(firstCol1Checked);
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

    public boolean getFirstCol2Checked() {
        return firstCol2Checked.get();
    }

    public BooleanProperty firstCol2CheckedProperty() {
        return firstCol2Checked;
    }

    public void setFirstCol2Checked(boolean firstCol2Checked) {
        this.firstCol2Checked.set(firstCol2Checked);
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

    public boolean getSecondCol1Checked() {
        return secondCol1Checked.get();
    }

    public BooleanProperty secondCol1CheckedProperty() {
        return secondCol1Checked;
    }

    public void setSecondCol1Checked(boolean secondCol1Checked) {
        this.secondCol1Checked.set(secondCol1Checked);
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

    public boolean getSecondCol2Checked() {
        return secondCol2Checked.get();
    }

    public BooleanProperty secondCol2CheckedProperty() {
        return secondCol2Checked;
    }

    public void setSecondCol2Checked(boolean secondCol2Checked) {
        this.secondCol2Checked.set(secondCol2Checked);
    }
}
