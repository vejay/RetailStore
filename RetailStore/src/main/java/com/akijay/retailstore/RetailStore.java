package com.akijay.retailstore;

import java.io.File;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class RetailStore 
{
	@RequestMapping("/")
	String home()
	{
		return "Hello World21!";
	}
	
	public enum Tutorial implements Label 
	{
		JAVA,SCALA,SQL,NEO4J;
	}
	
	public enum TutorialRelationships implements RelationshipType
	{
		JVM_LANUGAGES, NON_JVM_LANGUAGES;
	}
	
	public static void main(String[] args) throws Exception
	{
		SpringApplication.run(RetailStore.class, args);
		GraphDatabaseFactory dbFactory = new GraphDatabaseFactory();
		GraphDatabaseService db = dbFactory.newEmbeddedDatabase(new File("/Users/vijay/testne04jDB"));
		
		try (Transaction tx = db.beginTx()) 
		{
			// Perform DB operations
			Node javaNode = db.createNode(Tutorial.JAVA);
			javaNode.setProperty("id", "001");
			javaNode.setProperty("name", "Learn Java");
			
			
			Node scalaNode = db.createNode(Tutorial.SCALA);
			scalaNode.setProperty("id", "002");
			scalaNode.setProperty("name", "Learn Scala");
			
			Relationship relationship = javaNode.createRelationshipTo(scalaNode, TutorialRelationships.JVM_LANUGAGES);
			relationship.setProperty("oops", "yes");
			
			tx.success();
		}
	}
}
