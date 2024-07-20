package kiosk;

public class Menu {
    protected String name;
    protected String explanation;

    public Menu(String name, String explanation) {
        this.name = name;
        this.explanation = explanation;
    }

    public String show() {
        return String.format("%-10s | %s", name, explanation);
    }
}
