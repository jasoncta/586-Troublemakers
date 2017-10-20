import java.io.InputStream;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.util.FileManager;
import org.apache.jena.vocabulary.VCARD;



public class Main {
	
	//private static String inputFileName = "/Users/jasontan/Documents/workspace/jena-app/src/NYCpopulation.rdf";
	private static String inputFileName = "/Users/jasontan/Documents/workspace/jena-app/src/NTNames.owl";
	//private static String inputFileName = "/Users/jasontan/Documents/workspace/jena-app/src/peel.rdf";
	//private static String inputFileName = "/Users/jasontan/Documents/workspace/jena-app/src/vc-db-1.rdf";
	//private static String inputFileName = "/Users/jasontan/Documents/workspace/jena-app/src/API_NY.GDP.MKTP.CD_DS2_en_csv_v2.csv";

	public Main() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		WebScraper web = new WebScraper();
		String[] urls = new String[] {"http://www.nationmaster.com/country-info/stats/Economy/GDP"}; 
		web.scrape(urls);
		
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
		 
		// some definitions
		 String personURI    = "http://somewhere/country";
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
		
		      
	}

}
