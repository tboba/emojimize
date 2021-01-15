package pl.tymoteuszboba.emojimize;

import pl.tymoteuszboba.emojimize.api.VendorApi;
import pl.tymoteuszboba.emojimize.parser.EmojiParser;

public class Emojimize {

    private final VendorApi api;

    public Emojimize(VendorApi api) {
        this.api = api;
    }

    public String parse(String stringToParse) {
        return EmojiParser.parse(api, stringToParse);
    }

}
