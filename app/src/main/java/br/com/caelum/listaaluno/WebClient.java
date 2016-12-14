package br.com.caelum.listaaluno;

import java.io.IOException;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

/**
 * Created by android6523 on 14/12/16.
 */

public class WebClient {
    public String post(String json)  {
        // HttpURLConnection con;
        HttpURLConnection con = null;
        try {


            URL url = new URL("https://www.caelum.com.br/mobile");
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-type", "application/json");
            con.setRequestProperty("Accept", "application/json");
            con.setDoInput(true);
            con.setDoOutput(true);

            PrintStream saida = new PrintStream(con.getOutputStream());

            saida.println(json);
            con.connect();

            Scanner scanner = new Scanner(con.getInputStream());
            if (scanner.hasNext()) {
                return scanner.next();

            } else {
                return "Erro ao conectar no serviço";
            }

        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Erro";



    }
}
