//package br.edu.ifpb.side.sparql;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.util.FileManager;

public class SparqlTest {
	
	//private static String inputFileName = "/Users/jasontan/Documents/workspace/jena-app/src/peel.rdf";
	private static String inputFileName = "/Users/jasontan/Documents/workspace/jena-app/src/NTNames.owl";

	public static void main(String[] args) {
		sparqlTest();
	}
	
	static void sparqlTest(){
		FileManager.get().addLocatorClassLoader(SparqlTest.class.getClassLoader());
		Model model = FileManager.get().loadModel(inputFileName);
		
		String queryString = 
//						"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" +
//						"PREFIX foaf: <http://xmlns.com/foaf/0.1/>" +
						"SELECT * where { " +
//						" ?person foaf:name ?x ." +
//						" FILTER(?x = \"Damires Souza\") " +
//						" ?person foaf:knows ?person2 ." +
//						" ?person2 foaf:name ?y ." +
//						" FILTER(?y = \"Valeria Cavalcanti\") " + 
						"}";
		
		Query query = QueryFactory.create(queryString);
		QueryExecution qexec = QueryExecutionFactory.create(query, model);
		
		try {
			org.apache.jena.query.ResultSet results = qexec.execSelect();
			while ( results.hasNext() ){
				QuerySolution solution = results.nextSolution();
				Literal name = solution.getLiteral("x");
				System.out.println(name);
			}
		} finally {
			qexec.close();
		}
		
				
		
	}
}
