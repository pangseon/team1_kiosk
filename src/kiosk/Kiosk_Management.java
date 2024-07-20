package kiosk;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Kiosk_Management {
    private Order[] orderQueue;
    private Order[] completedOrders;
    private int orderCounter;
    private int completedOrderCounter;
    private Product[] productList;
    private int productCounter;
    private int waitingNumberCounter;

    public Kiosk_Management() {
        this.orderQueue = new Order[100];
        this.completedOrders = new Order[100];
        this.orderCounter = 0;
        this.completedOrderCounter = 0;
        this.productList = new Product[100];
        this.productCounter = 0;
        this.waitingNumberCounter = 1;
    }

    public void addOrder(Order order) {
        order.setWaitingNumber(waitingNumberCounter);
        waitingNumberCounter++;
        orderQueue[orderCounter] = order;
        orderCounter++;
    }

    public int getOrderCounter() {
        return orderCounter;
    }

    public void printMenu() {
        // [NEW MENU] 생성 후 채워짐
        System.out.println("[ NEW MENU ]");
        for (int i = 0; i < productCounter; i++) {
            if (productList[i] != null) {
                System.out.println((i + 1) + ". " + productList[i].show());
            }
        }
        System.out.println();
    }


    //1. 대기주문목록
    public void printOrderQueue() {
        Scanner sc = new Scanner(System.in);
        System.out.println("[ 대기 주문 목록 ]");
        if (orderCounter > 0) {
            for (int i = 0; i < orderCounter; i++) {
                if (orderQueue[i] != null) {
                    System.out.println("====================================");
                    Order order = orderQueue[i];
                    System.out.println("대기 번호: " + order.getWaitingNumber());
                    System.out.println("주문 상품 목록:");
                    for (int j = 0; j < order.numProduct; j++) {
                        if (order.orderList[j] != null && order.orderList[j].num > 0) {
                            System.out.println("  - " + order.orderList[j].cartShow());
                        }
                    }
                    System.out.println("주문 총 가격: W " + order.sum);
                    System.out.println("요청 사항: 없음");
                    System.out.println("주문 일시: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(order.orderTime));
                    System.out.println("완료 주문 일시: " + order.completionTime);
                    System.out.println("====================================");
                }
            }
            System.out.println("주문번호를 입력해주세요.");
            int orderNumber = sc.nextInt();
            if (orderNumber > 0 && orderNumber <= orderCounter) {
                System.out.println("위 주문을 완료했습니까?(y/n)");
                String str = sc.next();
                if (str.equals("y")) { //완료 시
                    orderQueue[orderNumber - 1].setCompletionTime();
                    completedOrders[completedOrderCounter++] = orderQueue[orderNumber - 1];
                    for (int i = orderNumber - 1; i < orderCounter - 1; i++) {
                        orderQueue[i] = orderQueue[i + 1];
                    }
                    orderQueue[orderCounter - 1] = null;
                    orderCounter--;
                    System.out.println("주문이 완료처리되었습니다."); //완료주문목록으로 넘어감
                } else if (str.equals("n")) { //완료처리 안 됨
                    System.out.println("주문이 취소처리되었습니다.");
                } else {
                    System.out.println("잘못된 주문 번호입니다.");
                }
            }
        } else {
            System.out.println("====================================");
            System.out.println("현재 주문이 없습니다.");
            System.out.println("====================================");
        }
    }

    //2. 완료주문목록
    public void printCompletedOrders() {
        System.out.println("[ 완료 주문 목록 ]"); //대기 주문 목록과 비슷
        if (completedOrderCounter > 0) {
            for (int i = 0; i < completedOrderCounter; i++) {
                if (completedOrders[i] != null) {
                    System.out.println("====================================");
                    Order order = completedOrders[i];
                    System.out.println("대기 번호: " + order.getWaitingNumber());
                    System.out.println("주문 상품 목록:");
                    for (int j = 0; j < order.numProduct; j++) {
                        if (order.orderList[j] != null && order.orderList[j].num > 0) {
                            System.out.println("  - " + order.orderList[j].cartShow());
                        }
                    }
                    System.out.println("주문 총 가격: W " + order.sum);
                    System.out.println("요청 사항: 없음");
                    System.out.println("주문 일시: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(order.orderTime));
                    System.out.println("완료 주문 일시: " + order.completionTime);
                    System.out.println("====================================");
                }
            }
        } else {
            System.out.println("====================================");
            System.out.println("현재 완료된 주문이 없습니다.");
            System.out.println("====================================");
        }
    }

    //3.상품생성 코드
    public void createProduct() {
        Scanner sc = new Scanner(System.in);
        System.out.println("[ 상품 생성 ]");
        System.out.print("상품 이름: ");
        String name = sc.nextLine();
        System.out.print("상품 설명: ");
        String explanation = sc.nextLine();
        System.out.print("상품 가격: ");
        double price = sc.nextDouble();

        Product newProduct = new Product(name, price, explanation);
        newProduct.setNew(true); // 새로운 상품으로 설정
        productList[productCounter++] = newProduct;

        System.out.println("새로운 상품이 생성되었습니다:");
        printMenu(); // 새로 추가된 상품 포함하여 메뉴 출력
    }

    //4. 상품삭제 코드 작성

    public void mainShow() {
        Scanner sc = new Scanner(System.in);
        Kiosk_Management km = new Kiosk_Management();
        System.out.println("[ MANAGEMENT MENU ]");
        System.out.println("1. 대기주문 목록");
        System.out.println("2. 완료주문 목록");
        System.out.println("3. 상품 생성");
        System.out.println("4. 상품 삭제");
    }

    public static void main(String[] args) {
        Kiosk_Management km = new Kiosk_Management();
        km.mainShow();
    }
}
