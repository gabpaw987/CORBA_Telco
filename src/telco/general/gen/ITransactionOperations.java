package telco.general.gen;


/**
 * Generated from IDL interface "ITransaction".
 *
 * @author JacORB IDL compiler V 3.2, 07-Dec-2012
 * @version generated at 08.04.2013 20:08:34
 */

public interface ITransactionOperations
	extends org.omg.CosTransactions.ResourceOperations , org.omg.CosTransactions.TransactionalObjectOperations
{
	/* constants */
	/* operations  */
	void useMessage(telco.general.gen.Message m);
	void useMessageExplicit(telco.general.gen.Message m, org.omg.CosTransactions.Control control_);
}
