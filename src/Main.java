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
	
	public static void convertToRDF(Model model, Resource hasType,String fileName) {

		JSONParser parser = new JSONParser();
		// add the property
		//Resource HAS = model.createProperty("https://www.usc.edu/Has");

		try {     
			Object obj = parser.parse(new FileReader(fileName));

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
				         .addProperty((Property)hasType, b.get("amount").toString());
				         
						

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
		
		Resource hasBirthRate = model.createProperty("https://www.usc.edu/hasBirthRate");
		convertToRDF(model, hasBirthRate, "Birth Rate.json");
		
		Resource hasGDP = model.createProperty("https://www.usc.edu/hasGDP");
		convertToRDF(model, hasGDP, "GDP.json");
		
		Resource hasAverageMonthlyDisposableSalary = model.createProperty("https://www.usc.edu/hasAverageMonthlyDisposableSalary");
		convertToRDF(model, hasAverageMonthlyDisposableSalary, "Average Monthly Disposable Salary.json");
		
		Resource hasElectricityConsumption = model.createProperty("https://www.usc.edu/hasElectricityConsumption");
		convertToRDF(model, hasElectricityConsumption, "Electricity Consumption.json");
		
		Resource hasEmploymentRate = model.createProperty("https://www.usc.edu/hasEmploymentRate");
		convertToRDF(model, hasEmploymentRate, "Employment Rate.json");
		
		Resource hasFemaleRetirement = model.createProperty("https://www.usc.edu/hasFemaleRetirement");
		convertToRDF(model, hasFemaleRetirement, "Female Retirement.json");
		
		Resource hasGasolinePrices = model.createProperty("https://www.usc.edu/hasGasolinePrices");
		convertToRDF(model, hasGasolinePrices, "Gasoline Prices.json");
		
		Resource hasHospitalBeds = model.createProperty("https://www.usc.edu/hasHospitalBeds");
		convertToRDF(model, hasHospitalBeds, "Hospital Beds.json");
		
		Resource hasLaborForce = model.createProperty("https://www.usc.edu/hasLaborForce");
		convertToRDF(model, hasLaborForce, "Labor Force.json");
		
		Resource hasLifeExpectancy = model.createProperty("https://www.usc.edu/hasLifeExpectancy");
		convertToRDF(model, hasLifeExpectancy, "Life Expectancy.json");
		
		Resource hasMaleRetirement = model.createProperty("https://www.usc.edu/hasMaleRetirement");
		convertToRDF(model, hasMaleRetirement, "Male Retirement.json");
		
		Resource hasMilitaryBudget = model.createProperty("https://www.usc.edu/hasMilitaryBudget");
		convertToRDF(model, hasMilitaryBudget, "Military Budget.json");
		
		Resource hasOilConsumption = model.createProperty("https://www.usc.edu/hasOilConsumption");
		convertToRDF(model, hasOilConsumption, "Oil Consumption.json");
		
		Resource hasPopulation = model.createProperty("https://www.usc.edu/hasPopulation");
		convertToRDF(model, hasPopulation, "Population.json");
		
		Resource hasRealEstatePrices = model.createProperty("https://www.usc.edu/hasRealEstatePrices");
		convertToRDF(model, hasRealEstatePrices, "Real Estate Prices.json");
		
		Resource hasStandardWorkWeek = model.createProperty("https://www.usc.edu/hasStandardWorkWeek");
		convertToRDF(model, hasStandardWorkWeek, "Standard Work Week.json");
		
		Resource hasTerrorism = model.createProperty("https://www.usc.edu/hasTerrorism");
		convertToRDF(model, hasTerrorism, "Terrorism.json");
		
		Resource hasUnemploymentRate = model.createProperty("https://www.usc.edu/hasUnemploymentRate");
		convertToRDF(model, hasUnemploymentRate, "Unemployment Rate.json");
		
		

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




