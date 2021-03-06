package com.projetosuniso.digdin.requisicoes.conta;

import android.os.AsyncTask;
import android.os.Build;
import androidx.annotation.RequiresApi;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

public class ContaAtualiza extends AsyncTask<Void, Void, String> {

    private final String conta;
    private final Long id;

    public ContaAtualiza(String conta, Long id) {
        this.conta = conta;
        this.id = id;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected String doInBackground(Void... voids) {

        String result;

        try {
            URL url = new URL("http://minebank-api-service.herokuapp.com/contas/"+ id);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setReadTimeout(5000);
            connection.setConnectTimeout(5000);
            connection.setRequestProperty("Content-type", "application/json");
            connection.setRequestMethod("PUT");
            connection.setDoOutput(true);

            connection.connect();

            OutputStream os = connection.getOutputStream();
            os.write(conta.getBytes());

            result = readResponse(connection);
            connection.disconnect();

            return result;

        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private String readResponse(HttpURLConnection request) throws IOException {
        ByteArrayOutputStream os;
        try (InputStream is = request.getInputStream()) {
            os = new ByteArrayOutputStream();
            int b;
            while ((b = is.read()) != -1) {
                os.write(b);
            }
        }
        return new String(os.toByteArray());
    }
}
