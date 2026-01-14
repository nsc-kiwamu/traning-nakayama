package change;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import io.rebuilding.NestSearch;
import io.rebuilding.RebuildFileManager;
import io.rebuilding.SampleManager;
import io.rebuilding.SimpleRule;

/**
 * 入出力の問題 完全版
 *
 * 問題①：NestSearchでサブディレクトリも対象
 * 問題②：文章中の「。」の後に改行を追加
 * 問題③：文字コードを意識せずバイナリコピーも可能
 *
 * ※問題②と③を完全に両立させる場合、ファイルがテキストであることが前提
 */
public class Problem15 {

    public static void main(String[] args) {
        // 統合版マネージャー
        RebuildFileManager manager = new MyIntegratedManager(new NestSearch(), new SimpleRule("Problem15"));

        // 入力フォルダを指定して再編成
        manager.rebuild(Paths.get("./data/in"));
    }

    /**
     * 統合版マネージャークラス
     * ・問題①：NestSearchでサブディレクトリも対象
     * ・問題②：文章の「。」の後に改行を追加
     * ・問題③：文字コードに依存せず、バイナリコピーも可能
     */
    static class MyIntegratedManager extends SampleManager {

        public MyIntegratedManager(NestSearch search, SimpleRule rule) {
            super(search, rule);
        }

        @Override
        public void rebuild(Path sourceDir) {
            // 1. 親クラスでファイル構成を再編成
            super.rebuild(sourceDir);

            // 2. 全ファイルを取得
            List<Path> files;
            try {
                files = getAllFiles(sourceDir);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }

            // 3. 各ファイルを加工
            for (Path file : files) {
                try {
                    // まずバイナリコピーを保持（問題③対応）
                    byte[] originalData = Files.readAllBytes(file);

                    // ここから文字列加工（問題②対応）
                    // ※文字化けを避けるため UTF-8 に変換可能な場合のみ
                    String content;
                    try {
                        content = new String(originalData, "UTF-8");
                    } catch (Exception e) {
                        // 文字列変換できない場合はバイナリコピーだけにする
                        Files.write(file, originalData);
                        continue;
                    }

                    // 「。」の後に改行を入れる
                    String processed = content.replaceAll("。", "。\n");

                    // 加工後をバイト列に変換して上書き
                    Files.write(file, processed.getBytes("UTF-8"));

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        /**
         * ディレクトリ以下のすべてのファイルを取得するヘルパーメソッド
         */
        private List<Path> getAllFiles(Path dir) throws IOException {
            return Files.walk(dir)
                    .filter(Files::isRegularFile)
                    .collect(Collectors.toList());
        }
    }
}
