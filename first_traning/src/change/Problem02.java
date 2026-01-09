package change;

import java.util.ArrayList;
import java.util.List;

import util.DateUtil;
import util.NumericUtil;

/**
 * ランダムで生成した数値を並び変える問題
 */
public class Problem02 {

    /**
     * ランダムで生成した数値を振り分ける問題
     */
    public static void main(String[] args) {

        /*
         * ランダムに生成した数値のリストについて
         * バブルソートを使用して並び替えを実施したい。
         * 途中まで実装してあるため、残りを実装せよ
         */

        // テスト用のランダムデータを作成
        List<Integer> testList = NumericUtil.makeRandomList(10, 10);

        System.out.println("元のリスト：" + testList);
        System.out.println("------------------------------------------");

        /* -- ここから問題 -- */
        // バブルソートを使用して昇順ソート(テストデータは使いまわすため、コピーして使用)
        System.out.println("昇順ソート開始");
        bubbleSortAsc(new ArrayList<Integer>(testList));

        // バブルソートを使用して降順ソート(テストデータは使いまわすため、コピーして使用)
        System.out.println("降順ソート開始");
        bubbleSortDesc(new ArrayList<Integer>(testList));

        // 他にどのようなソートアルゴリズムがあるか調べ、作成せよ

    }

    /**
     * 引数で受け取ったリストをバブルソートで昇順に並び替えたものを出力する。
     * @param testList 並び替え対象リスト
     * @return 並び替えたリスト
     */
    public static void bubbleSortAsc(List<Integer> testList) {

        // 処理時間測定用に開始時間を取得
        long startTime = System.currentTimeMillis();

        // 値を入れ替えるための一時変数
        int tmpData;

        for (int i = 0; i < testList.size(); i++) {
            for (int j = i + 1; j < testList.size(); j++) {

                /*-- 問題 ここのif文内の「true」を変更して実装せよ--*/
                // 左の要素が右の要素より大きい場合に入れ替え
                if (testList.get(i) > testList.get(j)) {
                    tmpData = testList.get(i);
                    testList.set(i, testList.get(j));
                    testList.set(j, tmpData);
                }

                // ソート中の状態を出力（インデックスに*を付与）
                checkSort(testList, i, j);
            }
        }

        // 処理時間測定用に終了時間を取得
        long endTime = System.currentTimeMillis();

        // ソート結果と処理時間を出力
        System.out.println("昇順ソート結果：" + testList);
        System.out.println("処理時間:" + DateUtil.getProcTime(startTime, endTime));
        System.out.println("------------------------------------------");
    }

    /**
     * バブルソートで降順ソート
     */
    public static void bubbleSortDesc(List<Integer> testList) {

        long startTime = System.currentTimeMillis();
        int tmpData;

        for (int i = 0; i < testList.size(); i++) {
            for (int j = i + 1; j < testList.size(); j++) {

                // 左の要素が右の要素より小さい場合に入れ替え
                if (testList.get(i) < testList.get(j)) {
                    tmpData = testList.get(i);
                    testList.set(i, testList.get(j));
                    testList.set(j, tmpData);
                }

                // ソート中の状態を出力（インデックスに*を付与）
                checkSort(testList, i, j);
            }
        }

        long endTime = System.currentTimeMillis();
        System.out.println("降順ソート結果：" + testList);
        System.out.println("処理時間:" + DateUtil.getProcTime(startTime, endTime));
        System.out.println("------------------------------------------");
    }

    /** 
     * ソート中のリストの状態を出力し、入れ替え判定中の要素には"*"を付与する
     * @param testList 出力対象リスト
     * @param tgtIndex1 入替判定中インデックス
     * @param tgtIndex2 入替判定中インデックス
     */
    public static void checkSort(List<Integer> testList, int tgtIndex1, int tgtIndex2) {

        for (int i = 0; i < testList.size(); i++) {

            System.out.print("[");
            if (i == tgtIndex1 || i == tgtIndex2) {
                System.out.print("*" + testList.get(i));
            } else {
                System.out.print(testList.get(i));
            }
            System.out.print("]");
        }
        System.out.println("");

        try {
            Thread.sleep(500); // 0.5秒スリープで視覚的に確認
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
