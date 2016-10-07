package telco.general.gen;

/**
 * Generated from IDL exception "DeliverException".
 *
 * @author JacORB IDL compiler V 3.2, 07-Dec-2012
 * @version generated at 08.04.2013 20:08:34
 */

public final class DeliverException
	extends org.omg.CORBA.UserException
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public DeliverException()
	{
		super(telco.general.gen.DeliverExceptionHelper.id());
	}

	public java.lang.String reason = "";
	public DeliverException(java.lang.String _reason,java.lang.String reason)
	{
		super(_reason);
		this.reason = reason;
	}
	public DeliverException(java.lang.String reason)
	{
		super(telco.general.gen.DeliverExceptionHelper.id());
		this.reason = reason;
	}
}
