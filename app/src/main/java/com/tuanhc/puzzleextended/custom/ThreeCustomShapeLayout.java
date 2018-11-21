package com.tuanhc.puzzleextended.custom;

import com.xiaopo.flying.puzzle.Line;

public class ThreeCustomShapeLayout extends CustomShapeLayout {
    public ThreeCustomShapeLayout(int theme) {
        super(theme);
    }

    @Override
    public int getThemeCount() {
        return 5;
    }

    @Override
    public void layout() {
        addLine(0, Line.Direction.HORIZONTAL, 0.5f);
        addShape(SHAPE_CIRCLE, CENTER_CENTER, 0.6f);
    }
}
