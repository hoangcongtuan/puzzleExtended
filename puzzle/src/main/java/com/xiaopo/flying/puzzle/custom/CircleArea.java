package com.xiaopo.flying.puzzle.custom;

import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.Region;

import com.xiaopo.flying.puzzle.Area;
import com.xiaopo.flying.puzzle.Line;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class CircleArea extends CustomArea {

    private PointF pCenter;
    private float radius;
    private float padding;

    private Path areaPath = new Path();
    private RectF areaRect = new RectF();
    private Region areaRegion = new Region();
    private PointF[] handleBarPoints = new PointF[2];

    private float paddingLeft;
    private float paddingTop;
    private float paddingRight;
    private float paddingBottom;
    private float radian;

    CircleArea() {
        handleBarPoints[0] = new PointF();
        handleBarPoints[1] = new PointF();
    }

    CircleArea(RectF baseRect) {
        this();
        setBaseRect(baseRect);
    }

    private void setBaseRect(RectF baseRect) {
        pCenter = new PointF((baseRect.left + baseRect.right) / 2, (baseRect.top + baseRect.bottom) / 2);
        radius = Math.min((baseRect.right - baseRect.left) / 2, (baseRect.bottom - baseRect.top) / 2);
    }

    CircleArea(CircleArea src) {
        this.pCenter = src.pCenter;
        this.radius = src.radius;

        handleBarPoints[0] = new PointF();
        handleBarPoints[1] = new PointF();
    }

    @Override public float left() {
//        return lineLeft.minX() + paddingLeft;
        return pCenter.x + padding - radius;
    }

    @Override public float top() {
//        return lineTop.minY() + paddingTop;
        return pCenter.y + padding - radius;
    }

    @Override public float right() {
//        return lineRight.maxX() - paddingRight;
        return pCenter.x - padding + radius;
    }

    @Override public float bottom() {
//        return lineBottom.maxY() - paddingBottom;
        return pCenter.y - padding + radius;
    }

    @Override public float centerX() {
//        return (left() + right()) / 2;
        return pCenter.x;
    }

    @Override public float centerY() {
//        return (top() + bottom()) / 2;
        return pCenter.y;
    }

    @Override public float width() {
        return right() - left();
    }

    @Override public float height() {
        return bottom() - top();
    }

    @Override public PointF getCenterPoint() {
        return new PointF(centerX(), centerY());
    }

    @Override public boolean contains(PointF point) {
        return contains(point.x, point.y);
    }

    @Override public boolean contains(float x, float y) {
//        return getAreaRect().contains(x, y);
        return getAreaRegion().contains((int)x, (int)y);
    }

    @Override public boolean contains(Line line) {
        // TODO: 11/20/18 Circle not support now
        return false;
//        return lineLeft == line || lineTop == line || lineRight == line || lineBottom == line;
    }

    @Override public Path getAreaPath() {
        areaPath.reset();
//    areaPath.addRoundRect(getAreaRect(), radian, radian, Path.Direction.CCW);
        areaPath.addCircle(pCenter.x, pCenter.y,
                radius - padding * 2,
                Path.Direction.CCW);
        //areaPath.addRect(getAreaRect(), Path.Direction.CCW);
        return areaPath;
    }

    @Override public RectF getAreaRect() {
        areaRect.set(left(), top(), right(), bottom());
        return areaRect;
    }

    @Override
    public Region getAreaRegion() {
        RectF rectF = new RectF();
        Path path = getAreaPath();
        path.computeBounds(rectF, true);
        areaRegion.setPath(path, new Region((int) rectF.left, (int) rectF.top, (int) rectF.right, (int) rectF.bottom));
        return areaRegion;
    }

    @Override public List<Line> getLines() {
        // TODO: 11/20/18 Circle do not have any Line
        return Arrays.asList();
//        return Arrays.asList((Line) lineLeft, lineTop, lineRight, lineBottom);
    }

    @Override public PointF[] getHandleBarPoints(Line line) {
        // TODO: 11/20/18 Circle not support this feature
//        if (line == lineLeft) {
            handleBarPoints[0].x = left();
            handleBarPoints[0].y = top() + height() / 4;
            handleBarPoints[1].x = left();
            handleBarPoints[1].y = top() + height() / 4 * 3;
//        } else if (line == lineTop) {
//            handleBarPoints[0].x = left() + width() / 4;
//            handleBarPoints[0].y = top();
//            handleBarPoints[1].x = left() + width() / 4 * 3;
//            handleBarPoints[1].y = top();
//        } else if (line == lineRight) {
//            handleBarPoints[0].x = right();
//            handleBarPoints[0].y = top() + height() / 4;
//            handleBarPoints[1].x = right();
//            handleBarPoints[1].y = top() + height() / 4 * 3;
//        } else if (line == lineBottom) {
//            handleBarPoints[0].x = left() + width() / 4;
//            handleBarPoints[0].y = bottom();
//            handleBarPoints[1].x = left() + width() / 4 * 3;
//            handleBarPoints[1].y = bottom();
//        }
        return handleBarPoints;
    }

    @Override public float radian() {
        return radian;
    }

    @Override public void setRadian(float radian) {
        this.radian = radian;
    }

    @Override public float getPaddingLeft() {
        return paddingLeft;
    }

    @Override public float getPaddingTop() {
        return paddingTop;
    }

    @Override public float getPaddingRight() {
        return paddingRight;
    }

    @Override public float getPaddingBottom() {
        return paddingBottom;
    }

    @Override public void setPadding(float padding) {
        setPadding(padding, padding, padding, padding);
    }

    @Override public void setPadding(float paddingLeft, float paddingTop, float paddingRight,
                                     float paddingBottom) {
        this.paddingLeft = paddingLeft;
        this.paddingTop = paddingTop;
        this.paddingRight = paddingRight;
        this.paddingBottom = paddingBottom;

        this.padding = paddingBottom;
    }

    @Override
    public void setCutoffPath(Path path) {
        // TODO: 11/20/18 cutOffPath for CircleArea
    }
}
