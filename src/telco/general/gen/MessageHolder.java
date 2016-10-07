package telco.general.gen;

/**
 * Generated from IDL struct "Message".
 *
 * @author JacORB IDL compiler V 3.2, 07-Dec-2012
 * @version generated at 08.04.2013 20:08:34
 */

public final class MessageHolder
	implements org.omg.CORBA.portable.Streamable
{
	public telco.general.gen.Message value;

	public MessageHolder ()
	{
	}
	public MessageHolder(final telco.general.gen.Message initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return telco.general.gen.MessageHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = telco.general.gen.MessageHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		telco.general.gen.MessageHelper.write(_out, value);
	}
}
