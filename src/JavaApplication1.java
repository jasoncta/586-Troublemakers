//package javaapplication1;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JavaApplication1 {
    public void parse() {

        JSONParser parser = new JSONParser();

        try {     
            Object obj = parser.parse(new FileReader("/Users/jasontan/Documents/workspace/jena-app/data0.json"));

            JSONObject jsonObject =  (JSONObject) obj;
            System.out.println(jsonObject.toJSONString());
            
            JSONArray arr = jsonObject.getJSONArray("data");
            //JSONArray arr = new JSONArray();
            //arr.add(jsonObject.get("data"));

            //String [] data;
//            System.out.println(data.toString());
//            for (int i = 0; i < data.length; i++) {
//            	System.out.println(data[i]);
//            	//String country = (String) jsonObject.get("country");
//            	//System.out.println(country);
//            }
           
            String amount = (String) jsonObject.get("amount");
            System.out.println(amount);


            // loop array
            /*
            JSONArray data = (JSONArray) jsonObject.get("data");
            Iterator<String> iterator = data.iterator();
            while (iterator.hasNext()) {
             System.out.println(iterator.next());
            }
            */
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}