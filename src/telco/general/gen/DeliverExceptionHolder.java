package telco.general.gen;

/**
 * Generated from IDL exception "DeliverException".
 *
 * @author JacORB IDL compiler V 3.2, 07-Dec-2012
 * @version generated at 08.04.2013 20:08:34
 */

public final class DeliverExceptionHolder
	implements org.omg.CORBA.portable.Streamable
{
	public telco.general.gen.DeliverException value;

	public DeliverExceptionHolder ()
	{
	}
	public DeliverExceptionHolder(final telco.general.gen.DeliverException initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return telco.general.gen.DeliverExceptionHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = telco.general.gen.DeliverExceptionHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		telco.general.gen.DeliverExceptionHelper.write(_out, value);
	}
}
