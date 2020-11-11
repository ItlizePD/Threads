import java.util.Arrays;
import java.util.Objects;


public class ThreadAssignment3 {
    public static void main(String[] args) {
        Park park = new Park(3);
        for (int i = 0; i < 20; i++) {
            new Thread(new ParkCar(park, new Car("car:" + (int)(Math.random() *10)))).start();
        }
    }
}

class ParkCar implements Runnable{
    private final Park park;
    private Car car;
    ParkCar(Park park, Car car) {
        this.park = park;
        this.car = car;
    }
    public void run () {
        synchronized (park) {
            int pos = park.isAvailable();
            if (pos < 0) {
                if (park.isIn(car) > -1) {
                    park.setPosStatu(park.isIn(car), car);
                    car.setStopPos(null);
                    System.out.println(car.toString() + " drive away " + park.toString());
                } else {
                    System.out.println("full!");
                }
            } else {
                if (park.isIn(car) > -1) {
                    park.setPosStatu(park.isIn(car), car);
                    car.setStopPos(null);
                    System.out.println(car.toString() + " drive away " + park.toString());
                } else {
                    park.setPosStatu(pos, car);
                    car.setStopPos(pos);
                    System.out.println(car.toString() + " park success " + park.toString());
                }
            }
        }
    }
}
class Park {
    private Car[] status;
    public Park(int capacity) {
        this.status = new Car[capacity];
        Arrays.fill(status, null);
    }
    void setPosStatu(int index, Car car) {
        this.status[index] = this.status[index] != null && this.status[index].equals(car) ? null : car;
    }

    int isAvailable() {
        for (int i = 0; i < this.status.length; i++) {
            if (this.status[i] == null) {
                return i;
            }
        }
        return -1;
    }
    int isIn(Car car) {
        for (int i = 0; i < this.status.length; i++)
            if (car.equals(this.status[i])) {
                return i;
            }
        return -1;
    }
    @Override
    public String toString() {
        return "status:" + Arrays.toString(status);
    }
}
class Car {
    private String carId;
    private Integer stopPos;
    Car(String carId) {
        this.carId = carId;
    }
    void setStopPos(Integer stopPos) {
        this.stopPos = stopPos;
    }
    @Override
    public String toString() {
        return  carId;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(carId, car.carId);
    }
    @Override
    public int hashCode() {
        return Objects.hash(carId);
    }
}
