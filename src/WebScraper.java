import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebScraper {

	public void scrape(HashMap<String, String> urls) {
		try {
			int k = 0;
			for (Map.Entry<String, String> url : urls.entrySet()) {
				JSONObject json = new JSONObject();
				Document doc = Jsoup.connect(url.getValue()).get();
				String title = doc.title();
				Element table = doc.select("tbody").get(0);
				Elements rows = table.select("tr");
				JSONArray array = new JSONArray();
				
				for (int i = 0; i < rows.size(); i++) {
					Element row = rows.get(i);
					String country = row.getElementsByClass("full").get(0).text();
					String amount = row.getElementsByClass("amount").get(0).text();
					String date = row.getElementsByClass("date").get(0).text();
					JSONObject item = new JSONObject();
					item.put("country", country);
					item.put("amount", amount);
					item.put("date", date);
					array.add(item);
				}
				json.put("data", array);
				try {
					FileWriter fileWriter = new FileWriter(url.getKey() + ".json");
					fileWriter.write(json.toJSONString());
					fileWriter.flush();
					fileWriter.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				k += 1;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
