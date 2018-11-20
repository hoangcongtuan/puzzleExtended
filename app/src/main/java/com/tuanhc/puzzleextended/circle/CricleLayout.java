package com.tuanhc.puzzleextended.circle;

public class CricleLayout extends CircleBaseLayout {
    public CricleLayout(int theme) {
        super(theme);
    }

    @Override
    public void layout() {
        makeCircleCenter();
    }

    @Override
    public int getThemeCount() {
        return 5;
    }
}
