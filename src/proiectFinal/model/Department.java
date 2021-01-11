package proiectFinal.model;

public enum Department {
    IT(1),
    HR(2),
    SALES(3),
    FMG(4),
    MANAGER(5),
    TRAINER(6);

    private int value;

    Department(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
