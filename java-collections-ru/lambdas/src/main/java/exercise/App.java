package exercise;

// BEGIN
class App {
    public static String[][] enlargeArrayImage(String[][] matrix) {
        int length = matrix.length;

        if (length == 0) {
            return new String[0][0];
        }
        String[][] result = new String[length * 2][length * 2];

        for (int i = 0; i < matrix.length; i += 1) {
            for (int j = 0; j < matrix.length; j++) {
                result[2 * i][2 * j] = matrix[i][j];
                result[2 * i][2 * j + 1] = matrix[i][j];
                result[2 * i + 1][2 * j] = matrix[i][j];
                result[2 * i + 1][2 * j + 1] = matrix[i][j];
            }
        }
        return result;
    }
}
// END
