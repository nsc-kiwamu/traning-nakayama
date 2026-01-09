package race.wrap;

public class AllWrapper implements StringWrapper {

    @Override
    public String wrap(String target, char wrapChar) {

        StringWrapper top = new TopWrapper();
        StringWrapper side = new SideWrapper();
        StringWrapper bottom = new BottomWrapper();

        String result = top.wrap(target, wrapChar);
        result = side.wrap(result, wrapChar);
        result = bottom.wrap(result, wrapChar);

        return result;
    }
}
