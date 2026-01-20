package change;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import race.CarefulDriver;
import race.Driver;
import race.ExtremeDriver;
import race.NormalDriver;
import race.vehicle.Boat;
import race.vehicle.FastBoat;
import race.vehicle.NormalBoat;
import race.vehicle.Vehicle;
import race.vehicle.parts.EcoEngine;
import race.vehicle.parts.EcoPropeller;
import race.vehicle.parts.Engine;
import race.vehicle.parts.NormalEngine;
import race.vehicle.parts.NormalPropeller;
import race.vehicle.parts.PowerEngine;
import race.vehicle.parts.PowerPropeller;
import race.vehicle.parts.Propeller;

/**
 * 継承、実装の問題
 *
 */
public class Problem12 {

    /**
     * 様々な部品を組み合わせ、ボートレースをする問題
     * @param args
     */
    public static void main(String args[]) {

        // 1台目のボートを作る
        Engine engine01 = new NormalEngine();
        Propeller propeller01 = new NormalPropeller();
        Boat boat01 = new NormalBoat(engine01, propeller01, "01");

        Driver driver01 = new NormalDriver();
        boat01.ride(driver01);
        boat01.setFuel(100);

        // 2台目のボートを作る
        Engine engine02 = new NormalEngine();
        Propeller propeller02 = new PowerPropeller();
        Boat boat02 = new FastBoat(engine02, propeller02, "02");

        Driver driver02 = new NormalDriver();
        boat02.ride(driver02);
        boat02.setFuel(100);

        // 3台目のボートを作る
        Engine engine03 = new PowerEngine();
        Propeller propeller03 = new NormalPropeller();
        Boat boat03 = new NormalBoat(engine03, propeller03, "03");

        Driver driver03 = new ExtremeDriver();
        boat03.ride(driver03);
        boat03.setFuel(100);
        
     // 4台目のボートを作る
      //2025/1/7 NAKAYAMA add_st
        Engine engine04 = new EcoEngine();
        Propeller propeller04 = new EcoPropeller();
        Boat boat04 = new NormalBoat(engine04, propeller04, "04");

        Driver driver04 = new CarefulDriver();
        boat04.ride(driver04);
        boat04.setFuel(100);

        List<Vehicle> boatList = Arrays.asList(boat01, boat02, boat03, boat04);
      //2025/1/7 NAKAYAMA add_end

        // レースの走行距離
        int mileage = 50;

        rase(boatList, mileage);
        //graphicalRace(boatList, mileage);

        /* -- ここから問題 -- */
        /*
         * エンジン、プロペラ、ボート、ドライバーを追加しレースをせよ
         */

        /*
         * レースの走行距離が長い場合、燃料が切れてレースが終わらない。
         * 参加しているボートの燃料が全て切れた場合、レースを中断する
         * ように修正せよ
         */

        /*
         * 出力結果のが以下となるようなgraphicalRaceメソッドを作成せよ
         * 実行する際はraceメソッドはコメントアウトし実行すること
         *
         * ・現状の出力イメージ
         * 01が3進みました
         * 02が0進みました
         * 03が13進みました
         * 01がトータルで3進みました
         * 02がトータルで0進みました
         * 03がトータルで13進みました
         * ～繰り返し～
         *
         * ・新しい出力イメージ
         * ==================================================|ゴール
         * >>>01
         * 02
         * >>>>>>>>>>>>>03
         * ～繰り返し～
         */

        /*
         * コンストラクタに以下を持つ抽象クラスcarを作成し、
         * レースを実施せよ
         * コンストラクタ
         * ・エンジン(既存インターフェースを使用)
         * ・タイヤ(新規インターフェースを作成)
         * ・車体番号
         */

    }

    /**
     * レースを実施する
     * @param list 出場車リスト
     * @param distance 距離
     */
    public static void rase(List<Vehicle> list, int distance) {

        // 出場車のリストを表示
        list.forEach(Vehicle::outputInfo);

        Map<String, Integer> distanceMap = list.stream()
                .collect(Collectors.toMap(
                        Vehicle::getBoatName,
                        s -> 0));

        boolean isRace = true;

        do {
            // ★ 追加：誰かが進んだか判定
            boolean someoneMoved = false;

            for (Vehicle boat : list) {
                int addDistance = boat.drive();
                System.out.println(boat.getBoatName() + "が" + addDistance + "進みました");

                if (addDistance > 0) {
                    someoneMoved = true;
                }

                distanceMap.put(
                        boat.getBoatName(),
                        distanceMap.get(boat.getBoatName()) + addDistance
                );
            }

            for (String key : distanceMap.keySet()) {
                int curDistance = distanceMap.get(key);
                System.out.println(key + "がトータルで" + curDistance + "進みました");

                if (curDistance >= distance) {
                    isRace = false;
                }
            }

            // ★ 追加：全艇燃料切れ
            if (!someoneMoved) {
                System.out.println("全てのボートの燃料が切れました。レースを中断します。");
                break;
            }

        } while (isRace);

        judge(distanceMap);
    }


    /**
     * 走行距離から着順を決める
     * @param result レース結果
     */
    private static void judge(Map<String, Integer> result) {

        List<String> rankList = new ArrayList<>();

        // 引数のマップを走行距離で降順ソートし、キーをリストに詰める
        rankList = result.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue())) //MAPのバリュー(走行距離)で降順ソート
                .map(s -> s.getKey())
                .collect(Collectors.toList());


        int rank = 0;
        int prevDistance = 0;

        System.out.println("=========== 結果発表 ===========");

        // 順位判定をおこなう
        for (int i = 0; i < rankList.size(); i++) {
            int curDistance = result.get(rankList.get(i));

            // 前の走行距離と同じ場合は順位は変えない
            if (curDistance != prevDistance) {
                rank ++;
            }
            System.out.println(rank + "位" + rankList.get(i));

            prevDistance = curDistance;

        }

    }

    /**
     * レース状況を視覚的に表示しながら実施する。
     * @param list 出場車リスト
     * @param distance 距離
     */
  //2025/1/7 NAKAYAMA add_st
    public static void graphicalRace(List<Vehicle> list, int distance) {

        Map<String, Integer> distanceMap = list.stream()
                .collect(Collectors.toMap(
                        Vehicle::getBoatName,
                        s -> 0));

        boolean isRace = true;

        while (isRace) {
            boolean someoneMoved = false;

            for (Vehicle boat : list) {
                int add = boat.drive();
                if (add > 0) {
                    someoneMoved = true;
                }

                distanceMap.put(
                        boat.getBoatName(),
                        distanceMap.get(boat.getBoatName()) + add
                );
            }

            // ゴールライン表示
            System.out.println("==================================================|ゴール");

            for (Vehicle boat : list) {
                int cur = distanceMap.get(boat.getBoatName());

                // 「>」の数で進捗を表現
                String bar = "";
                for (int i = 0; i < cur / 3; i++) {
                    bar += ">>>";
                }
                System.out.println(bar + boat.getBoatName());

                if (cur >= distance) {
                    isRace = false;
                }
            }

            System.out.println();

            // 全艇燃料切れ
            if (!someoneMoved) {
                System.out.println("全てのボートの燃料が切れました。レースを中断します。");
                break;
            }

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        judge(distanceMap);
      //2025/1/7 NAKAYAMA add_end
    }
}


