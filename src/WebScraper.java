import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.jsoup.Connection.Response;
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
					
					if(isMoney(amount)) {
						item.put("amount", parseMoney(amount));
					}
					else {
						item.put("amount", parseAmount(amount));
					}
					
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
	
	public void scrape_wiki(HashMap<String, String> urls) throws IOException {
		Response res = Jsoup.connect("http://en.wikipedia.org/wiki/Greece").execute();
		String html = res.body();
		Document doc2 = Jsoup.parseBodyFragment(html);
		Element body = doc2.body();
		Elements tables = body.getElementsByTag("table");
		Element infobox = null;
		for (Element table : tables) {
			if (table.className().contains("infobox")==true) {
				infobox = table.getElementsByTag("tbody").get(0);
//				System.out.println(table.outerHtml());
				break;
			}
		}
		Elements rows = infobox.select("tr");
		JSONArray array = new JSONArray();
		
		for (int i = 0; i < rows.size(); i++) {
			Element row = rows.get(i);
//			System.out.println(row.outerHtml());
//			System.out.println("Ok");
			if(row.select("td").get(0).text() == "Religion") {
				System.out.println("Ok");
				System.out.println(row.getElementsByTag("td").text());
			}
		}
		
	}
	
	public double formatAmount(String n) {
		double res = 0.0;
		if(isMoney(n)) {
			res = parseMoney(n);
		}
		else if(isFigure(n)) {
			res = parseNumber(n); 
		}
		return res;
	}
	
	public Boolean isMoney(String n) {
		return n.toLowerCase().contains("$") == true;
	}
	
	public Boolean isFigure(String n) {
		return n.toLowerCase().contains("million") == true 
				|| n.toLowerCase().contains("billion") == true 
				|| n.toLowerCase().contains("trillion") == true;
	}
	
	public double parseMoney(String n) {
		return parseNumber(n.replace("$", "").replace("US", ""));
	}
	
	public double parseAmount(String n) {
		return Double.parseDouble(n.replaceAll(",", "").replace("%", "").split(" ")[0]);
	}
	
	public double parseNumber(String n) { 
		double res = 0.0;
		if(n.contains("million") || n.contains("MN")) {
			res = Double.parseDouble(n.replace("million", "").replace("MN", "").replaceAll(",", "")) * 10e6;
		}
		if(n.contains("billion") || n.contains("BN")) {
			res = Double.parseDouble(n.replace("billion", "").replace("BN", "").replaceAll(",", "")) * 10e9;
		}
		if(n.contains("trillion") || n.contains("TN")) {
			res = Double.parseDouble(n.replace("trillion", "").replace("TN", "").replaceAll(",", "")) * 10e12;
		}
		try {
			res = Double.parseDouble(n.replaceAll(",", ""));
		}
		catch(Exception e) {
			
		}
		return res;
	}
}
