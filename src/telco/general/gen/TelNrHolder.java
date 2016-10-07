package telco.general.gen;

/**
 * Generated from IDL struct "TelNr".
 *
 * @author JacORB IDL compiler V 3.2, 07-Dec-2012
 * @version generated at 08.04.2013 20:08:34
 */

public final class TelNrHolder
	implements org.omg.CORBA.portable.Streamable
{
	public telco.general.gen.TelNr value;

	public TelNrHolder ()
	{
	}
	public TelNrHolder(final telco.general.gen.TelNr initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return telco.general.gen.TelNrHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = telco.general.gen.TelNrHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		telco.general.gen.TelNrHelper.write(_out, value);
	}
}
