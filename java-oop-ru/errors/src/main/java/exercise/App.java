package exercise;

// BEGIN
class App {
    public static void printSquare(Circle circle) {
        try {
            System.out.println((int) Math.round(circle.getSquare()));
        } catch (NegativeRadiusException exception) {
            System.out.println(exception.getMessage());
        } finally {
            System.out.println("Вычисление окончено");
        }
    }
}
// END
