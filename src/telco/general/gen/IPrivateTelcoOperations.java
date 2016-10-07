package telco.general.gen;


/**
 * Generated from IDL interface "IPrivateTelco".
 *
 * @author JacORB IDL compiler V 3.2, 07-Dec-2012
 * @version generated at 08.04.2013 20:08:34
 */

public interface IPrivateTelcoOperations
	extends telco.general.gen.IPublicTelcoOperations
{
	/* constants */
	/* operations  */
	telco.general.gen.Message[] login(java.lang.String suffix, java.lang.String pin, telco.general.gen.IClient callback) throws telco.general.gen.InvalidLoginException;
	boolean logout(java.lang.String suffix, java.lang.String pin) throws telco.general.gen.InvalidLoginException;
	boolean send(java.lang.String suffix, java.lang.String pin, telco.general.gen.Message m) throws telco.general.gen.InvalidLoginException,telco.general.gen.DeliverException;
	boolean sendExplicit(java.lang.String suffix, java.lang.String pin, telco.general.gen.Message m) throws telco.general.gen.InvalidLoginException,telco.general.gen.DeliverException;
}
