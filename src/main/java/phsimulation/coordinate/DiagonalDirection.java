package main.java.phsimulation.coordinate;

public enum DiagonalDirection implements Direction {
    UP_RIGHT(1, 1),
    DOWN_RIGHT(1, -1),
    DOWN_LEFT(-1, -1),
    UP_LEFT(-1, 1);

    public final int dx;
    public final int dy;

    DiagonalDirection(int dx, int dy) {
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
