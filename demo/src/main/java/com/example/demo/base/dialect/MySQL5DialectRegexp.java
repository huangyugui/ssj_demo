package com.example.demo.base.dialect;

import org.hibernate.dialect.function.SQLFunctionTemplate;
import org.hibernate.type.StandardBasicTypes;

public class MySQL5DialectRegexp extends org.hibernate.dialect.MySQL5InnoDBDialect{
	  public MySQL5DialectRegexp() {
	    super();
	    registerFunction( "regexp", new SQLFunctionTemplate(StandardBasicTypes.BOOLEAN, "(case when (?1 REGEXP ?2) then 1 else 0 end)") );
	  }
	}