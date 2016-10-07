package telco.general.gen;

/**
 * Generated from IDL interface "IClient".
 *
 * @author JacORB IDL compiler V 3.2, 07-Dec-2012
 * @version generated at 08.04.2013 20:08:34
 */

public final class IClientHolder	implements org.omg.CORBA.portable.Streamable{
	 public IClient value;
	public IClientHolder()
	{
	}
	public IClientHolder (final IClient initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return IClientHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = IClientHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		IClientHelper.write (_out,value);
	}
}
