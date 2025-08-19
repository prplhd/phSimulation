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

    public Map<Coordinate, Entity> getEntitiesCopy() {
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

    public void moveEntity(Coordinate from, Coordinate to, Entity entity) {
        if (from.equals(to)){
            return;
        }

        Entity fromEntity = getEntity(from).orElse(null);
        if (fromEntity != entity) {
            throw new IllegalStateException("No expected entity found at %s for movement".formatted(from));
        }

        Entity toEntity = getEntity(to).orElse(null);
        if (toEntity != null) {
            throw new IllegalStateException("Target cell %s is already occupied".formatted(to));
        }

        removeEntity(from);
        setEntity(to, entity);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
