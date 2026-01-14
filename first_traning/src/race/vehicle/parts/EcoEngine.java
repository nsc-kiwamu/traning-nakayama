package race.vehicle.parts;

public class EcoEngine implements Engine {

    @Override
    public int getPower(int oil) {
        // 燃料1あたりの出力は低いが、安定している
        return oil * 2;
    }
}
