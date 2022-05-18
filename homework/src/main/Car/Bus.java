public class Bus {
    int maxPassenger = 10;
    static int passenger;
    int intake = 0;
    int busNum;
    int oil = 0;
    int maxoil = 100;
    int speed = 0;

    public Bus(int num) {
        this.busNum = num;
    }

    public void take(int passenger) {
        this.passenger += passenger;
        this.intake += 1000*passenger;
    }

    public int getBusNum() {
        return busNum;
    }

    public int getPassenger() {
        return passenger;
    }

    public int getMaxPassenger() {
        return maxPassenger;
    }

    public int getOil() {
        return oil;
    }

    public int getMaxOil() {
        return maxoil;
    }

    public int getSpeed() {
        return speed;
    }

    public static void clearScreen() {
        for (int i = 0; i < 30; i++)
            System.out.println("");
    }

    public static void allClear() {
        passenger = 0;
    }

    public void showInfo(int speed_val, int oil_val, int passenger_val, int money) {
        speed = speed_val;
        oil = oil_val;
        passenger = passenger_val;
        intake += money;
        System.out.println("["+this.getBusNum()+"번] 현재속도: "+speed_val+"km/h 연료: "+oil_val+"/"+this.getMaxOil()+" 탑승인원: "+passenger_val+"/"+this.getMaxPassenger()+" 누적요금: "+intake+"원");
    }
}

