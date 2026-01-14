package change;

import java.io.BufferedReader;
//2025/1/9 NAKAYAMA add_st
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;
//2025/1/9 NAKAYAMA add_end

/**
 * 入出力の問題
 */
public class Problem14 {

    /**
     * csvファイルを読み込み、加算結果を出力する
     * @param args
     */
    public static void main(String[] args) {
        //2025/1/9 NAKAYAMA add_st
        execBufferedReaderToFile();
        //2025/1/9 NAKAYAMA add_end

        /*
         * ここから問題
         */

        /*
         * 課題１
         * 【実装イメージ】で3通り提示しましたが、すべて実行して試してみてください。
         * 問題なく動くんだな、ということが理解できれば完了として構いません。
         * 可能ならデバッグでステップ実行してもらうとより理解が深まると思います。
         */
        //execBufferedReader();
        //execStream();
        //execList();

        /*
         * 課題２
         * 【実装イメージ】からメモリ展開しないどちらかを選択して、
         * 現在はSystem.outに出力している計算結果をファイルに出力するようにしてください。
         * 今回は出力方法は問いません。（目的とすることが実現できることが大事です）
         */

        /*
         * 課題３
         * 現在は【ポイント】①の
         * 1,2
         * 3,1
         * …
         * を対象とした加算のみになっていますが、
         * これを改造して②の
         * 1,2,+ 
         * 3,1,- 
         * … 
         * が処理出来るようにしてみてください。
         * 演算は
         * 「+」：加算
         * 「-」：減算
         * 「*」：乗算
         * 「/」：除算
         * の4通りで考えてください。
         * 2項演算で構いませんが、お願いされていることすべて終わってしまってものすごく暇です・・・
         * となるようであれば逆ポーランド記法による複数回演算に改造してみてください。
         */

        /*
         * 課題４
         * 出力内容をその行の演算だけではなく、
         * １つ前の行で行った演算の結果を加算して出力するようにしてみてください。
         * ※１：１つ前の行でも、その前の行との加算は行っています。
         * ※２：修正ポイントはsumメソッドでなくても構いません。（IIRフィルタが参考になるかもしれません）
         */
    }

    /**
     * csvファイルを読み込み、加算結果を出力する。<br>
     * 入力ストリームを使用して1行づつ読み込んで処理するパターン
     */
    protected static void execBufferedReader() {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get("./data/in/Problem14_01.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(sum(line));
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    /**
     * csvファイルを読み込み、加算結果を出力する。<br>
     * ストリームAPIを使用して1行づつ処理するパターン
     */
    protected static void execStream() {
        try (Stream<String> stream = Files.lines(Paths.get("./data/in/Problem14_01.csv"))) {
            stream.forEach(line -> {
                System.out.println(sum(line));
            });
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    /**
     * csvファイルを読み込み、加算結果を出力する。<br>
     * 全行分のデータをリストで取得して処理するパターン
     */
    protected static void execList() {
        List<String> lines;
        try {
            lines = Files.readAllLines(Paths.get("./data/in/Problem14_01.csv"));
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        for (String line : lines) {
            System.out.println(sum(line));
        }
    }

    //2025/1/9 NAKAYAMA add_st
    /**
     * csvファイルを読み込み、計算結果をファイルに出力する
     */
    protected static void execBufferedReaderToFile() {

        int prevResult = 0;

        try {
            // 出力フォルダ作成（存在しない場合）
            Files.createDirectories(Paths.get("./data/out"));

            BufferedReader reader = Files.newBufferedReader(Paths.get("./data/in/Problem14_02.csv"));
            BufferedWriter writer = Files.newBufferedWriter(Paths.get("./data/out/Problem14_result.csv"));

            String line;
            while ((line = reader.readLine()) != null) {

                int current = calc(line);
                int total = prevResult + current;

                writer.write(String.valueOf(total));
                writer.newLine();

                prevResult = total;
            }

            reader.close();
            writer.close();

        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
    //2025/1/9 NAKAYAMA add_end

    //2025/1/9 NAKAYAMA add_st
    /**
     * 1行分の計算を行う（課題3用）
     */
    protected static int calc(String line) {
        String[] tokens = line.split(",");

        int a = Integer.parseInt(tokens[0]);
        int b = Integer.parseInt(tokens[1]);
        String op = tokens[2];

        switch (op) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                return a / b;
            default:
                throw new IllegalArgumentException("不正な演算子");
        }
    }
    //2025/1/9 NAKAYAMA add_end

    //2025/1/9 NAKAYAMA add_st
    /**
     * 元の課題1用 sum() メソッド
     * 1行分の数値をカンマ区切りで足す
     */
    protected static int sum(String line) {
        int sum = 0;
        for (String val : line.split(",")) {
            sum += Integer.parseInt(val);
        }
        return sum;
    }
    //2025/1/9 NAKAYAMA add_end

}
