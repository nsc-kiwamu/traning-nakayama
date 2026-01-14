package race.wrap;

public class TopWrapper implements StringWrapper {

    @Override
    public String wrap(String target, char wrapChar) {

        String[] lines = target.split(System.lineSeparator());
        int maxLength = 0;

        // 一番長い行を探す
        for (String line : lines) {
            if (maxLength < line.length()) {
                maxLength = line.length();
            }
        }

        StringBuilder sb = new StringBuilder();

        // 上の枠
        for (int i = 0; i < maxLength ; i++) {
            sb.append(wrapChar);
        }
        sb.append(System.lineSeparator());

        // 元の文字列
        sb.append(target);

        return sb.toString();
    }
}
