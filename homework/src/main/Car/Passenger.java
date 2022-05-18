public class Passenger {
    int money;

    public Passenger(int money) {
        this.money = money;
    }

    public void takeBus(Bus bus, int passenger) {
//        this.money -= 1000;
        bus.take(passenger);
    }

    public int getMoney() {
        return money;
    }

    public void showInfo() {
        System.out.println(this.getMoney());
    }
}