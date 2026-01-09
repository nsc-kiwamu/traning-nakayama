package race.vehicle.parts;

public class EcoPropeller implements Propeller {

    @Override
    public int getForce(int power) {
        // 出力を抑えめに推進力へ変換（燃費重視）
        return (int) (power * 0.7);
    }
}
