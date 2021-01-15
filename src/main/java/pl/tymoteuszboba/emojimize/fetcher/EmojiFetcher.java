package pl.tymoteuszboba.emojimize.fetcher;

import com.eclipsesource.json.*;
import org.apache.commons.text.StringEscapeUtils;
import org.jetbrains.annotations.NotNull;
import pl.tymoteuszboba.emojimize.api.UrlVendorApi;
import pl.tymoteuszboba.emojimize.entity.emoji.Emoji;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

public class EmojiFetcher {

    public static Optional<Emoji> fetchEmojiFromJson(@NotNull UrlVendorApi api, String emoji) throws IOException {
        URL url = api.getUrl(new String(api.getApiKey()), emoji);
        System.setProperty("http.agent", "Chrome");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

         try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
             JsonValue value = Json.parse(reader);
             if (value.isNull()) {
                 throw new NullPointerException("Emoji cannot be parsed!");
             }

             JsonArray emojiArray = value.asArray();

             if (emojiArray.isEmpty()) {
                 return Optional.empty();
             }

             JsonObject emojiAsObject = emojiArray.get(0).asObject();
             String identifier = emojiAsObject.get("slug").asString();

             byte[] unicodeBytes = emojiAsObject.get("character").asString().getBytes(StandardCharsets.UTF_8);

             System.out.println(new String(unicodeBytes, StandardCharsets.UTF_8));
             return Optional.of(new Emoji(identifier, new String(unicodeBytes, StandardCharsets.UTF_8)));
         }
    }

}
