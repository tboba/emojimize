package pl.tymoteuszboba.emojimize.api;

import pl.tymoteuszboba.emojimize.entity.emoji.Emoji;

import java.io.IOException;
import java.util.Optional;

public abstract class VendorApi {

    public Optional<Emoji> parseEmoji(String emoji) throws IOException {
        return null;
    }

}
