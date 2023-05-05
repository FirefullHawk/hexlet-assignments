package exercise;

// BEGIN
class ReversedSequence implements CharSequence {
    private final String inputText;

    ReversedSequence(String inputText) {
        this.inputText = inputText;
    }

    private String getReverse(String input) {
        return new StringBuilder(input).reverse().toString();
    }

    @Override
    public int length() {
        return inputText.length();
    }

    @Override
    public char charAt(int i) {
        return getReverse(inputText).charAt(i);
    }

    @Override
    public CharSequence subSequence(int i, int i1) {
        if (i1 > inputText.length()) {
            i1 = inputText.length();
        }
        if (i < 0) {
            i = 0;
        }
        return getReverse(inputText).subSequence(i, i1);
    }

    @Override
    public String toString() {
        return getReverse(inputText);
    }
}
// END
