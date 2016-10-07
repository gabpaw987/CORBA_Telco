package telco.general.gen;

/**
 * Generated from IDL alias "MessageArray".
 *
 * @author JacORB IDL compiler V 3.2, 07-Dec-2012
 * @version generated at 08.04.2013 20:08:34
 */

public final class MessageArrayHolder
	implements org.omg.CORBA.portable.Streamable
{
	public telco.general.gen.Message[] value;

	public MessageArrayHolder ()
	{
	}
	public MessageArrayHolder (final telco.general.gen.Message[] initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return MessageArrayHelper.type ();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = MessageArrayHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		MessageArrayHelper.write (out,value);
	}
}
