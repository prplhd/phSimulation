package main.java.phsimulation;
import main.java.phsimulation.coordinate.Coordinate;
import main.java.phsimulation.entities.Entity;
import main.java.phsimulation.exceptions.InvalidCoordinateException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class WorldMap {
    private final int height;
    private final int width;
    private final Map<Coordinate, Entity> entities = new HashMap<>();

    public WorldMap(int height, int width) {
        this.height = height;
        this.width = width;
    }

    public Map<Coordinate, Entity> toMap() {
        return new HashMap<>(entities);
    }

    public void ensureInBounds(Coordinate coordinate) {
        int x = coordinate.x();
        int y = coordinate.y();
        boolean isXInBounds = (x >= 0 && x < width);
        boolean isYInBounds = (y >= 0 && y < height);

        if (!isXInBounds || !isYInBounds) {
            throw new InvalidCoordinateException(coordinate, width, height);
        }
    }

    public boolean isInBounds(Coordinate coordinate) {
        int x = coordinate.x();
        int y = coordinate.y();
        boolean isXInBounds = (x >= 0 && x < width);
        boolean isYInBounds = (y >= 0 && y < height);

        return isXInBounds && isYInBounds;
    }

    public Optional<Entity> getEntity(Coordinate coordinate) {
        ensureInBounds(coordinate);
        return Optional.ofNullable(entities.get(coordinate));
    }

    public void setEntity(Coordinate coordinate, Entity entity) {
        ensureInBounds(coordinate);
        entities.put(coordinate, entity);
    }

    public void removeEntity(Coordinate coordinate) {
        ensureInBounds(coordinate);
        entities.remove(coordinate);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
