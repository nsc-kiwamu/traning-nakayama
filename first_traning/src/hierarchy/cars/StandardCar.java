package hierarchy.cars;

/**
 * 普通車を表すクラス
 * Carインターフェースを実装
 * 1回の走行で80km進む
 */
public class StandardCar implements Car {

    private static final int speed = 80;

    @Override
    public void run(String carModel) {
        System.out.println(carModel + "で走ります");
    }

    @Override
    public int carRun() {
        System.out.println("普通車で" + speed + "km進みました");
        return speed;
    }
}
