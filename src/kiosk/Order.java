package kiosk;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Order {
    public Product[] orderList;
    public int numProduct = 0;
    public double totalPrice = 0;
    public double sum;
    public Product[] soldProduct;
    int numOrder = 0;
    public Date orderTime;
    public String completionTime;
    private int waitingNumber; //대기 번호

    public Order() {
        this.waitingNumber = 1;
        this.orderList = new Product[100];
        this.soldProduct = new Product[100];
        this.orderTime = new Date();
    }

    public void addQuestion() {
        System.out.println("위 메뉴를 장바구니에 추가하시겠습니까?");
        System.out.println("1. 확인        2. 취소");
    }

    public void addToCart(Product p) {
        orderList[numProduct] = p;
        System.out.println(p.name + "가 장바구니에 추가되었습니다.");
        System.out.println();
        if (p.num == 0) {
            numProduct++;
            p.num++;
            soldProduct[numOrder++] = p;
        } else {
            p.num++;
        }
    }

    public void orderQuestion() {
        if (numProduct == 0) {
            System.out.println("장바구니가 비어있습니다.");
        }
        System.out.println("아래와 같이 주문 하시겠습니까?");
        System.out.println();
        System.out.println("[ Orders ]");
        sum = 0;
        for (int i = 0; i < numProduct; i++) {
            System.out.println(orderList[i].cartShow());
            if (orderList[i].num == 1) {
                sum += orderList[i].price;
            } else {
                sum += (orderList[i].price * orderList[i].num);
            }
        }
        totalPrice += sum;
        System.out.println();
        System.out.println("[ Total ]");
        System.out.println("W " + sum);
        System.out.println();
        System.out.println("1. 주문      2. 메뉴판");
    }
    public void setWaitingNumber(int number) {
        this.waitingNumber = number;
    }

    public int getWaitingNumber() {
        return waitingNumber;
    }

    public void ordering(Kiosk_Management km) {
        System.out.println("주문이 완료되었습니다!");
        System.out.println();
        System.out.println("대기 번호는 [ " + getWaitingNumber() + " ]번 입니다.");
        System.out.println("3초 후 메뉴판으로 돌아갑니다.");
        km.addOrder(this);
        numProduct = 0;
    }

    public void setCompletionTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        this.completionTime = sdf.format(new Date());
    }

    public void cancelQuestion() {
        System.out.println("진행하던 주문을 취소하시겠습니까?");
        System.out.println("1. 확인        2. 취소");
    }

    public void cancel() {
        System.out.println("진행하던 주문이 취소되었습니다.");
        System.out.println();
        numProduct = 0;
    }

    public void secretShow() {
        System.out.println("[ 총 판매 금액 현황 ]");
        System.out.println("현재까지 총 판매된 금액은 [ W " + totalPrice + " ] 입니다.");
        System.out.println();
        System.out.println("[ 총 판매 상품 목록 현황 ]");
        System.out.println("현재까지 총 판매된 상품 목록은 아래와 같습니다.");
        System.out.println();
        for (int i = 0; i < numOrder; i++) {
            String sf = String.format("- %-20s | W %.1f", soldProduct[i].name, soldProduct[i].price);
            System.out.println(sf);
        }
        System.out.println();
        System.out.println("1. 돌아가기");
    }
}


