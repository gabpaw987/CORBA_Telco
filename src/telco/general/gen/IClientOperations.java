package telco.general.gen;


/**
 * Generated from IDL interface "IClient".
 *
 * @author JacORB IDL compiler V 3.2, 07-Dec-2012
 * @version generated at 08.04.2013 20:08:34
 */

public interface IClientOperations
{
	/* constants */
	/* operations  */
	boolean onSMS(telco.general.gen.Message m);
	boolean onSMSExplicit(telco.general.gen.Message m, org.omg.CosTransactions.Control control_);
}
