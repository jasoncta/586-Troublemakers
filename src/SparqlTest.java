//package br.edu.ifpb.side.sparql;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.util.FileManager;

public class SparqlTest {
	
	//private static String inputFileName = "/Users/jasontan/Documents/workspace/jena-app/src/peel.rdf";
	//private static String inputFileName = "/Users/jasontan/Documents/workspace/jena-app/src/NTNames.owl";
	//private static String inputFileName = "/Users/jasontan/Documents/workspace/jena-app/src/vc-db-1.rdf";
	//private static String inputFileName = "/Users/jasontan/Desktop/example.ttl";
	private static String inputFileName = "data.ttl";
	//private static String inputFileName = "vc-db-2.rdf";


	public static void main(String[] args) {
		sparqlTest();
	}
	
	static void sparqlTest(){
		FileManager.get().addLocatorClassLoader(SparqlTest.class.getClassLoader());
		Model model = FileManager.get().loadModel(inputFileName);
		
		String queryString = //"SELECT ?x WHERE { ?x  <http://www.w3.org/2001/vcard-rdf/3.0#Family>  \"Smith\"}";
				//"SELECT ?titles WHERE {<http://example.org/book/book2> ?titles \"SPARQL Tutorial2\" .}";
				//"SELECT ?x ?titles WHERE {<http://example.org/book/book2> ?x ?titles .}";
				"SELECT * WHERE {?x <http://www.nationmaster.com/country-info/stats/Economy/GDP> ?gdp . " +
				"FILTER(?gdp > 10000000000000)}";
				
				/*
				"PREFIX vcard: <http://www.w3.org/2001/vcard-rdf/3.0#> " +

					"SELECT ?g " +
					"WHERE " +
					"{ ?y vcard:Given ?g . " +
					  "FILTER regex(?g, \"r\", \"i\") }";
				*/
				/*
				"PREFIX info: <http://somewhere/peopleInfo#> " +

					"SELECT ?g " +
					"WHERE " +
					  "{ " +
					    "?g info:age ?age . " +
					    "FILTER (?age >= 24) " +
					  "}";
					  */
				
				
				/*
				"PREFIX info:    <http://somewhere/peopleInfo#> " +
					"PREFIX vcard:   <http://www.w3.org/2001/vcard-rdf/3.0#> " +

					"SELECT * " +
					"WHERE " +
					"{ " +
					    "?person vcard:FN  ?name . " +
					    " ?person info:age ?age . " +
					"}";
					*/

		
		//"SELECT ?titles WHERE { ?y  <http://www.w3.org/2001/vcard-rdf/3.0#N>  <Has> . ?y  <http://www.w3.org/2001/vcard-rdf/3.0#N>  ?titles .}";
				
				//"SELECT ?x WHERE { ?x  <http://www.w3.org/2001/vcard-rdf/3.0#FN>  \"John Smith\" }";
						//"SELECT * " +
						//"WHERE { ?x <http://www.w3.org/2001/vcard-rdf/3.0#FN> ?fname }";
						//"WHERE { ?y <http://www.w3.org/2001/vcard-rdf/3.0#Family> \"Smith\" . ?y ?x <http://www.w3.org/2001/vcard-rdf/3.0#Given> ?givenName .}";
				
				//"SELECT ?x WHERE { ?x  <http://www.w3.org/2001/vcard-rdf/3.0#FN>  "John Smith" }";
				//"SELECT ?y WHERE { ?y  <http://www.w3.org/2001/vcard-rdf/3.0#Family>  \"Smith\" .}";
				/*
				"PREFIX vcard:      <http://www.w3.org/2001/vcard-rdf/3.0#> " +

					"SELECT ?titles " +
					"WHERE " +
					 "{ ?y vcard:Family \"Smith\" . " +
					   "?y vcard:Given  ?titles . " +
					 "}" ;
					 */
		
		Query query = QueryFactory.create(queryString);
		QueryExecution qexec = QueryExecutionFactory.create(query, model);
		
		try {
			org.apache.jena.query.ResultSet results = qexec.execSelect();
			ResultSetFormatter.out(System.out, results, query);
			while ( results.hasNext() ){
				QuerySolution solution = results.nextSolution();
				//Literal name = solution.getLiteral("x");
				//RDFNode name = solution.get("g");
				RDFNode name2 = solution.get("age");
				RDFNode name = solution.get("g");
				String a = name.toString();
				//String b = name2.toString();
				System.out.println(a + " ");
			}
		} finally {
			qexec.close();
		}
		
				
		
	}
}
