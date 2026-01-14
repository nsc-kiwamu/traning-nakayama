package hierarchy.life;

public abstract class Plant extends Creature {

    public Plant(int lifespan) {
        super(lifespan);
    }

    public abstract void photosynthesis();
}
