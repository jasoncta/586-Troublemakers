import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.util.FileManager;
import org.apache.jena.vocabulary.VCARD;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;



public class Main {

	//private static String inputFileName = "/Users/jasontan/Documents/workspace/jena-app/src/NYCpopulation.rdf";
	private static String inputFileName = "/Users/jasontan/Documents/workspace/jena-app/src/NTNames.owl";
	//private static String inputFileName = "/Users/jasontan/Documents/workspace/jena-app/src/peel.rdf";
	//private static String inputFileName = "/Users/jasontan/Documents/workspace/jena-app/src/vc-db-1.rdf";
	//private static String inputFileName = "/Users/jasontan/Documents/workspace/jena-app/src/API_NY.GDP.MKTP.CD_DS2_en_csv_v2.csv";

	public Main() {
		// TODO Auto-generated constructor stub
	}
	
	public void convertToRDF(Model model, String fileName) {
		
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		WebScraper web = new WebScraper();
		HashMap<String, String> urls = new HashMap<String, String>() {{
			put("GDP","http://www.nationmaster.com/country-info/stats/Economy/GDP");
			put("Population", "http://www.nationmaster.com/country-info/stats/People/Population");
			put("Military Budget", "http://www.nationmaster.com/country-info/stats/Military/Budget");
			put("Electricity Consumption", "http://www.nationmaster.com/country-info/stats/Energy/Electricity/Consumption");;
			put("Gasoline Prices", "http://www.nationmaster.com/country-info/stats/Energy/Gasoline-prices");
			put("Oil Consumption", "http://www.nationmaster.com/country-info/stats/Energy/Oil/Consumption");
			put("Terrorism", "http://www.nationmaster.com/country-info/stats/Conflict/Terrorism/Global-Terrorism-Index");
			put("Life Expectancy", "http://www.nationmaster.com/country-info/stats/Health/Life-expectancy-at-birth%2C-total/Years");
			put("Hospital Beds", "http://www.nationmaster.com/country-info/stats/Health/Hospital-beds/Per-1%2C000-people");
			put("Birth Rate", "http://www.nationmaster.com/country-info/stats/People/Birth-rate");
			put("Unemployment Rate", "http://www.nationmaster.com/country-info/stats/Labor/Unemployment-rate");
			put("Employment Rate", "http://www.nationmaster.com/country-info/stats/Labor/Employment-rate/Adults");
			put("Labor Force", "http://www.nationmaster.com/country-info/stats/Labor/Force/Total/Per-capita");
			put("Male Retirement", "http://www.nationmaster.com/country-info/stats/Labor/Male-retirement-age");
			put("Female Retirement", "http://www.nationmaster.com/country-info/stats/Labor/Female-retirement-age");
			put("Standard Work Week", "http://www.nationmaster.com/country-info/stats/Labor/Hours-worked/Standard-workweek");
			put("Average Monthly Disposable Salary", "http://www.nationmaster.com/country-info/stats/Cost-of-living/Average-monthly-disposable-salary/After-tax");
			put("Real Estate Prices", "http://www.nationmaster.com/country-info/stats/Cost-of-living/Real-estate-prices/Rent-per-month/3-bedroom-apartment/City-centre");
		}}; 
		web.scrape(urls);

		//JavaApplication1 parser = new JavaApplication1();
		//parser.parse();
		
		String countryURI    = "data";

		// create an empty Model
		Model model = ModelFactory.createDefaultModel();

		// create the resource
		//Resource countryGDP = model.createResource(countryURI);

		// add the property
		Resource HAS = model.createProperty("https://www.usc.edu/Has");

		/*
		countryGDP.addProperty(VCARD.FN, country)
		.addProperty(VCARD.N,
				model.createResource()
				.addProperty(VCARD.Given, GDP));;
				model.write(System.out, "TURTLE");
				*/



				JSONParser parser = new JSONParser();

				try {     
					Object obj = parser.parse(new FileReader("Birth Rate.json"));

					JSONObject jsonObject =  (JSONObject) obj;
					System.out.println("test: " + jsonObject.toJSONString());
					System.out.println("get: " + jsonObject.get("data"));
					JSONArray a = (JSONArray) jsonObject.get("data");
					System.out.println(a);
					//JSONObject b = (JSONObject) a.get(0);
					//System.out.println(b.get("country"));
					//System.out.print(jsonObject.get("data"));
					for (int i = 0; i < a.size(); i++) {
						JSONObject b = (JSONObject) a.get(i);
						System.out.println(b.get("country") + " : " + b.get("amount"));
						
						// add the property
						/*
						Resource countryGDP = model.createResource(b.get("country").toString());
					

						
								model.createResource().addProperty((Property) HAS, b.get("country").toString())
								.addProperty((Property) HAS, b.get("amount").toString());
								*/
						
						// some definitions
						String personURI    = "http://somewhere/JohnSmith";
						String givenName    = "John";
						String familyName   = "Smith";
						String fullName     = givenName + " " + familyName;

						// create an empty Model
						//Model model = ModelFactory.createDefaultModel();

						// create the resource
						//   and add the properties cascading style
						String st = b.get("country").toString();
						st = st.replaceAll("\\s+","");
						st = "https://www.usc.edu/" + st;
						System.out.println(st);
						Resource johnSmith
						  = model.createResource(st)
						         .addProperty((Property)HAS, b.get("amount").toString());
						         
								

					}


					//String amount = (String) jsonObject.get("amount");
					//System.out.println(amount);





				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ParseException e) {
					e.printStackTrace();
				}


				// create an empty model
				//Model model = ModelFactory.createDefaultModel();

				// use the FileManager to find the input file
				/*
		 InputStream in = FileManager.get().open( inputFileName );
		if (in == null) {
		    throw new IllegalArgumentException(
		                                 "File: " + inputFileName + " not found");
		}

		// read the RDF/XML file
		model.read(in, null);
				 */

				// write it to standard out
				//model.write(System.out, "TURTLE");
				/*
	// some definitions
	String personURI    = "data";
	String country     = "USA";
	String GDP = "$100";

	// create an empty Model
	Model model = ModelFactory.createDefaultModel();

	// create the resource
	Resource countryGDP = model.createResource(personURI);

	// add the property

	countryGDP.addProperty(VCARD.FN, country)
	.addProperty(VCARD.N,
			model.createResource()
			.addProperty(VCARD.Given, GDP));;
			model.write(System.out, "TURTLE");

				 */
				
				model.write(System.out, "TURTLE");
				 // Write as Turtle via model.write
			    model.write(System.out, "TTL") ;
			    
			    String fileName = "data.ttl";
			    FileWriter out = new FileWriter( fileName );
			    try {
			        model.write( out, "TTL" );
			    }
			    finally {
			       try {
			           out.close();
			       }
			       catch (IOException closeException) {
			           // ignore
			       }
			    }





	}
}




