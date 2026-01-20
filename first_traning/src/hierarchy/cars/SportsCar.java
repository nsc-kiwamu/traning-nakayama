package hierarchy.cars;

/**
 * スポーツカーを表すクラス
 * Carインターフェースを実装
 * 1回の走行で120km進む
 */
public class SportsCar implements Car {

    private static final int speed = 120;

    @Override
    public void run(String carModel) {
        System.out.println(carModel + "で走ります");
    }

    @Override
    public int carRun() {
        System.out.println("スポーツカーで" + speed + "km進みました");
        return speed;
    }
}
