package com.qa.application.util;

import java.util.Arrays;

public class JsonDataField {
	public String name;
	  public String[] value;
	  public String expected;
	  
	  public JsonDataField(String name, String[] value)
	  {
	    this.name = name;
	    this.value = Arrays.copyOf(value, value.length);
	  }
	  
	  public JsonDataField(String name, String[] value, String expected)
	  {
	    this.name = name;
	    this.value = Arrays.copyOf(value, value.length);
	    this.expected = expected;
	  }
}
