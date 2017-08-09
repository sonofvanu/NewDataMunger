package com.stackroute.datamunger.testcases;

import static org.junit.Assert.*;

import org.junit.*;

import com.stackroute.datamunger.model.WhereRestrictionalConditions;
import com.stackroute.datamunger.parsingunit.*;


public class TestCase {

	static QueryParser queryParser;
	
	@BeforeClass
	public static void initialiser()
	{
		queryParser=new QueryParser();
	}
	
	@Test
	public void parameterVerifier() 
	{
		QueryParameter queryParameter=new QueryParameter();
		queryParameter=queryParser.querySegregator("select empsalary,empname from emp.csv where empsalary>18 and empdept=it order by empsalary");
		assertNotNull(queryParameter);
		display(queryParameter);
	}

	private void display(QueryParameter queryParameter) {
		// TODO Auto-generated method stub
		System.out.println("File path: "+queryParameter.getFilePath());
		System.out.println("Group by column: "+queryParameter.getGroupByColumn());
		System.out.println("Order by column: "+queryParameter.getOrderByColumn());
		System.out.println("Where conditions: ");
		for(WhereRestrictionalConditions conditions:queryParameter.getRestrictions())
		{
			System.out.println(conditions.getColumn()+"    "+conditions.getOperator()+"      "+conditions.getValue());
		}
		System.out.println("Column names:");
		queryParameter.getColumNames().forEach(System.out::println);
	}

}
