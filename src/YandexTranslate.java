import javax.net.ssl.HttpsURLConnection;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

public class YandexTranslate {
    public static void main(String[] args) throws IOException {

        Scanner inputScanner = new Scanner(System.in);
        String line, translated;
        System.out.println("Введите фразу для перевода : ");

        while (inputScanner.hasNextLine()) {
            line = inputScanner.nextLine();
            translated = translate("en", line);
            System.out.println(translated);
            System.out.println("Введите фразу для перевода : ");

        }

    }

    private static String translate(String lang, String input) throws IOException {
        String urlStr = "https://translate.yandex.net/api/v1.5/tr.json/translate?key=";
        String key = "trnsl.1.1.20180801T170150Z.d9c1c191c45cd0a6.dab17b54e4c6d1ac1ec2665f81a4b8fe4b5eec31";
        urlStr = urlStr + key + "\n";
        URL urlObj = new URL(urlStr);
        HttpsURLConnection connection = (HttpsURLConnection)urlObj.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        DataOutputStream dataOutputStream = new DataOutputStream(connection.getOutputStream());
        dataOutputStream.writeBytes("text=" + URLEncoder.encode(input, "UTF-8") + "&lang=" + lang);

        InputStream response = connection.getInputStream();
        String json = new java.util.Scanner(response).nextLine();
        int start = json.indexOf("[");
        int end = json.indexOf("]");
        String translated = json.substring(start + 2, end - 1);
        return translated;

    }
}
