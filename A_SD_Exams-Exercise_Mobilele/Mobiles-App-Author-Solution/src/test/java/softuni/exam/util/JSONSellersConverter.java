package softuni.exam.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JSONSellersConverter {
    public static void main(String[] args) throws IOException {
        try {
            String json = Files.readString(Paths.get("src/main/resources/files/json/sellers.json"));
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String firstName = jsonObject.getString("firstName");
                String lastName = jsonObject.getString("lastName");
                String personalNumber = jsonObject.getString("personalNumber");
                System.out.printf(
                        "\"  {\\n\" +\n" +
                                "\"    \\\"firstName\\\": \\\"%s\\\",\\n\" +\n" +
                                "\"    \\\"lastName\\\": \\\"%s\\\",\\n\" +\n" +
                                "\"    \\\"personalNumber\\\": \\\"%s\\\"\\n\" +\n" +
                                "\"  },\\n\" +\n",
                        firstName, lastName, personalNumber);
            }

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }
}
