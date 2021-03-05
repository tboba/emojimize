package pl.tymoteuszboba.emojimize.parser;

import pl.tymoteuszboba.emojimize.api.VendorApi;
import pl.tymoteuszboba.emojimize.entity.emoji.Emoji;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmojiParser {

    public static String parse(VendorApi api, String originalString) {
        String[] splitPhrase = originalString.split(" ");

        for (String word : splitPhrase) {
            if (word.contains(":")) {
                Matcher emojiMatcher = Pattern.compile("(?:[:].*?[:])").matcher(word);

                while (emojiMatcher.find()) {
                    int firstEmojiIndex = emojiMatcher.start();
                    int lastEmojiIndex = emojiMatcher.end();

                    String subStringEmoji = word.substring(firstEmojiIndex, lastEmojiIndex);
                    String trimmedEmoji = subStringEmoji.replace(":", "");

                    try {
                        Optional<Emoji> emoji = api.parseEmoji(trimmedEmoji);
                        if (emoji.isPresent()) {
                            originalString = originalString.replace(subStringEmoji,
                                    new String(emoji.get().getUnicode().getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8));
                        }
                    } catch (IOException ignored) {}
                }
            }
        }

        return originalString;
    }

}
