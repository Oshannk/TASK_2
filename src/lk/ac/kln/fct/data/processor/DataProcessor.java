package lk.ac.kln.fct.data.processor;

import lk.ac.kln.fct.data.pojo.Record;
import lk.ac.kln.fct.data.utils.Constants;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DataProcessor implements Constants {
    private static DateFormat dateformat = new SimpleDateFormat(DATE_FORMAT);

    /**
     * This method reads the contents form a given JSON file and prints the required data.
     *
     * @param filePath the path to JSON file.
     */ArrayList<Record> rcdList = new ArrayList<Record>();
    public ArrayList<Record> getDataByDate(String filePath, String searchDate) {

        try {
            String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
            JSONObject obj  = new JSONObject(fileContent);
            JSONArray arr = obj.getJSONArray(Constants.JSON_ARRAY);

            for(int i = 0; i < arr.length(); i ++) {
                JSONObject element = arr.getJSONObject(i);
                if(element.getString(Constants.DATE).equals(searchDate)) {
                    try {
                        Date date = dateformat.parse(element.getString(Constants.DATE));
                        int deaths = element.getInt(DEATHS);
                        int cases = element.getInt(CASES);
                        String country = element.getString(COUNTRY);
                        String continent = element.getString(CONTINENT);

                        Record rcd = new Record(date,deaths,cases,country,continent);
                        rcdList.add(rcd);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return rcdList;
    }
}
