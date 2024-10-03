import java.util.Comparator;

public class SymData implements SymDataAPI, Comparable<SymData> {
    private char symbol;
    private int frequency;
    private String code;
    private SymData left;
    private SymData right;

    public SymData(char ch, int f, String cd, SymData lft, SymData rgt) {
        symbol = ch;
        frequency = f;
        code = cd;
        left = lft;
        right = rgt;
    }

    @Override
    public char getSymb() { return symbol; }

    @Override
    public int getFreq() { return frequency; }

    @Override
    public String getCode() { return code; }

    @Override
    public SymData getLeft() { return left; }

    @Override
    public SymData getRight() { return right; }

    @Override
    public void setCode(String cd) { code = cd; }

    @Override
    public String toString() {
        return String.format("%c, %d, %s", symbol, frequency, code);
    }

    @Override
    public int compareTo(SymData s) {
        return Comparator.comparing(SymData::getFreq)
                .thenComparing(SymData::getSymb)
                .compare(this, s);
    }

}
