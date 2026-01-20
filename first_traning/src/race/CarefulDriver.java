package race;

public class CarefulDriver implements Driver {

    @Override
    public DriveType drive() {
        // 無理をしない運転
        return DriveType.moveOn;
    }

    @Override
    public double getDrivingSkills() {
        // 安全重視で控えめ
        return 0.8;
    }
}
