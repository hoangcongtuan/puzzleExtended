package com.tuanhc.puzzleextended.custom;


public class TwoCustomShapeLayout extends CustomShapeLayout {
    public TwoCustomShapeLayout(int theme) {
        super(theme);
    }

    @Override
    public int getThemeCount() {
        return 5;
    }

    @Override
    public void layout() {
        addShape(SHAPE_CIRCLE, BOTTOM_CENTER, 0.4f);
    }
}
