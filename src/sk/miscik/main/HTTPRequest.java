package sk.miscik.main;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by client on 01.02.2018.
 */
public class HTTPRequest {

    public static final String IP_ADDRESS = "194.160.229.181:8081";
    private static HTTPRequest instance;

    private HTTPRequest() {}
    public static HTTPRequest getInstance() {
        if (instance == null) {
            instance = new HTTPRequest();
        }
        return instance;
    }

    public String getAuth(String data) throws Exception {
        URL obj = new URL("http://"+HTTPRequest.IP_ADDRESS+"/api/auth/login");
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

        return response.toString().replaceAll("localhost:[0-9]+",HTTPRequest.IP_ADDRESS);
    }

    public List<String> getCities(User user) throws Exception {
        String url = "http://"+HTTPRequest.IP_ADDRESS+"/api/locations";
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
        String url = "http://"+HTTPRequest.IP_ADDRESS+"/api/locations/"+city+"/rooms";
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
        String url = "http://"+HTTPRequest.IP_ADDRESS+"/api/positions";
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
        String url = "http://"+HTTPRequest.IP_ADDRESS+"/api/interviews";
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
        interview.put("dateTime", applicant.getDate());
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
            JOptionPane.showMessageDialog(null,"Failed to submit interview!","Error",JOptionPane.ERROR_MESSAGE);
            return;
        } else {
            JOptionPane.showMessageDialog(null,"Interview added successfully!","Success",JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public Applicant[] getInterviews(User user, int start, int limit) throws Exception {
        String url = "http://"+HTTPRequest.IP_ADDRESS+"/api/interviews";
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

        Applicant[] applicants = new Applicant[limit];
        for (int i = 0; i < limit; i++) {
            applicants[i] = null;
        }

        JSONArray array = (JSONArray) new JSONParser().parse(response.toString());
        if (start >= array.size()) return null;
        if (start < 0) return null;
        int end = start+limit>=array.size()?array.size():start+limit;
        List list = array.subList(start, end);
        int i = 0;
        for (Object obj1 : list) {
            JSONObject o = (JSONObject) obj1;
            JSONObject candidate = (JSONObject) o.get("candidate");
            String firstName = (String) candidate.get("firstName");
            String lastName = (String) candidate.get("lastName");
            String phone = String.valueOf(candidate.get("phone"));
            String email = (String) candidate.get("email");
            String position = (String) candidate.get("position");

            JSONObject interview = (JSONObject) o.get("interview");
            String city = (String) interview.get("location");
            String room = (String) interview.get("room");
            String date = (String) interview.get("dateTime");

            applicants[i] = new Applicant(firstName,lastName,email,phone,city,room,position,date);

            i++;
        }

        return applicants;
    }

    public void deleteCandidate(User user, Applicant applicant) {
        //TODO: DELETE CANDIDATE
    }
    
    public void logout(User user) throws Exception {
        String url = "http://"+HTTPRequest.IP_ADDRESS+"/api/auth/logout";
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
