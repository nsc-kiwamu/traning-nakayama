package race.wrap;

public class SideWrapper implements StringWrapper {

    @Override
    public String wrap(String target, char wrapChar) {

        String[] lines = target.split(System.lineSeparator());
        int maxLength = 0;

        // 最長行を取得
        for (String line : lines) {
            if (maxLength < line.length()) {
                maxLength = line.length();
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < lines.length; i++) {

            sb.append(wrapChar);
            sb.append(lines[i]);

            // 足りない分をスペースで埋める
            for (int j = 0; j < maxLength - lines[i].length(); j++) {
                sb.append(" ");
            }

            sb.append(wrapChar);

            if (i < lines.length - 1) {
                sb.append(System.lineSeparator());
            }
        }

        return sb.toString();
    }
}
