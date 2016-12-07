package com.tougher;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService
public class PojoWebImpl implements PojoWeb {

	@Override
	@WebResult(name = "return", targetNamespace = "http://entity/", partName = "return")
	@WebMethod
	public String echo(@WebParam(partName = "arg0", name = "arg0") String arg0) {
		return "Hello " + arg0;
	}
}
