import java.util.*;

public class Data {
    char alphabet;
    int ID;
    List<Integer> position;

    public Data (char alphabet, int ID, List<Integer> position) {
        this.alphabet = alphabet;
        this.ID = ID;
        this.position = new ArrayList<>(position);
    }

    public char getAlphabet() {
        return alphabet;
    }

    public int getID() {
        return ID;
    }

    public List<Integer> getPosition () {
        return position;
    }
}