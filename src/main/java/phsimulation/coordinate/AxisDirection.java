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
    public Coordinate apply(Coordinate coordinate) {
        return new Coordinate(coordinate.x() + dx, coordinate.y() + dy);
    }
}
