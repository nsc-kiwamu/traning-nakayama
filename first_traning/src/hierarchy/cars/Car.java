package hierarchy.cars;

/**
 * 車を表すインターフェース
 * run() と carRun() を実装することを定義
 */
public interface Car {

    /**
     * 共通の走行メソッド
     * @param carModel 車種名
     */
    void run(String carModel);

    /**
     * 車固有の走行距離を返す
     * @return 走行距離
     */
    int carRun();
}
