//  버스

import java.util.Random;

public class Start_Bus {
    public static void main(String[] args) throws InterruptedException {
        Random random = new Random(); // 랜덤 함수 선언
        int passenger_people = 0;
        int passenger_random;
        int oil_val = 30;
        int money;
        boolean status = false;
        Bus bus1 = new Bus(random.nextInt(1000)); // 버스

        Bus.clearScreen();
        System.out.println("[운행중] 버스가 출발하였습니다.");

            while (true) {
                if ((oil_val > 10) && status == false) {
                    passenger_random = 1 + random.nextInt(5); // 탑승객

                    if (bus1.getPassenger() + passenger_random <= bus1.getMaxPassenger()) {
                        System.out.println("[운행중] " + passenger_random + "명이 버스의 탑승하였습니다.");
                        passenger_people += passenger_random;
                        money = passenger_random * 1000;
                        oil_val -= 5;
                    } else {
                        oil_val -= 1;
                        money = 0;
                        System.out.println("[운행중] 버스가 꽉찼음으로 " + passenger_random + "명의 인원을 탑승시키지 못하였습니다.");
                    }

                    bus1.showInfo(10 + random.nextInt(50), oil_val, passenger_people, money);
                    Thread.sleep(2000);
                    Bus.clearScreen();
                } else if((oil_val <= 10) && status == false) {
                    System.out.println("[복귀중] 버스의 연료가 부족하여 차고지로 복귀합니다.");
                    Thread.sleep(1000);
                    for(int i=5; i > 0 ; i--) {
                        Bus.clearScreen();
                        System.out.println("[복귀중] 차고지까지 남은시간: " + i + "초");
                        Thread.sleep(1000);
                    } status = true;
                } else if((oil_val <= 10) && status == true) {
                    Bus.clearScreen();
                    System.out.println("[차고지] 버스의 연료를 충전합니다.");
                    Thread.sleep(2000);

                    for(int i=29; i >= oil_val ; oil_val++) {
                        Bus.clearScreen();
                        System.out.println("[차고지] 충전중: " + oil_val + "/30");
                        Thread.sleep(500);
                    }
                    status = false;
                    Bus.allClear();
                    passenger_people = 0;
                    Bus.clearScreen();
                    System.out.println("[운행중] 연료를 전부 충전하여 버스가 출발하였습니다.");
                    Thread.sleep(3000);
                    continue;
                }
            }
    }
}

