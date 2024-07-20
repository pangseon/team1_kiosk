package kiosk;

public class Product extends Menu {
    public double price;
    public int num = 0;
    private boolean isNew; // 상품이 새로운지 여부를 나타내는 필드

    public Product(String name, double price, String explanation) {
        super(name, explanation);
        this.price = price;
        this.isNew = false; // 초기에는 새 상품이 아님
    }

    @Override
    public String show() {
        return String.format("%-20s | W %-5.1f| %s", name, price, explanation);
    }

    public String cartShow() {
        return String.format("%-20s | W %-5.1f| %d개 | %s", name, price, num, explanation);
    }

    // isNew 필드의 getter
    public boolean isNew() {
        return isNew;
    }

    // isNew 필드의 setter
    public void setNew(boolean isNew) {
        this.isNew = isNew;
    }
}


