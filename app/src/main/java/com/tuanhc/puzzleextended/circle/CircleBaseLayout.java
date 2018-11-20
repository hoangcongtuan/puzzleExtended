package com.tuanhc.puzzleextended.circle;

import android.util.Log;

import com.xiaopo.flying.puzzle.custom.CirclePuzzleLayout;

public abstract class CircleBaseLayout extends CirclePuzzleLayout {
    static final String TAG = "NumberStraightLayout";
    protected int theme;

    public CircleBaseLayout(int theme) {
        if (theme >= getThemeCount()) {
            Log.e(TAG, "NumberStraightLayout: the most theme count is "
                    + getThemeCount()
                    + " ,you should let theme from 0 to "
                    + (getThemeCount() - 1)
                    + " .");
        }
        this.theme = theme;
    }

    public abstract int getThemeCount();

    public int getTheme() {
        return theme;
    }
}
