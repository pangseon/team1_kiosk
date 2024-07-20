package kiosk;
import java.util.Scanner;

public class Kiosk {
    public static void main(String[] args) {
        Menu[] menus = new Menu[3];
        menus[0] = new Menu("Macaroon", "바삭한 꼬끄와 달콤한 크림이 조화로운 마카롱");
        menus[1] = new Menu("Financier", "버터 풍미 가득한 휘낭시에");
        menus[2] = new Menu("Coffee", "매장에서 직접 만드는 커피");

        kiosk.Product[] products1 = new Product[5];
        Product[] products2 = new Product[5];
        Product[] products3 = new Product[3];

        products1[0] = new Product("Nutella Macaroon", 3.5, "누텔라(초코잼) 크림이 들어간 달달한 마카롱");
        products1[1] = new Product("Cheese Macaroon", 3.5, "찐한 치즈크림이 들어간 마카롱");
        products1[2] = new Product("Strawberry Macaroon", 3.5, "딸기퓨레를 넣은 찐 딸기맛 마카롱");
        products1[3] = new Product("Oreo Macaroon", 3.5, "오레오 분태를 가득 넣은 마카롱");
        products1[4] = new Product("Yogurt Macaroon", 3.5, "상큼달달한 요거트 크림이 들어간 마카롱");

        products2[0] = new Product("Plain Financier", 2.5, "겉은 바삭하고 속은 쫀득한 기본 휘낭시에");
        products2[1] = new Product("Choco Financier", 2.5, "많이 달지 않고 고급진 초코맛 휘낭시에");
        products2[2] = new Product("Soboro Financier", 2.5, "직접 만든 소보로 가루 가득 얹은 휘낭시에");
        products2[3] = new Product("Oreo Financier", 2.7, "반죽에 오레오 분태를 가득 넣고 화이트초코로 윗면을 얇게 코팅한 휘낭시에");
        products2[4] = new Product("Pecan Financier", 2.5, "기본 휘낭시에 반죽에 카라멜라이징한 피칸을 가득 올려 구워낸 휘낭시에");

        products3[0] = new Product("Americano", 3.5, "어떤 디저트와도 잘 어울리는 아메리카노");
        products3[1] = new Product("Cafe Latte", 4.0, "우유와 에스프레소가 만나 고소한 라떼");
        products3[2] = new Product("Vanilla Latte", 4.5, "매장에서 직접 만든 천연 바닐라빈 시럽을 사용한 바닐라라떼");

        Order order = new Order();
        Kiosk_Management km = new Kiosk_Management();

        Kiosk kiosk = new Kiosk();

        Scanner scan = new Scanner(System.in);

        while (true) {
            menuShow(menus, km);
            //kiosk.menuShow(menus);
            Product[] product;
            int menuAnswer = scan.nextInt();
            if (menuAnswer == (menus.length +1)) {
                order.orderQuestion();
                int orderAnswer = scan.nextInt();
                if (orderAnswer == 1) {
                    order.ordering(km);
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } else if (menuAnswer == (menus.length + 2)) {
                order.cancelQuestion();
                int cancelAnswer = scan.nextInt();
                if (cancelAnswer == 1) {
                    order.cancel();
                }
            } else if(menuAnswer == (menus.length + 3)){
                //키오스크 관리 프로그램 메인 메뉴
                km.mainShow();
                int i = scan.nextInt();
                if(i == 1){
                    // 대기 주문 목록
                    km.printOrderQueue();
                }else if(i == 2){
                    // 완료 주문 목록
                    km.printCompletedOrders();
                }else if(i == 3){
                    // 상품생성
                    km.createProduct();
                }else if(i == 4){
                    // 상품 삭제
                }
            }else if (menuAnswer == 0) {
                order.secretShow();
                int backAnswer = scan.nextInt();
            } else {
                System.out.println("FALL BAKERY에 오신 걸 환영합니다.");
                System.out.println("아래 상품메뉴판을 보시고 상품을 골라 입력해주세요.");
                System.out.println();
                if (menuAnswer == 1) {
                    product = products1;
                } else if (menuAnswer == 2) {
                    product = products2;
                } else {
                    product = products3;
                }
                System.out.println("[ " + menus[menuAnswer-1].name + " MENU ]");
                kiosk.productShow(product);
                int productAnswer = scan.nextInt();
                product[productAnswer-1].show();

                System.out.println("\"" + product[productAnswer-1].show() + "\"");
                order.addQuestion();

                int addAnswer = scan.nextInt();
                if (addAnswer == 1) {
                    order.addToCart(product[productAnswer-1]);
                }
            }
        }
    }
        public static void menuShow(Menu menus[], Kiosk_Management km){
            System.out.println("\"FALL BAKERY에 오신 걸 환영합니다.");
            System.out.println("아래 메뉴판을 보시고 메뉴를 골라 입력해주세요.");
            System.out.println();
            System.out.println("[ FALL BAKERY MENU ]");
            for (int i = 0; i < menus.length; i++) {
                System.out.print((i + 1) + ". ");
                System.out.println(menus[i].show());
            }
            System.out.println();
            km.printMenu(); //3. 상품 생성


            System.out.println("[ ORDER MENU ]");
            int i = menus.length;
            System.out.println((i + 1) + ". Order  | 장바구니를 확인 후 주문합니다.");
            System.out.println((i + 2) + ". Cancel | 진행중인 주문을 취소합니다.");

            //키오스크 관리 프로그램을 어따 넣는건지 모르겠어서 일단 여기다 넣음
            System.out.println();
            System.out.println("[ KIOSK MANAGEMENT MENU ]");
            System.out.println((i + 3) + ". Management | 키오스크 관리 프로그램으로 이동합니다.");
        }

        public static void productShow(Product products[]){
            for (int i = 0; i < products.length; i++) {
                System.out.println((i + 1) + ". " + products[i].show());
            }
        }
    }

