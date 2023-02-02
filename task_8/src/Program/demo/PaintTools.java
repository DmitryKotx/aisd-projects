package Program.demo;

import java.awt.*;

public class PaintTools {

    public static class Circle {
        private static final int exRadius = 30;
        private static final int inRadius = (exRadius / 14) * 13;
        private static final Font valueFont = new Font("Monospaced", Font.BOLD, 35);

        private Color fillColor = Color.WHITE;
        private Color borderColor = Color.BLACK;
        private Color valueColor = Color.BLACK;

        private int xExternal;
        private int yExternal;
        private int xInternal;
        private int yInternal;
        private int xCenter;
        private int yCenter;
        private int xValue;
        private int yValue;
        private int value;

        public Circle(int x, int y, int v) {
            xCenter = x;
            yCenter = y;
            xExternal = x - exRadius;
            yExternal = y - exRadius;
            xInternal = x - inRadius;
            yInternal = y - inRadius;
            xValue = ((Double) (x - valueFont.getSize() / 2.2)).intValue();
            yValue = ((Double) (y - valueFont.getSize() / 2.2)).intValue();
            value = v;
        }

        public int getXExternal() {
            return xExternal;
        }

        public int getYExternal() {
            return yExternal;
        }

        public int getExRadius() {
            return exRadius;
        }

        public int getInRadius() {
            return inRadius;
        }

        public int getXInternal() {
            return xInternal;
        }

        public int getYInternal() {
            return yInternal;
        }

        public int getXCenter() {
            return xCenter;
        }

        public int getYCenter() {
            return yCenter;
        }

        public Color getFillColor() {
            return fillColor;
        }

        public Color getBorderColor() {
            return borderColor;
        }

        public Color getValueColor() {
            return valueColor;
        }

        public int getXValue() {
            return xValue;
        }

        public int getYValue() {
            return yValue;
        }

        public Font getValueFont() {
            return valueFont;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public void setPosition(int x, int y) {
            xExternal = x - exRadius;
            yExternal = y - exRadius;
            xInternal = x - inRadius;
            yInternal = y - inRadius;
            xCenter = x;
            yCenter = y;
            xValue = ((Double) (x - valueFont.getSize() / 2.2)).intValue();
            yValue = ((Double) (y - valueFont.getSize() / 2.2)).intValue();
        }

        public void setColors(Color fillColor, Color borderColor, Color valueColor) {
            this.fillColor = fillColor;
            this.borderColor = borderColor;
            this.valueColor = valueColor;
        }
    }

    public static class Connection {
        private Color lineColor = Color.BLACK;
        private final static int clickOffset = 20;

        private Circle beginCircle;
        private Circle endCircle;
        private boolean isConfirmed;
        private Integer xBegin;
        private Integer yBegin;
        private Integer xEnd;
        private Integer yEnd;
        private Double k;

        public Connection() {
            beginCircle = null;
            endCircle = null;
            xBegin = null;
            yBegin = null;
            xEnd = null;
            yEnd = null;
            k = null;
            isConfirmed = false;
        }
        public void setConfirmed(boolean isConfirmed) {
            this.isConfirmed = isConfirmed;
        }

        public void setBeginCircle(Circle beginCircle) {
            this.beginCircle = beginCircle;
            this.xBegin = beginCircle.getXExternal() + beginCircle.getExRadius();
            this.yBegin = beginCircle.getYExternal() + beginCircle.getExRadius();
        }

        public void setEndCircle(Circle endCircle) {
            this.endCircle = endCircle;
            this.xEnd = endCircle.getXExternal() + endCircle.getExRadius();
            this.yEnd = endCircle.getYExternal() + endCircle.getExRadius();
            countK();

        }
        private void countK() {
            double dY = Math.abs(yEnd - yBegin);
            double dX = Math.abs(xEnd - xBegin);
            if ((xBegin > xEnd && yBegin > yEnd) || (xBegin < xEnd && yBegin < yEnd)) {
                dY *= -1;
            }
            k = dY / dX;
        }

        public Color getLineColor() {
            return lineColor;
        }

        public boolean isConfirmed() {
            return isConfirmed;
        }

        public Integer getXBegin() {
            return xBegin;
        }

        public Integer getYBegin() {
            return yBegin;
        }

        public Integer getXEnd() {
            return xEnd;
        }

        public Integer getYEnd() {
            return yEnd;
        }

        public Circle getBeginCircle() {
            return beginCircle;
        }

        public Circle getEndCircle() {
            return  endCircle;
        }

        public double getK() {
            return k;
        }

        public void setPosition(int x1, int y1, int x2, int y2) {
            xBegin = x1;
            yBegin = y1;
            xEnd = x2;
            yEnd = y2;
            countK();
        }

        public void setLineColor(Color color) {
            lineColor = color;
        }

        public void clear() {
            beginCircle = null;
            endCircle = null;
            xBegin = null;
            yBegin = null;
            xEnd = null;
            yEnd = null;
            isConfirmed = false;
            k = null;
        }

        public boolean isClickPointBelongsArea(int x, int y) {
            boolean accessoryX = (xBegin <= x && x <= xEnd) || (xEnd <= x && x <= xBegin);
            boolean accessoryY = (yBegin <= y && y <= yEnd) || (yEnd <= y && y <= yBegin);
            int leftPart = y - getYBegin();
            int rightPart = ((Double) (-1 * getK() * (x - getXBegin()))).intValue();
            return Math.abs(leftPart - rightPart) <= clickOffset && accessoryX && accessoryY;
        }

        public boolean isEqual(Connection connector) {
            return this.getBeginCircle() == connector.getBeginCircle() && this.getEndCircle() == connector.getEndCircle() ||
                    this.getBeginCircle() == connector.getEndCircle() && this.getEndCircle() == connector.getBeginCircle();
        }
    }
}