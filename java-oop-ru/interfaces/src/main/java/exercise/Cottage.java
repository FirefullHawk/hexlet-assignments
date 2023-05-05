package exercise;

// BEGIN
class Cottage implements Home {
    private double area;
    private int floorCount;

    Cottage(double area, int floorCount) {
        this.area = area;
        this.floorCount = floorCount;
    }

    @Override
    public double getArea() {
        return area;
    }

    @Override
    public int compareTo(Home toCompare) {
        if (this.getArea() == toCompare.getArea()) {
            return 0;
        } else {
            return this.getArea() > toCompare.getArea() ? 1 : -1;
        }
    }

    @Override
    public String toString() {
        return this.floorCount + " этажный коттедж площадью " + getArea() + " метров";
    }
}
// END
