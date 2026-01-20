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
 * 入出力の問題 完全版（①②③対応）
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
     */
    static class MyIntegratedManager extends SampleManager {

        public MyIntegratedManager(NestSearch search, SimpleRule rule) {
            super(search, rule);
        }

        @Override
        public void rebuild(Path sourceDir) {
            // 1. 親クラスでファイル構成を再編成
            super.rebuild(sourceDir);

            // 2. 全ファイルを取得（再編成後）
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
                    byte[] originalData = Files.readAllBytes(file);

                    // 文字列変換可能な場合のみ「。」後改行
                    String content = null;
                    try {
                        content = new String(originalData, "UTF-8");
                    } catch (Exception e) {
                        // UTF-8 に変換できない場合はバイナリコピーのみ
                        Files.write(file, originalData);
                        continue;
                    }

                    // 「。」の後に改行（すでに改行がある場合は重複回避）
                    String processed = content.replaceAll("。(?!\\n)", "。\n");

                    // バイト列に変換して書き込み
                    Files.write(file, processed.getBytes("UTF-8"));

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        /**
         * ディレクトリ以下のすべてのファイルを取得する
         */
        private List<Path> getAllFiles(Path dir) throws IOException {
            return Files.walk(dir)
                    .filter(Files::isRegularFile)
                    .collect(Collectors.toList());
        }
    }
}
