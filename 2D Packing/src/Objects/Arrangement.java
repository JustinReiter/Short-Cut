package Objects;

public class Arrangement {
    Coordinate coordinate;
    int direction;
    /*
        0: North
        1: 90 degrees clockwise
        2: 180 degrees clockwise
        3: 270 degrees clockwise
     */

    public Arrangement(Coordinate coordinate, int direction) {
        this.coordinate = new Coordinate(coordinate);
        this.direction = direction;
    }

    public Arrangement(Arrangement arrangement) {
        this.coordinate = new Coordinate(arrangement.coordinate);
        this.direction = arrangement.direction;
    }
}
