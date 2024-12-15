package softuni.exam.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JSONSalesConverter {

    public static void main(String[] args) throws IOException {
        try {
            String json = Files.readString(Paths.get("src/main/resources/files/json/sales.json"));
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                boolean discounted = jsonObject.getBoolean("discounted");
                String number = jsonObject.getString("number");
                String saleDate = jsonObject.getString("saleDate");
                int sellerId = jsonObject.getInt("seller");
                    System.out.printf(
                                    "\"  {\\n\" +\n" +
                                    "\"    \\\"discounted\\\": %s,\\n\" +\n" +
                                    "\"    \\\"number\\\": \\\"%s\\\",\\n\" +\n" +
                                    "\"    \\\"saleDate\\\": \\\"%s\\\",\\n\" +\n" +
                                    "\"    \\\"seller\\\": %d\\n\" +\n" +
                                    "\"  },\\n\" +\n",
                            discounted, number, saleDate, sellerId);
                }

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }
}

