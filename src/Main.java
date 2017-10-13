import java.io.InputStream;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.util.FileManager;



public class Main {
	
	//private static String inputFileName = "/Users/jasontan/Documents/workspace/jena-app/src/NYCpopulation.rdf";
	private static String inputFileName = "/Users/jasontan/Documents/workspace/jena-app/src/NTNames.owl";
	//private static String inputFileName = "/Users/jasontan/Documents/workspace/jena-app/src/peel.rdf";
	//private static String inputFileName = "/Users/jasontan/Documents/workspace/jena-app/src/vc-db-1.rdf";

	public Main() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		 // create an empty model
		 Model model = ModelFactory.createDefaultModel();

		 // use the FileManager to find the input file
		 InputStream in = FileManager.get().open( inputFileName );
		if (in == null) {
		    throw new IllegalArgumentException(
		                                 "File: " + inputFileName + " not found");
		}

		// read the RDF/XML file
		model.read(in, null);

		// write it to standard out
		model.write(System.out, "TURTLE");
		      
	}

}
