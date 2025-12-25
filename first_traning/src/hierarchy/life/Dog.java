package hierarchy.life;

public class Dog extends Animal {

    public Dog(int lifespan) {
        super(lifespan);
    }

    @Override
    void eat() {
        System.out.println(this.getClass().getSimpleName() + "が肉を食べた");
    }

    @Override
    void toAct() {
        System.out.println(this.getClass().getSimpleName() + "は歩き回っている");
    }
}
