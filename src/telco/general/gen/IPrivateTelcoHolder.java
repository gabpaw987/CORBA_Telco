package telco.general.gen;

/**
 * Generated from IDL interface "IPrivateTelco".
 *
 * @author JacORB IDL compiler V 3.2, 07-Dec-2012
 * @version generated at 08.04.2013 20:08:34
 */

public final class IPrivateTelcoHolder	implements org.omg.CORBA.portable.Streamable{
	 public IPrivateTelco value;
	public IPrivateTelcoHolder()
	{
	}
	public IPrivateTelcoHolder (final IPrivateTelco initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return IPrivateTelcoHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = IPrivateTelcoHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		IPrivateTelcoHelper.write (_out,value);
	}
}
