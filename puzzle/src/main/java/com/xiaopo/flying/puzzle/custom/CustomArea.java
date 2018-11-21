package com.xiaopo.flying.puzzle.custom;

import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;

import com.xiaopo.flying.puzzle.Area;
import com.xiaopo.flying.puzzle.Line;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public abstract class CustomArea implements Area{
    public CustomArea() {
        //not thing
    }

    protected static class AreaComparator implements Comparator<Area> {
        @Override public int compare(Area lhs, Area rhs) {
            if (lhs.top() < rhs.top()) {
                return -1;
            } else if (lhs.top() == rhs.top()) {
                if (lhs.left() < rhs.left()) {
                    return -1;
                } else if (lhs.left() == rhs.left()) {
                    return 0;
                } else {
                    return 1;
                }
            } else {
                return 1;
            }
        }
    }
}
