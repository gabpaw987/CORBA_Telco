package telco.general.gen;


/**
 * Generated from IDL interface "IPublicTelco".
 *
 * @author JacORB IDL compiler V 3.2, 07-Dec-2012
 * @version generated at 08.04.2013 20:08:34
 */

public interface IPublicTelcoOperations
{
	/* constants */
	/* operations  */
	java.lang.String getName();
	java.lang.String getPrefix();
	boolean deliver(telco.general.gen.Message m) throws telco.general.gen.DeliverException;
	boolean deliverExplicit(telco.general.gen.Message m, org.omg.CosTransactions.Control control_) throws telco.general.gen.DeliverException;
}
