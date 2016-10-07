package telco.general.gen;

/**
 * Generated from IDL exception "InvalidLoginException".
 *
 * @author JacORB IDL compiler V 3.2, 07-Dec-2012
 * @version generated at 08.04.2013 20:08:34
 */

public final class InvalidLoginExceptionHolder
	implements org.omg.CORBA.portable.Streamable
{
	public telco.general.gen.InvalidLoginException value;

	public InvalidLoginExceptionHolder ()
	{
	}
	public InvalidLoginExceptionHolder(final telco.general.gen.InvalidLoginException initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return telco.general.gen.InvalidLoginExceptionHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = telco.general.gen.InvalidLoginExceptionHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		telco.general.gen.InvalidLoginExceptionHelper.write(_out, value);
	}
}
