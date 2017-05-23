package news.flicker.com.flicker;

import java.io.DataInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

//3100245
/**
 * Created by Gemy on 1/3/2017.
 */

public class Connections {
    private static Connections instance = null;
    private  String urlParameters;

    private String result;
    private StringBuffer sb = new StringBuffer();

    public static Connections getInstance(){
        if (instance == null)
            instance = new Connections();
        return instance;
    }

    public String searchConnection(String input){
        URL url;
        HttpURLConnection urlConnection = null;
        String result="";
        try {
            String FlickrApiKey = "54096bbdd188f325eb19b72b9f638a09";
            url = new URL("https://api.flickr.com/services/rest?method=flickr.photos.search&per_page=40&nojsoncallback=1&format=json&tags="+input+"&api_key="+FlickrApiKey);
            urlConnection = (HttpURLConnection) url
                    .openConnection();
            urlConnection.setRequestMethod("GET");
            InputStream in = urlConnection.getInputStream();
           result= getResult(in);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return result;
    }



    private String getResult(InputStream is){
        result="";
        try {
            sb.delete(0, sb.length());
            int c;
            DataInputStream dis = new DataInputStream(is);
            while((c=dis.read())!=-1) {
                sb.append((char) c);
            }
            dis.close();
            result=sb.toString();
            result = new String(result.getBytes("ISO-8859-1"), "UTF-8");
        } catch (Exception ex) {
            result=ex.getMessage().toString();
        }

        return result;
    }
}
