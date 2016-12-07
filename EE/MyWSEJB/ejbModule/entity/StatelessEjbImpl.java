package entity;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@Stateless
@Remote(StatelessEjb.class)

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class StatelessEjbImpl implements StatelessEjb
{
   /* (non-Javadoc)
 * @see entity.StatelessEjb#echo(java.lang.String)
 */
@WebMethod
   public String echo(String input)
   {
      return "Hello " + input;
   }
}
