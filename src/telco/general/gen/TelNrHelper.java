package telco.general.gen;


/**
 * Generated from IDL struct "TelNr".
 *
 * @author JacORB IDL compiler V 3.2, 07-Dec-2012
 * @version generated at 08.04.2013 20:08:34
 */

public abstract class TelNrHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(TelNrHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_struct_tc(telco.general.gen.TelNrHelper.id(),"TelNr",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("prefix", org.omg.CORBA.ORB.init().create_string_tc(0), null),new org.omg.CORBA.StructMember("suffix", org.omg.CORBA.ORB.init().create_string_tc(0), null)});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final telco.general.gen.TelNr s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static telco.general.gen.TelNr extract (final org.omg.CORBA.Any any)
	{
		org.omg.CORBA.portable.InputStream in = any.create_input_stream();
		try
		{
			return read (in);
		}
		finally
		{
			try
			{
				in.close();
			}
			catch (java.io.IOException e)
			{
			throw new RuntimeException("Unexpected exception " + e.toString() );
			}
		}
	}

	public static String id()
	{
		return "IDL:telco/general/gen/TelNr:1.0";
	}
	public static telco.general.gen.TelNr read (final org.omg.CORBA.portable.InputStream in)
	{
		telco.general.gen.TelNr result = new telco.general.gen.TelNr();
		result.prefix=in.read_string();
		result.suffix=in.read_string();
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final telco.general.gen.TelNr s)
	{
		java.lang.String tmpResult0 = s.prefix;
out.write_string( tmpResult0 );
		java.lang.String tmpResult1 = s.suffix;
out.write_string( tmpResult1 );
	}
}
