//package br.edu.ifpb.side.sparql;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.util.FileManager;

public class SparqlTest {
	
	//private static String inputFileName = "/Users/jasontan/Documents/workspace/jena-app/src/peel.rdf";
	//private static String inputFileName = "/Users/jasontan/Documents/workspace/jena-app/src/NTNames.owl";
	//private static String inputFileName = "/Users/jasontan/Documents/workspace/jena-app/src/vc-db-1.rdf";
	private static String inputFileName = "/Users/jasontan/Desktop/example.ttl";


	public static void main(String[] args) {
		sparqlTest();
	}
	
	static void sparqlTest(){
		FileManager.get().addLocatorClassLoader(SparqlTest.class.getClassLoader());
		Model model = FileManager.get().loadModel(inputFileName);
		
		String queryString = //"SELECT ?x WHERE { ?x  <http://www.w3.org/2001/vcard-rdf/3.0#Family>  \"Smith\"}";
				"SELECT ?titles WHERE {<http://example.org/book/book2> ?titles \"SPARQL Tutorial2\" .}";
				
				//"SELECT ?x WHERE { ?x  <http://www.w3.org/2001/vcard-rdf/3.0#FN>  \"John Smith\" }";
						//"SELECT * " +
						//"WHERE { ?x <http://www.w3.org/2001/vcard-rdf/3.0#FN> ?fname }";
						//"WHERE { ?y <http://www.w3.org/2001/vcard-rdf/3.0#Family> \"Smith\" . ?y ?x <http://www.w3.org/2001/vcard-rdf/3.0#Given> ?givenName .}";
				
				//"SELECT ?x WHERE { ?x  <http://www.w3.org/2001/vcard-rdf/3.0#FN>  "John Smith" }";
				//"SELECT ?y WHERE { ?y  <http://www.w3.org/2001/vcard-rdf/3.0#Family>  \"Smith\" .}";
		
		Query query = QueryFactory.create(queryString);
		QueryExecution qexec = QueryExecutionFactory.create(query, model);
		
		try {
			org.apache.jena.query.ResultSet results = qexec.execSelect();
			while ( results.hasNext() ){
				QuerySolution solution = results.nextSolution();
				//Literal name = solution.getLiteral("x");
				RDFNode name = solution.get("titles");
				String a = name.toString();
				System.out.println(a);
			}
		} finally {
			qexec.close();
		}
		
				
		
	}
}
