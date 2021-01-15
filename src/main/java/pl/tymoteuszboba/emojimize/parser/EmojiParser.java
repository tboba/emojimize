package pl.tymoteuszboba.emojimize.parser;

import pl.tymoteuszboba.emojimize.api.VendorApi;
import pl.tymoteuszboba.emojimize.entity.emoji.Emoji;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

public class EmojiParser {

    public static String parse(VendorApi api, String originalString) {
        String[] splittedPhrase = originalString.split(" ");

        for (String word : splittedPhrase) {
            if (word.startsWith(":") && word.endsWith(":")) {
                String wordCopy = word;
                word = word.replace(":", "");

                try {
                    Optional<Emoji> emoji = api.parseEmoji(word);
                    if (emoji.isPresent()) {
                        originalString = originalString.replace(wordCopy,
                                new String(emoji.get().getUnicode().getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8));
                    }
                } catch (IOException ignored) {}
            }
        }

        return originalString;
    }

}
