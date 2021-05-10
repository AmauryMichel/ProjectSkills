package com.example.projectskills;


import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class DBConnect {
    // Set of static strings so that if there is a change in the link, you can simply edit it here instead of everywhere
    public static String addressBase = "http://10.0.2.2/project/";
    public static String addressLogin = "DB_LOGIN.php";
    public static String addressRegister = "DB_REG.php";

    public static String addressGetGroups = "DB_GET_GROUPS.php";

    public static String addressCrGroup = "DB_CR_GROUP.php";


    public static String login(String username, String pass) {
        String login_url = addressBase + addressLogin;
        StringBuilder result = new StringBuilder();

        try {
            URL url = new URL(login_url);
            // Connect to the URL
            HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
            httpConnection.setRequestMethod("POST");
            httpConnection.setDoOutput(true);
            httpConnection.setDoInput(true);
            OutputStream outputStream = httpConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));
            // Set the data to be sent to the URL
            String postData = URLEncoder.encode("login", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8")
                    + "&" + URLEncoder.encode("pass", "UTF-8") + "=" + URLEncoder.encode(pass, "UTF-8");
            bufferedWriter.write(postData); // Write the data
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            // Read the data returned from the URL
            InputStream inputStream = httpConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.ISO_8859_1));
            String line = "";

            while ((line = bufferedReader.readLine()) != null) { // Get all of the lines
                result.append(line);
            }

            bufferedReader.close();
            inputStream.close();
            httpConnection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    // register() functions the same as login()
    public static String register(String username, String pass) {
        String register_url = addressBase + addressRegister;
        StringBuilder result = new StringBuilder();

        try {
            URL url = new URL(register_url);
            HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
            httpConnection.setDoOutput(true);
            httpConnection.setDoInput(true);
            OutputStream outputStream = httpConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));
            String postData = URLEncoder.encode("login", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8")
                    + "&" + URLEncoder.encode("pass", "UTF-8") + "=" + URLEncoder.encode(pass, "UTF-8");
            bufferedWriter.write(postData);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            InputStream inputStream = httpConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.ISO_8859_1));
            String line = "";

            while ((line = bufferedReader.readLine()) != null) {
                result.append(line);
            }

            bufferedReader.close();
            inputStream.close();
            httpConnection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    public static JSONArray getGroups(String userID) {
        String login_url = addressBase + addressGetGroups;
        JSONArray result = new JSONArray();

        try {
            URL url = new URL(login_url);
            // Connect to the URL
            HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
            httpConnection.setRequestMethod("POST");
            httpConnection.setDoOutput(true);
            httpConnection.setDoInput(true);
            OutputStream outputStream = httpConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));
            // Set the data to be sent to the URL
            String postData = URLEncoder.encode("userID", "UTF-8") + "=" + URLEncoder.encode(userID, "UTF-8");
            bufferedWriter.write(postData); // Write the data
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            // Read the data returned from the URL
            InputStream inputStream = httpConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.ISO_8859_1));
            String line = "";

            if ((line = bufferedReader.readLine()) != null) { // Get all of the lines
                result = new JSONArray(line);
                Log.d("MyApp", line);
            }

            bufferedReader.close();
            inputStream.close();
            httpConnection.disconnect();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String createGroup(String groupName) {
        String login_url = addressBase + addressCrGroup;
        StringBuilder result = new StringBuilder();

        try {
            URL url = new URL(login_url);
            // Connect to the URL
            HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
            httpConnection.setRequestMethod("POST");
            httpConnection.setDoOutput(true);
            httpConnection.setDoInput(true);
            OutputStream outputStream = httpConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));
            // Set the data to be sent to the URL
            String postData = URLEncoder.encode("managerID", "UTF-8") + "=" + URLEncoder.encode(MainActivity.userID, "UTF-8")
                    + "&" + URLEncoder.encode("groupName", "UTF-8") + "=" + URLEncoder.encode(groupName, "UTF-8");
            bufferedWriter.write(postData); // Write the data
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            // Read the data returned from the URL
            InputStream inputStream = httpConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.ISO_8859_1));
            String line = "";

            while ((line = bufferedReader.readLine()) != null) { // Get all of the lines
                result.append(line);
            }

            bufferedReader.close();
            inputStream.close();
            httpConnection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();
    }
}