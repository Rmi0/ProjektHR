package sk.miscik.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by client on 01.02.2018.
 */
public class HTMLRequest {

    private static HTMLRequest instance;

    private HTMLRequest() {
    }
    public static HTMLRequest getInstance() {
        if (instance == null) {
            instance = new HTMLRequest();
        }
        return instance;
    }

    public String getAuth(String url, String data) throws Exception {
        if (url == null) throw new NullPointerException("url=null");
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        if (con == null) {
            throw new NullPointerException("exception: conn = null");
        }

        con.setRequestMethod("POST");
        con.setDoOutput(true);
        con.setRequestProperty("Accept", "application/json");
        con.setRequestProperty("Content-Type", "application/json");

        OutputStream os = con.getOutputStream();
        OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
        osw.write(data);
        osw.flush();
        osw.close();
        os.close();

        con.connect();

        int responseCode = con.getResponseCode();
        if (responseCode != 200) {
            System.err.println("ERROR: code "+responseCode);
            return "ERROR_"+responseCode;
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString();
    }

    public void logout(User user) throws Exception {
        String url = "http://194.160.229.181:8081/api/auth/logout";
        if (user == null) throw new NullPointerException("user=null");
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.addRequestProperty("Authorization","Bearer "+user.getToken());

        if (con == null) {
            throw new NullPointerException("exception: conn = null");
        }

        con.setRequestMethod("POST");

//        OutputStream os = con.getOutputStream();
//        OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
//        osw.write(data);
//        osw.flush();
//        osw.close();
//        os.close();

        con.connect();

        int responseCode = con.getResponseCode();
        if (responseCode != 200) {
            System.err.println("ERROR: code "+responseCode);
            return;
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
    }

}
