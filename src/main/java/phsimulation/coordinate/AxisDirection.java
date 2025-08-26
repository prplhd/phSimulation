package main.java.phsimulation.coordinate;

public enum AxisDirection implements Direction {
    UP(0, 1),
    RIGHT(1, 0),
    DOWN(0, -1),
    LEFT(-1, 0);

    public final int dx;
    public final int dy;

    AxisDirection(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }


    @Override
    public int dx() {
        return dx;
    }

    @Override
    public int dy() {
        return dy;
    }
}
