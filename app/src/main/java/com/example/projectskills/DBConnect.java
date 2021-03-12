package com.example.projectskills;


import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class DBConnect extends AsyncTask<String, Void, String>
{
    Context context;
    AlertDialog alertDialog;

    DBConnect(Context context)
    {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... params) {

        String type = params[0];

        if(type.equals("login"))
        {
            String login_url = "http://10.0.2.2/dashboard/DB_LOGIN.php";

            try
            {
                String user_name = params[1];
                String pass_word = params[2];

                URL url = new URL(login_url);
                HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
                httpConnection.setRequestMethod("POST");
                httpConnection.setDoOutput(true);
                httpConnection.setDoInput(true);
                OutputStream outputStream = httpConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String postData = URLEncoder.encode("user_name", "UTF-8") +"="+ URLEncoder.encode(user_name, "UTF-8")
                        +"&"+ URLEncoder.encode("pass_word", "UTF-8") +"="+ URLEncoder.encode(pass_word, "UTF-8");
                bufferedWriter.write(postData);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "", line = "";

                while((line = bufferedReader.readLine()) != null)
                {
                    result += line;
                }

                bufferedReader.close();
                inputStream.close();
                httpConnection.disconnect();

                return result;
            }
            catch (MalformedURLException e)
            {
                e.printStackTrace();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        else if(type.equals("register"))
        {
            String login_url = "http://10.0.2.2/dashboard/DB_REG.php";

            try
            {
                String user_name = params[1];
                String pass_word = params[2];
                String acc_type = params[3];

                URL url = new URL(login_url);
                HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
                httpConnection.setRequestMethod("POST");
                httpConnection.setDoOutput(true);
                httpConnection.setDoInput(true);
                OutputStream outputStream = httpConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String postData = URLEncoder.encode("user_name", "UTF-8") +"="+ URLEncoder.encode(user_name, "UTF-8")
                        +"&"+ URLEncoder.encode("pass_word", "UTF-8") +"="+ URLEncoder.encode(pass_word, "UTF-8")
                        +"&"+ URLEncoder.encode("acc_type", "UTF-8") +"="+ URLEncoder.encode(acc_type, "UTF-8");
                bufferedWriter.write(postData);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "", line = "";

                while((line = bufferedReader.readLine()) != null)
                {
                    result += line;
                }

                bufferedReader.close();
                inputStream.close();
                httpConnection.disconnect();

                return result;
            }
            catch (MalformedURLException e)
            {
                e.printStackTrace();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    protected void onPreExecute()
    {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Login/Register Status.");
    }

    @Override
    protected void onPostExecute(String result)
    {
        alertDialog.setMessage(result);
        alertDialog.show();
    }

    @Override
    protected void onProgressUpdate(Void... values)
    {
        super.onProgressUpdate(values);
    }
}