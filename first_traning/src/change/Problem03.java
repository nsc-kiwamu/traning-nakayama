package change;

import java.util.List;

import util.NumericUtil;

/**
 * ランダムで生成した数値で勝負をする問題
 * @param args
 */
public class Problem03 {

    /**
     * ランダムで生成した数値で勝負をする問題
     * @param args
     */
    public static void main(String[] args) {

        /*
         * ユーザAとユーザBで0～15の数値の書いた10枚
         * のカードを取得し、数値の大小で勝負をする。
         */

        // ユーザAとBでカードを取得
        List<Integer> cardListA = NumericUtil.makeRandomList(10, 15);
        List<Integer> cardListB = NumericUtil.makeRandomList(10, 15);

        // ユーザAとBで勝負する
        gameComplete(cardListA, cardListB);

        /* -- ここから問題 -- */
        /* 比較するカード数を100枚に増やし、先に3勝した方を勝ちと判断するメソッド(game3Win)
         * を実装せよ。
         * 結果には以下を出力すること
         * ・どちらが勝ったのか
         * ・3勝の内訳(何戦目で勝ったか)
         *
         * */

        /* 比較するカード数を1000枚に増やし、先に3連勝した方を勝ちと判断するメソッド(game3StraightWin)
         * を実装せよ。
         * 結果には以下を出力すること
         * ・どちらが勝ったのか
         * ・3勝の内訳(何戦目で勝ったか)
         *
         * */
        
        
        //2025/12/25 NAYAYAMA add_st
        // 100枚で先に3勝した方が勝ち
        game3Win(
            NumericUtil.makeRandomList(100, 15),
            NumericUtil.makeRandomList(100, 15)
        );

        // 1000枚で先に3連勝した方が勝ち
        game3StraightWin(
            NumericUtil.makeRandomList(1000, 15),
            NumericUtil.makeRandomList(1000, 15)
        );
        //2025/12/25 NAYAYAMA add_end
    }


    /**
     * 引数で与えられたリストの大小比較をおこなう。
     * @param targetAList ユーザAのリスト
     * @param targetBList ユーザBのリスト
     */
    private static void gameComplete(List<Integer> targetAList, List<Integer> targetBList) {

        System.out.println("--------- お互いのカードを大小比較しました。 ---------");

        System.out.println("ユーザAのリスト");
        targetAList.stream().map(s -> "[" + s + "]").forEach(System.out::print);
        System.out.println("");

        System.out.println("ユーザBのリスト");
        targetBList.stream().map(s -> "[" + s + "]").forEach(System.out::print);
        System.out.println("");

        // 要素数に差がある場合、少ない方に合わせて比較をする。
        int gameCount = targetAList.size() > targetBList.size() ? targetBList.size() : targetAList.size();

        int winCountA = 0;
        int winCountB = 0;

        for (int i = 0; i < gameCount; i++) {
            // 大小比較
            if (targetAList.get(i) < targetBList.get(i)) {
                winCountB++;
            } else if (targetBList.get(i) < targetAList.get(i)) {
                winCountA++;
            }
        }

        if (winCountA == winCountB) {
            System.out.println("引き分け");
        } else if (winCountA > winCountB) {
            System.out.println("Aの勝ち");
        } else {
            System.out.println("Bの勝ち");
        }

    }
    //2025/12/25 NAYAYAMA add_st
    private static void game3Win(List<Integer> targetAList, List<Integer> targetBList) {
    
    int winA = 0;
    int winB = 0;

    List<Integer> winRoundA = new java.util.ArrayList<>();
    List<Integer> winRoundB = new java.util.ArrayList<>();

    for (int i = 0; i < 100; i++) {

        if (targetAList.get(i) > targetBList.get(i)) {
            winA++;
            winRoundA.add(i + 1);
        } else if (targetBList.get(i) > targetAList.get(i)) {
            winB++;
            winRoundB.add(i + 1);
        }

        if (winA == 3 || winB == 3) {
            break;
        }
    }

    if (winA == 3) {
        System.out.println("Aの勝ち");
        System.out.println("勝った回数: " + winRoundA);
    } else if (winB == 3) {
        System.out.println("Bの勝ち");
        System.out.println("勝った回数: " + winRoundB);
    }
}

    
    private static void game3StraightWin(List<Integer> targetAList, List<Integer> targetBList) {

        int straightA = 0;
        int straightB = 0;

        List<Integer> winRoundA = new java.util.ArrayList<>();
        List<Integer> winRoundB = new java.util.ArrayList<>();

        for (int i = 0; i < 1000; i++) {

            if (targetAList.get(i) > targetBList.get(i)) {
                straightA++;
                straightB = 0;
                winRoundA.add(i + 1);
            } else if (targetBList.get(i) > targetAList.get(i)) {
                straightB++;
                straightA = 0;
                winRoundB.add(i + 1);
            } else {
                straightA = 0;
                straightB = 0;
            }

            if (straightA == 3 || straightB == 3) {
                break;
            }
        }

        if (straightA == 3) {
            System.out.println("Aの勝ち（3連勝）");
            System.out.println("連勝した回: " +
                    winRoundA.subList(winRoundA.size() - 3, winRoundA.size()));
        } else if (straightB == 3) {
            System.out.println("Bの勝ち（3連勝）");
            System.out.println("連勝した回: " +
                    winRoundB.subList(winRoundB.size() - 3, winRoundB.size()));
         //2025/12/25 NAYAYAMA add_end
        }
    }

}
