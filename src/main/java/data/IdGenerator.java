package data;

public abstract class IdGenerator {
    static int id = 0;

    static public int getId() {
        id++;
        return id;
    }
}
