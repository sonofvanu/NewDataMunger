package com.stackroute.datamunger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataMunger {

	public static void main(String[] args) {
		// read the query from the user into queryString variable
		String queryString="select * from ipl.csv where season > 2014 and city ='Bangalore'";
		DataMunger dataMunger=new DataMunger();
		dataMunger.parseQuery(queryString);
		// call the parseQuery method and pass the queryString variable as a parameter

	}
	

	public void parseQuery(String queryString) {
		//call the methods
		getSplitStrings(queryString);
		getFile(queryString);
		getBaseQuery(queryString);
		getConditionsPartQuery(queryString);
		getConditions(queryString);
		getLogicalOperators(queryString);
		getFields(queryString);
		getOrderByFields(queryString);
		getGroupByFields(queryString);
		getAggregateFunctions(queryString);
	}
	
	// parse the queryString into words and display
	public String[] getSplitStrings(String queryString) {
		
		String[] splittedStrings=queryString.split(" ");
		return splittedStrings;
	}

	// get and display the filename
	public String getFile(String queryString) {
		
		String fileName=(queryString.split("from")[1].trim()).split("where")[0].trim();
		return fileName;
	}
	
	// getting the baseQuery and display
	public String getBaseQuery(String queryString) {
		String baseQuery=(queryString.split("where")[0].trim());
		return baseQuery;

	}
	
	// get and display the where conditions part(if where condition exists)
	public String getConditionsPartQuery(String queryString) {
		String conditionsQuery=(queryString.split("where")[1].trim()).split("order by|group by")[0].trim();
		return conditionsQuery;

	}
	
	/* parse the where conditions and display the propertyName, propertyValue and
	 conditionalOperator for each conditions*/
	public String[] getConditions(String queryString) {
		String[] conditions=(queryString.split("where")[1].trim()).split("order by|group by")[0].trim().split("and|or");
		return conditions;
	}
	
	// get the logical operators(applicable only if multiple conditions exist)
	public String[] getLogicalOperators(String queryString) {
		List<String> logicalOperator=new ArrayList<>();
		for(String string:getConditions(queryString))
		{
			if(string.contains("min|max|avg|sum|count"))
				logicalOperator.add(string);
		}
		String[] logicalOperators=logicalOperator.toArray(new String[logicalOperator.size()]);
		return logicalOperators;
		
	}
	
	/*get the fields from the select clause*/
	public String[] getFields(String queryString) {
		
		String[] fields=(queryString.split("from")[0].trim()).split("select")[1].trim().split(",");
		return fields;
		
	}
	// get order by fields if order by clause exists
	public String[] getOrderByFields(String queryString) {
		if(queryString.contains("order by"))
		{
		String[] orderByField=(queryString.split("order by")[1].trim()).split(" ")[0].trim().toLowerCase().split(" ");
		return orderByField;
		}
		
		return null;
	}
	
	// get group by fields if group by clause exists
	public String[] getGroupByFields(String queryString) {
		if(queryString.contains("group by"))
		{
		String[] groupByFields=(queryString.split("group by")[1].trim()).split(" ")[0].trim().toLowerCase().split(" ");
		return groupByFields;
		}
		return null;
	}
	
	// parse and display aggregate functions(if applicable)
	public String[] getAggregateFunctions(String queryString) {
		List<String> aggregateFunction=new ArrayList<>();
		for(String string:getFields(queryString))
		{
			if(string.contains("min|max|avg|sum|count"))
			aggregateFunction.add(string);
		}
		String[] aggregateFunctions=aggregateFunction.toArray(new String[aggregateFunction.size()]);

		return aggregateFunctions;
	}

	
	
	
	
}