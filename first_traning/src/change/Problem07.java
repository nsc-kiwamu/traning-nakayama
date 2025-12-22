package change;

import hierarchy.sushi.Itamae;
import hierarchy.sushi.Tray;

// 回転寿司を注文する問題
public class Problem07 {

    /**
     * 回転寿司を注文する
     * @param args
     */
    public static void main(String[] args) {

        Itamae itamae = new Itamae();
        itamae.order("たまご");
        itamae.order("まぐろ");
        itamae.order("まぐろ");
        itamae.order("中トロ");
        itamae.order("うに");
        itamae.order("大トロ");
        itamae.order("まぐろ");

        System.out.println("注文票-------------");
        itamae.payment();
        System.out.println("-------------------");

        /* -- ここから問題 -- */

        // 黄皿である「サーモン」を3皿、緑皿である「甘エビ」を1皿を追加注文せよ
        // Trayクラスを継承した新たな黄皿、及び緑皿を作れ
        // なお、黄皿は1皿210円、緑皿は1皿420円とする。

        // 黄皿である「サーモン」を3皿追加注文
        itamae.order("サーモン");
        itamae.order("サーモン");
        itamae.order("サーモン");

        // 緑皿である「甘エビ」を1皿追加注文
        itamae.order("甘エビ");
        
// Trayクラスを継承した新たな黄皿、及び緑皿を作れ
// なお、黄皿は1皿210円、緑皿は1皿420円とする。
//黄皿（1皿210円）
class YellowTray extends Tray {
	public YellowTray(String name) {
     this.name = name;
     this.price = 210;
 }
}

//緑皿（1皿420円）
class GreenTray extends Tray {
 public GreenTray(String name) {
     this.name = name;
     this.price = 420;
 }
}
    }
