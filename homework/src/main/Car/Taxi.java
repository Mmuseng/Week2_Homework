import java.util.Random;

class TaxiList {
    String num; // 차량번호
    public int maxoil = 150; // 최대주유량
    int defaultRange = 300; // 기본거리
    int defaultCost = 1000; // 기본요금
    int rideCost = 100; // 거리당 요금

    int oil; // 주유량
    int speed; // 현재속도
    String Checkpoint; // 목적지
    int currentCost; // 현재요금
    int checkpointRange; // 목적지까지 거리
    int currentDistance;
    String currentState = "stop"; // 상태
    int delay; // 대기시간

    public TaxiList(String n) {
        num = n;
        oil = maxoil;
        speed = 0;
        Checkpoint = "차고지";
        defaultRange = defaultRange;
        defaultCost = defaultCost;
        checkpointRange = 0;
        rideCost = rideCost;
        currentState = currentState;
        currentDistance = 0;
    }

    public void Reset() {
        speed = 0;
        checkpointRange = 0;
        currentState = "stop";
        currentDistance = 0;
        currentCost = 0;
    }

    public void oil(int i) {
        oil = i;
    }

    public int getOil() {
        return oil;
    }

    public int getMaxOil() {
        return maxoil;
    }

    public int getDelay() {
        return delay;
    }

    public void speed(int i) {
        speed = i;
    }

    public void currentDistance(int i) {
        currentDistance = i;
    }
    public int GetcurrentDistance() {
        return currentDistance;
    }

    public void Checkpoint(String i) {
        Checkpoint = i;
    }

    public void currentCost(int i) {
        currentCost = i;
    }
    public int GetdefaultCost() {
        return defaultCost;
    }

    public int GetrideCost() {
        return rideCost;
    }

    public void checkpointRange(int i) {
        checkpointRange = i;
    }

    public int GetdefaultRange() {
        return defaultRange;
    }

    public int GetcheckpointRange() {
        return checkpointRange;
    }

    public void currentState(String i) {
        currentState = i;
    }

    public String GetcurrentState() {
        return currentState;
    }

    public String GetcurrentStateVal(String cS) {
        String currentStateVal = null;
        if(cS == "stop") {
            currentStateVal = "정차";
        } else if(cS == "ride_getoff") {
            currentStateVal = "빈차";
        } else if(cS == "ride_geton") {
            currentStateVal = "운행";
        } else if(cS == "oil_refill") {
            currentStateVal = "주유";
        } else if(cS == "end_point") {
            currentStateVal = "도착 (" + this.getDelay() + "s)";
        }
        return currentStateVal;
    }

    public void showTaxiInfo() {
        System.out.println("[" + this.GetcurrentStateVal(this.GetcurrentState()) + "] [" + num + "] [연료: " + oil + "L] [속도: " + speed + "km] [목적지: " + Checkpoint + "] [요금: " + currentCost + "원] [주행거리 : "+ currentDistance + "m] [남은거리: "+ checkpointRange + "m]");
    }
}

public class Taxi {
    Random rd = new Random(); // 랜덤 함수 선언

    public static void lines() {
        System.out.println("────────────────────────────────────────────────────────────────────────────────────");
    }

    public Taxi(int create_num) throws InterruptedException {
        TaxiList[] taxis = new TaxiList[create_num];
        String[][] Location = {{"경찰서", "450"},{"병원", "600"},{"소방서", "300"},{"시청", "800"},{"바다", "1000"}}; // TODO: 도착지 이름, 거리
        String[] middleNum = { "아","바","사","자" };

        // TODO: 택시 정보 기입
        for(int i = 0; i < create_num; i++) {
            String num = (String.format("%02d",(1 + rd.nextInt(99))) + middleNum[rd.nextInt(3)] + (1000 + rd.nextInt(8999)));
            taxis[i] = new TaxiList(num);
        }

        // TODO: 반복 실행
        while (true) {
            Bus.clearScreen();
            // TODO: 콘솔 출력
            this.lines();
            for(int i = 0; i < create_num; i++) {
                taxis[i].showTaxiInfo();
            }
            this.lines();

            for(int i = 0; i < create_num; i++) {
                if(rd.nextInt(10) == 1 && taxis[i].GetcurrentState() == "stop") { // 정차에서 빈차 확률
                    taxis[i].currentState("ride_getoff");
                }

                if(rd.nextInt(30) == 1 && taxis[i].GetcurrentState() == "ride_getoff") { // 빈차에서 탑승 확률
                    taxis[i].currentState("ride_geton");
                }

                // TODO: 운행
                if(taxis[i].GetcheckpointRange() > 0 && taxis[i].GetcurrentState() == "ride_geton" && taxis[i].getOil() > 0) {
                    int currentspeed = (10 + rd.nextInt(50));

                    if ((taxis[i].GetcheckpointRange() - currentspeed) > 0 && taxis[i].GetcurrentState() == "ride_geton") {
                        taxis[i].speed = currentspeed;
                        taxis[i].checkpointRange = taxis[i].GetcheckpointRange() - currentspeed;
                        taxis[i].currentDistance += currentspeed;

                        // TODO: 요금 추가
                        if (taxis[i].GetcurrentDistance() >= taxis[i].GetdefaultRange()) {
                            taxis[i].currentCost += taxis[i].GetrideCost(); // 거리 당 요금
                        } else {
                            taxis[i].currentCost = taxis[i].GetdefaultCost(); // 기본 요금
                        }
                        
                        // TODO: 연료 감소
                        if (currentspeed >= 30) { // 속도가 30 이하 일 경우
                            taxis[i].oil -= 3;
                        } else { // 속도가 30 이상 일 경우
                            taxis[i].oil -= 5;
                        }
                    } else { // TODO: 목적지 도착
                        taxis[i].currentState("end_point");
                        taxis[i].checkpointRange = 0;
                        taxis[i].speed = 0;
                        taxis[i].delay = 10;
                    }
                    // TODO: 목적지 도착 후 대기
                } else if((taxis[i].GetcurrentState() == "end_point") && taxis[i].delay > 0) {
                    taxis[i].delay--;
                } else if((taxis[i].GetcurrentState() == "end_point") && taxis[i].delay == 0) {
                    taxis[i].Reset(); // 택시 정보 초기화 (연료/목적지는 유지)
                }
                
                // TODO: 주유
                if(taxis[i].GetcheckpointRange() > 0 && taxis[i].GetcurrentState() == "ride_geton" && taxis[i].getOil() <= 0) {
                    taxis[i].currentState("oil_refill");
                    taxis[i].oil = 0;
                } else if(taxis[i].GetcheckpointRange() > 0 && taxis[i].GetcurrentState() == "oil_refill" && taxis[i].getOil() < taxis[i].getMaxOil()) {
                    taxis[i].oil += 10; // 연료 충전
                } else if(taxis[i].GetcheckpointRange() > 0 && taxis[i].GetcurrentState() == "oil_refill" && taxis[i].getOil() == taxis[i].getMaxOil()) {
                    taxis[i].currentState("ride_geton");
                }

                // TODO: 목적지 선택
                if(taxis[i].GetcurrentDistance() == 0 && taxis[i].GetcurrentState() == "ride_geton") {
                    int Lnum = rd.nextInt(Location.length);
                    String LocationName = Location[Lnum][0];
                    String LocationRange = Location[Lnum][1];
                    taxis[i].Checkpoint(LocationName);
                    taxis[i].checkpointRange(Integer.parseInt(LocationRange));
                }
            }

            Thread.sleep(1000); // 1000ms = 1초
        }
    }
}