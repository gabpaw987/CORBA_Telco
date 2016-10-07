package telco.general.gen;

/**
 * Generated from IDL alias "TelNrArray".
 *
 * @author JacORB IDL compiler V 3.2, 07-Dec-2012
 * @version generated at 08.04.2013 20:08:34
 */

public abstract class TelNrArrayHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;

	public static void insert (org.omg.CORBA.Any any, telco.general.gen.TelNr[] s)
	{
		any.type (type ());
		write (any.create_output_stream (), s);
	}

	public static telco.general.gen.TelNr[] extract (final org.omg.CORBA.Any any)
	{
		if ( any.type().kind() == org.omg.CORBA.TCKind.tk_null)
		{
			throw new org.omg.CORBA.BAD_OPERATION ("Can't extract from Any with null type.");
		}
		return read (any.create_input_stream ());
	}

	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(TelNrArrayHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_alias_tc(telco.general.gen.TelNrArrayHelper.id(), "TelNrArray",org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CORBA.ORB.init().create_struct_tc(telco.general.gen.TelNrHelper.id(),"TelNr",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("prefix", org.omg.CORBA.ORB.init().create_string_tc(0), null),new org.omg.CORBA.StructMember("suffix", org.omg.CORBA.ORB.init().create_string_tc(0), null)})));
				}
			}
		}
		return _type;
	}

	public static String id()
	{
		return "IDL:telco/general/gen/TelNrArray:1.0";
	}
	public static telco.general.gen.TelNr[] read (final org.omg.CORBA.portable.InputStream _in)
	{
		telco.general.gen.TelNr[] _result;
		int _l_result0 = _in.read_long();
		try
		{
			 int x = _in.available();
			 if ( x > 0 && _l_result0 > x )
				{
					throw new org.omg.CORBA.MARSHAL("Sequence length too large. Only " + x + " available and trying to assign " + _l_result0);
				}
		}
		catch (java.io.IOException e)
		{
		}
		_result = new telco.general.gen.TelNr[_l_result0];
		for (int i=0;i<_result.length;i++)
		{
			_result[i]=telco.general.gen.TelNrHelper.read(_in);
		}

		return _result;
	}

	public static void write (final org.omg.CORBA.portable.OutputStream _out, telco.general.gen.TelNr[] _s)
	{
		
		_out.write_long(_s.length);
		for (int i=0; i<_s.length;i++)
		{
			telco.general.gen.TelNrHelper.write(_out,_s[i]);
		}

	}
}
