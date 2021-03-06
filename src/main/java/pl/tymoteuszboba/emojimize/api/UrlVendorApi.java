package pl.tymoteuszboba.emojimize.api;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.net.MalformedURLException;
import java.net.URL;

public abstract class UrlVendorApi extends VendorApi {

    public abstract URL getUrl(@Nullable String apiKey, @NotNull String emojiType) throws MalformedURLException;

    public char[] getApiKey() {
        return null;
    }
}
