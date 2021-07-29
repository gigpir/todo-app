package it.polito.det.springTemplate.security;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.owasp.encoder.Encode;

public class XSSUtils {
    public static String stripXSS(String value) {
        if(value == null) {
            return null;
        }
        value = Encode.forHtmlContent(value).replaceAll("\0", "");

        return Jsoup.clean(value, Whitelist.none());
    }
}
