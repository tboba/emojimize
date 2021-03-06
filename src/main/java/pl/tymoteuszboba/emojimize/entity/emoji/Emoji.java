package pl.tymoteuszboba.emojimize.entity.emoji;

public class Emoji {

    private final String identifier;
    private final String unicode;

    public Emoji(String identifier, String unicode) {
        this.identifier = identifier;
        this.unicode = unicode;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getUnicode() {
        return unicode;
    }

}
