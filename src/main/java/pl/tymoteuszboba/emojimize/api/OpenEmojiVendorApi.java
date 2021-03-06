package pl.tymoteuszboba.emojimize.api;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import pl.tymoteuszboba.emojimize.entity.emoji.Emoji;
import pl.tymoteuszboba.emojimize.fetcher.EmojiFetcher;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;

public class OpenEmojiVendorApi extends UrlVendorApi {

    private char[] apiKey;

    public OpenEmojiVendorApi() {
    }

    public OpenEmojiVendorApi(String apiKey) {
        this.apiKey = apiKey.toCharArray();
    }

    public OpenEmojiVendorApi(char[] apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    public URL getUrl(@Nullable final String apiKey, @NotNull final String emojiType) throws MalformedURLException {
        return new URL(String.format("https://emoji-api.com/emojis/%s?access_key=%s", emojiType, apiKey));
    }

    @Override
    public char[] getApiKey() {
        return apiKey;
    }

    @Override
    public Optional<Emoji> parseEmoji(final String emoji) throws IOException {
        return EmojiFetcher.fetchEmojiFromJson(this, emoji);
    }
}
