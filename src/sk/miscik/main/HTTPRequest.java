package sk.miscik.main;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by client on 01.02.2018.
 */
public class HTTPRequest {

    private static HTTPRequest instance;

    private HTTPRequest() {}
    public static HTTPRequest getInstance() {
        if (instance == null) {
            instance = new HTTPRequest();
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

    public List<String> getCities(User user) throws Exception {
        String url = "http://localhost:8081/api/locations";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.addRequestProperty("Authorization","Bearer "+user.getToken());

        if (con == null) {
            throw new NullPointerException("exception: conn = null");
        }

        con.setRequestMethod("GET");
        con.setDoOutput(true);
        con.setRequestProperty("Accept", "application/json");

        con.connect();

        int responseCode = con.getResponseCode();
        if (responseCode != 200) {
            System.err.println("ERROR: code "+responseCode);
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        JSONParser parser = new JSONParser();
        JSONArray array = (JSONArray) parser.parse(response.toString());

        List<String> cities = new ArrayList<>();
        cities.addAll(array);

        return cities;
    }

    public List<String> getRooms(User user, String city) throws Exception {
        String url = "http://localhost:8081/api/locations/"+city+"/rooms";
        url = url.replaceAll(" ", "%20");
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.addRequestProperty("Authorization","Bearer "+user.getToken());

        if (con == null) {
            throw new NullPointerException("exception: conn = null");
        }

        con.setRequestMethod("GET");
        con.setDoOutput(true);
        con.setRequestProperty("Accept", "application/json");

        con.connect();

        int responseCode = con.getResponseCode();
        if (responseCode != 200) {
            System.err.println("ERROR: code "+responseCode);
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        JSONParser parser = new JSONParser();
        JSONArray array = (JSONArray) parser.parse(response.toString());

        List<String> rooms = new ArrayList<>();
        rooms.addAll(array);

        return rooms;
    }

    public List<String> getPositions(User user) throws Exception {
        String url = "http://localhost:8081/api/positions";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.addRequestProperty("Authorization","Bearer "+user.getToken());

        if (con == null) {
            throw new NullPointerException("exception: conn = null");
        }

        con.setRequestMethod("GET");
        con.setDoOutput(true);
        con.setRequestProperty("Accept", "application/json");

        con.connect();

        int responseCode = con.getResponseCode();
        if (responseCode != 200) {
            System.err.println("ERROR: code "+responseCode);
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        JSONParser parser = new JSONParser();
        JSONArray array = (JSONArray) parser.parse(response.toString());

        List<String> pos = new ArrayList<>();
        pos.addAll(array);

        return pos;
    }

    public void submitInterview(User user, Applicant applicant) throws Exception {
        String url = "http://localhost:8081/api/interviews";
        if (user == null) throw new NullPointerException("user=null");
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.addRequestProperty("Authorization","Bearer "+user.getToken());

        if (con == null) {
            throw new NullPointerException("exception: conn = null");
        }

        con.setRequestMethod("POST");
        con.setDoOutput(true);
        con.setRequestProperty("Accept", "application/json");
        con.setRequestProperty("Content-Type", "application/json");


        JSONObject tree = new JSONObject();

        JSONObject candidade = new JSONObject();
        candidade.put("firstName",applicant.getFirstName());
        candidade.put("lastName",applicant.getLastName());
        candidade.put("phone",applicant.getPhone());
        candidade.put("email", applicant.getEmail());
        candidade.put("position",applicant.getPosition());
        tree.put("candidate", candidade);

        JSONObject interview = new JSONObject();
        interview.put("location", applicant.getCity());
        interview.put("room", applicant.getRoom());
        Date date = new Date();
        SimpleDateFormat sdfD = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdfT = new SimpleDateFormat("HH:mm");
        interview.put("dateTime", sdfD.format(date)+"T"+sdfT.format(date));
        tree.put("interview", interview);

        String data = tree.toJSONString();
        System.out.println(data);

        OutputStream os = con.getOutputStream();
        OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
        osw.write(data);
        osw.flush();
        osw.close();
        os.close();

        con.connect();

        int responseCode = con.getResponseCode();
        if (responseCode != 201) {
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

    public void logout(User user) throws Exception {
        String url = "http://localhost:8081/api/auth/logout";
        if (user == null) throw new NullPointerException("user=null");
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.addRequestProperty("Authorization","Bearer "+user.getToken());

        if (con == null) {
            throw new NullPointerException("exception: conn = null");
        }

        con.setRequestMethod("POST");

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
