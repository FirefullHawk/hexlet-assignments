package exercise;

// BEGIN
class Segment {
    private final Point begin;
    private final Point end;

    Segment(Point begin, Point end) {
        this.begin = begin;
        this.end = end;
    }

    public Point getBeginPoint() {
        return this.begin;
    }

    public Point getEndPoint() {
        return this.end;
    }

    public Point getMidPoint() {
        int xMid = (begin.getX() + end.getX()) / 2;
        int yMid = (begin.getY() + end.getY()) / 2;
        return new Point(xMid, yMid);
    }
}
// END
