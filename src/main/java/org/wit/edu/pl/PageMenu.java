package org.wit.edu.pl;
import org.wit.edu.pl.support.ConfigReader;

public enum PageMenu {
    Buttons("buttons"),
    RadioButton("radio-button"),
    TextButton("text-box"),
    WebTables("webtables");

    private final String URL;

    PageMenu(String URL) {
        this.URL = ConfigReader.get("baseURL") + URL + "/";
    }

    public String getURL() {
        return URL;
    }
}
