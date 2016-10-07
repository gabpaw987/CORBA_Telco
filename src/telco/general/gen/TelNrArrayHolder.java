package telco.general.gen;

/**
 * Generated from IDL alias "TelNrArray".
 *
 * @author JacORB IDL compiler V 3.2, 07-Dec-2012
 * @version generated at 08.04.2013 20:08:34
 */

public final class TelNrArrayHolder
	implements org.omg.CORBA.portable.Streamable
{
	public telco.general.gen.TelNr[] value;

	public TelNrArrayHolder ()
	{
	}
	public TelNrArrayHolder (final telco.general.gen.TelNr[] initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return TelNrArrayHelper.type ();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = TelNrArrayHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		TelNrArrayHelper.write (out,value);
	}
}
