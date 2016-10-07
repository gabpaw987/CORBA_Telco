package telco.general.gen;

/**
 * Generated from IDL interface "IPublicTelco".
 *
 * @author JacORB IDL compiler V 3.2, 07-Dec-2012
 * @version generated at 08.04.2013 20:08:34
 */

public final class IPublicTelcoHolder	implements org.omg.CORBA.portable.Streamable{
	 public IPublicTelco value;
	public IPublicTelcoHolder()
	{
	}
	public IPublicTelcoHolder (final IPublicTelco initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return IPublicTelcoHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = IPublicTelcoHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		IPublicTelcoHelper.write (_out,value);
	}
}
