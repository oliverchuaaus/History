package entity;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.WebServiceContext;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class PojoWeb {
	@Resource
	private WebServiceContext ctx;

	@WebMethod
	public String echo(String input) {
		System.out.println(ctx.getMessageContext().entrySet());
		return "Hello " + input;
	}
}
