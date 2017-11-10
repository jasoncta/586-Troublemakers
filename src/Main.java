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
				st = "https://www.sir-lab.usc.edu/cs586/" + st;
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
	
	public static void convertToRDFWithInt(Model model, Resource hasType,String fileName) {

		JSONParser parser = new JSONParser();
		// add the property
		

		try {     
			Object obj = parser.parse(new FileReader(fileName));

			JSONObject jsonObject =  (JSONObject) obj;
			System.out.println("test: " + jsonObject.toJSONString());
			System.out.println("get: " + jsonObject.get("data"));
			JSONArray a = (JSONArray) jsonObject.get("data");
			System.out.println(a);
			
			for (int i = 0; i < a.size(); i++) {
				JSONObject b = (JSONObject) a.get(i);
				System.out.println(b.get("country") + " : " + b.get("amount"));
				
			
				// create an empty Model
				//Model model = ModelFactory.createDefaultModel();

				// create the resource
				//   and add the properties cascading style
				String st = b.get("country").toString();
				st = st.replaceAll("\\s+","");
				st = "https://www.sir-lab.usc.edu/cs586/" + st;
				System.out.println(st);
				Resource johnSmith
				  = model.createResource(st)
				         .addProperty((Property)hasType, model.createTypedLiteral(b.get("amount")));
				         
						

			}


		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	public static void convertToRDFfromEUData(Model model, Resource hasType,String fileName) {

		JSONParser parser = new JSONParser();
		// add the property
		

		try {     
			Object obj = parser.parse(new FileReader(fileName));

			JSONObject jsonObject =  (JSONObject) obj;
			System.out.println("test: " + jsonObject.toJSONString());
			System.out.println("get: " + jsonObject.get("data"));
			JSONArray a = (JSONArray) jsonObject.get("data");
			System.out.println(a);
			
			for (int i = 0; i < a.size(); i++) {
				JSONObject b = (JSONObject) a.get(i);
				System.out.println(b.get("country") + " : " + b.get("amount"));
				
			
				// create an empty Model
				//Model model = ModelFactory.createDefaultModel();

				// create the resource
				//   and add the properties cascading style
				String st = b.get("country").toString();
				st = st.replaceAll("\\s+","");
				st = "https://www.sir-lab.usc.edu/cs586/" + st;
				System.out.println(st);
				Resource johnSmith
				  = model.createResource(st)
				         .addProperty((Property)hasType, model.createTypedLiteral(b.get("amount")));
				         
						

			}


		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	public static void convertToRDFAddCountry(Model model, Resource hasType,String fileName) {

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
				st = "https://www.wikipedia.org/" + st;
				System.out.println(st);
				Resource johnSmith
				  = model.createResource(st)
				         .addProperty((Property)hasType, st);
				         
						

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
		
		String GDP_URL = "http://www.nationmaster.com/country-info/stats/Economy/GDP";
		String Population_URL = "http://www.nationmaster.com/country-info/stats/People/Population";
		String MilitaryBudget_URL = "http://www.nationmaster.com/country-info/stats/Military/Budget";
		String ElectricityConsumption_URL = "http://www.nationmaster.com/country-info/stats/Energy/Electricity/Consumption";
		String GasolinePrices_URL = "http://www.nationmaster.com/country-info/stats/Energy/Gasoline-prices";
		String OilConsumption_URL = "http://www.nationmaster.com/country-info/stats/Energy/Oil/Consumption";
		String Terrorism_URL = "http://www.nationmaster.com/country-info/stats/Conflict/Terrorism/Global-Terrorism-Index";
		String LifeExpectancy_URL = "http://www.nationmaster.com/country-info/stats/Health/Life-expectancy-at-birth%2C-total/Years";
		String HospitalBeds_URL = "http://www.nationmaster.com/country-info/stats/Health/Hospital-beds/Per-1%2C000-people";
		String BirthRate_URL = "http://www.nationmaster.com/country-info/stats/People/Birth-rate";
		String UnemploymentRate_URL = "http://www.nationmaster.com/country-info/stats/Labor/Unemployment-rate";
		String EmploymentRate_URL = "http://www.nationmaster.com/country-info/stats/Labor/Employment-rate/Adults";
		String LaborForce_URL = "http://www.nationmaster.com/country-info/stats/Labor/Force/Total/Per-capita";
		String MaleRetirement_URL = "http://www.nationmaster.com/country-info/stats/Labor/Male-retirement-age";
		String FemaleRetirement_URL = "http://www.nationmaster.com/country-info/stats/Labor/Female-retirement-age";
		String StandardWorkWeek_URL = "http://www.nationmaster.com/country-info/stats/Labor/Hours-worked/Standard-workweek";
		String AverageMonthlyDisposableSalary_URL = "http://www.nationmaster.com/country-info/stats/Cost-of-living/Average-monthly-disposable-salary/After-tax";
		String RealEstatePrices_URL = "http://www.nationmaster.com/country-info/stats/Cost-of-living/Real-estate-prices/Rent-per-month/3-bedroom-apartment/City-centre";
		String CountryName_URL = "http://www.nationmaster.com/hasName";
		String CO2PerCountry_URL = "http://edgar.jrc.ec.europa.eu/overview.php?v=CO2ts1990-2015&sort=des9";
		
		

		WebScraper web = new WebScraper();
		HashMap<String, String> urls = new HashMap<String, String>() {{
			put("GDP",GDP_URL);
			put("Population", Population_URL);
			put("Military Budget", MilitaryBudget_URL);
			put("Electricity Consumption", ElectricityConsumption_URL);
			put("Gasoline Prices", GasolinePrices_URL);
			put("Oil Consumption", OilConsumption_URL);
			put("Terrorism", Terrorism_URL);
			put("Life Expectancy", LifeExpectancy_URL);
			put("Hospital Beds", HospitalBeds_URL);
			put("Birth Rate", BirthRate_URL);
			put("Unemployment Rate", UnemploymentRate_URL);
			put("Employment Rate", EmploymentRate_URL);
			put("Labor Force", LaborForce_URL);
			put("Male Retirement", MaleRetirement_URL);
			put("Female Retirement", FemaleRetirement_URL);
			put("Standard Work Week", StandardWorkWeek_URL);
			put("Average Monthly Disposable Salary", AverageMonthlyDisposableSalary_URL);
			put("Real Estate Prices", RealEstatePrices_URL);
		}}; 
		web.scrape(urls);

		//JavaApplication1 parser = new JavaApplication1();
		//parser.parse();
		
		String countryURI    = "data";

		// create an empty Model
		Model model = ModelFactory.createDefaultModel();
		
		//Resource hasBirthRate = model.createProperty(BirthRate_URL);
		//convertToRDF(model, hasBirthRate, "Birth Rate.json");
		/*
		
		Resource hasGDP = model.createProperty(GDP_URL);
		convertToRDFWithInt(model, hasGDP, "GDP.json");
		
		Resource hasAverageMonthlyDisposableSalary = model.createProperty(AverageMonthlyDisposableSalary_URL);
		convertToRDF(model, hasAverageMonthlyDisposableSalary, "Average Monthly Disposable Salary.json");
		
		Resource hasElectricityConsumption = model.createProperty(ElectricityConsumption_URL);
		convertToRDF(model, hasElectricityConsumption, "Electricity Consumption.json");
		
		Resource hasEmploymentRate = model.createProperty(EmploymentRate_URL);
		convertToRDF(model, hasEmploymentRate, "Employment Rate.json");
		
		Resource hasFemaleRetirement = model.createProperty(FemaleRetirement_URL);
		convertToRDF(model, hasFemaleRetirement, "Female Retirement.json");
		
		Resource hasGasolinePrices = model.createProperty(GasolinePrices_URL);
		convertToRDF(model, hasGasolinePrices, "Gasoline Prices.json");
		
		Resource hasHospitalBeds = model.createProperty("HospitalBeds_URL");
		convertToRDF(model, hasHospitalBeds, "Hospital Beds.json");
		
		Resource hasLaborForce = model.createProperty(LaborForce_URL);
		convertToRDF(model, hasLaborForce, "Labor Force.json");
		
		Resource hasLifeExpectancy = model.createProperty(LifeExpectancy_URL);
		convertToRDF(model, hasLifeExpectancy, "Life Expectancy.json");
		
		Resource hasMaleRetirement = model.createProperty(MaleRetirement_URL);
		convertToRDF(model, hasMaleRetirement, "Male Retirement.json");
		
		Resource hasMilitaryBudget = model.createProperty(MilitaryBudget_URL);
		convertToRDF(model, hasMilitaryBudget, "Military Budget.json");
		
		Resource hasOilConsumption = model.createProperty(OilConsumption_URL);
		convertToRDF(model, hasOilConsumption, "Oil Consumption.json");
		
		Resource hasPopulation = model.createProperty(Population_URL);
		convertToRDF(model, hasPopulation, "Population.json");
		
		Resource hasRealEstatePrices = model.createProperty(RealEstatePrices_URL);
		convertToRDF(model, hasRealEstatePrices, "Real Estate Prices.json");
		
		Resource hasStandardWorkWeek = model.createProperty(StandardWorkWeek_URL);
		convertToRDF(model, hasStandardWorkWeek, "Standard Work Week.json");
		
		Resource hasTerrorism = model.createProperty(Terrorism_URL);
		convertToRDFWithInt(model, hasTerrorism, "Terrorism.json");
		
		Resource hasUnemploymentRate = model.createProperty(UnemploymentRate_URL);
		convertToRDF(model, hasUnemploymentRate, "Unemployment Rate.json");
		
		*/
		Resource hasCO2Emission = model.createProperty(CO2PerCountry_URL);
		convertToRDFfromEUData(model, hasCO2Emission, "co2_2016.json");
		
		
		//Resource hasName = model.createProperty(CountryName_URL);
		//convertToRDFAddCountry(model, hasName, "GDP.json");
		
		

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




