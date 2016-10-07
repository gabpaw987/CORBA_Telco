package telco.general.gen;

/**
 * Generated from IDL interface "ITransaction".
 *
 * @author JacORB IDL compiler V 3.2, 07-Dec-2012
 * @version generated at 08.04.2013 20:08:34
 */

public final class ITransactionHolder	implements org.omg.CORBA.portable.Streamable{
	 public ITransaction value;
	public ITransactionHolder()
	{
	}
	public ITransactionHolder (final ITransaction initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return ITransactionHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = ITransactionHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		ITransactionHelper.write (_out,value);
	}
}
