package com.ot.devicecheck.model;

/**
 * Created by Anurag on 26-02-2018.
 */

public class CheckListItem {
    private boolean isChecked;
    private String text;

    public CheckListItem(String item) {
        this.text = item;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "CheckListItem{" +
                "isChecked=" + isChecked +
                ", text='" + text + '\'' +
                '}';
    }
}
