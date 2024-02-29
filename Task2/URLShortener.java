import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class URLShortener {
    private Map<String, String> shortToLongMap;
    private Map<String, String> longToShortMap;
    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int SHORT_URL_LENGTH = 6;

    public URLShortener() {
        this.shortToLongMap = new HashMap<>();
        this.longToShortMap = new HashMap<>();
    }

    public String shortenURL(String longURL) {
        if (longToShortMap.containsKey(longURL)) {
            return longToShortMap.get(longURL);
        }
        String shortURL = generateShortURL();
        shortToLongMap.put(shortURL, longURL);
        longToShortMap.put(longURL, shortURL);
        System.out.println("");
        return shortURL;
    }

    public String getOriginalURL(String shortURL) {
        return shortToLongMap.getOrDefault(shortURL, null);
    }

    private String generateShortURL() {
        StringBuilder shortURL = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < SHORT_URL_LENGTH; i++) {
            int index = random.nextInt(CHARACTERS.length());
            shortURL.append(CHARACTERS.charAt(index));
        }
        return shortURL.toString();
    }

    public static void main(String[] args) {
        URLShortener urlShortener = new URLShortener();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the long URL: ");
        String longURL = scanner.nextLine();

        String shortURL = urlShortener.shortenURL(longURL);
        System.out.println("Shortened URL: " + shortURL);

        String originalURL = urlShortener.getOriginalURL(shortURL);
        System.out.println("Original URL: " + originalURL);

        scanner.close();
    }
}
