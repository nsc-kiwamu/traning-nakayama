package hierarchy.life;

public class Tree extends Plant {

    public Tree(int lifespan) {
        super(lifespan);
    }

    @Override
    void toAct() {
        activity(); // 活動時に光合成
    }

    public void activity() {
        photosynthesis();
    }

    @Override
    public void photosynthesis() {
        System.out.println("木は光合成をしている");
    }
}
