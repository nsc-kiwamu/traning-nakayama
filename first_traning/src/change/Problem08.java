package change;

// 2025/12/23 S.NAYAYAMA
import hierarchy.life.Dog;
import hierarchy.life.Earth;
import hierarchy.life.Mouse;
import hierarchy.life.Tiger;
import hierarchy.life.Tree;
		// 2025/12/23 E.NAYAYAMA

/**
 * 継承の問題
 *
 */
public class Problem08 {

    /**
     * 地球上に存在する生物の活動を確認する。
     * @param args
     */
    public static void main(String[] args) {
        Tiger tiger = new Tiger(15);
        Mouse mouse = new Mouse(2);

        Earth earth = Earth.getInstance();
        earth.birthCreature(tiger);
        earth.birthCreature(mouse);
        earth.timeElapsed();
        
     // 2025/12/23 S.NAYAYAMA
        Dog dog = new Dog(12);
        earth.birthCreature(dog);

        Tree tree = new Tree(100);
        earth.birthCreature(tree);
     // 2025/12/23 E.NAYAYAMA

        // 10年経過させる
        for (int i = 1; i <= 10; i++) {
            System.out.println("～～～～～" + i + "年目" + "～～～～～");
            System.out.println("====== 現在の生態系 ======");
            earth.displayCreatures();
            System.out.println("------ 活動内容 ------");
            earth.timeElapsed();
            System.out.println("");
        }

        /* -- ここから問題 -- */
        // Animalクラスを継承した新たな生物を作り、地球上に存在させよ
        // 植物を表す抽象クラス(Plant)を作り、それを継承した新たな生物を作り、地球上に存在させよ
        // 寿命が減らない生物を作成したいが可能か？可能であれば生物を作り、不可能であれば理由を説明せよ
        // 不可能　理由：Creature.timeElapsed() は final メソッドで age++ が必ず実行されるため、
        //				 子クラスで寿命の減少を防ぐことはできない

    }

}
