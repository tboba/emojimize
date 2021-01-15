package pl.tymoteuszboba.emojimize;

import pl.tymoteuszboba.emojimize.api.OpenEmojiVendorApi;

import java.nio.charset.StandardCharsets;

public class TestClass {

    public static void main(String[] args) {
        String TEST_STRING
                = "ASCII     abcde xyz\n"
                + "German    äöü ÄÖÜ ß\n"
                + "Polish    ąęźżńł\n"
                + "Russian   абвгдеж эюя\n"
                + "CJK       你好\n";

        System.out.println(new String(TEST_STRING.getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8));
        System.out.println("\uD83D\uDE03");
        Emojimize emojimize = new Emojimize(new OpenEmojiVendorApi("f837c590baa885dc808b97ffbe42bc68fac52c0c"));
        System.out.println(emojimize.parse("Open emoji jest super! :grinning-squinting-face:"));
    }

}
