public interface SymDataAPI {
    char getSymb();
    int getFreq();
    String getCode();
    SymData getLeft();
    SymData getRight();
    void setCode(String cd);
    String toString();
    int compareTo(SymData s);
}
