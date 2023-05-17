package exercise;

// BEGIN
class Circle {
    Point center;
    int radius;

    Circle(Point center, int radius) {
        this.center = center;
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }

    double getSquare() throws NegativeRadiusException {
        if (this.radius < 0) {
            throw new NegativeRadiusException("Не удалось посчитать площадь");
        } else {
            return Math.PI * this.radius * this.radius;
        }
    }
}
// END
