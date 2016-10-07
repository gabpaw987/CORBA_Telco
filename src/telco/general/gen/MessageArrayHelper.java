package telco.general.gen;

/**
 * Generated from IDL alias "MessageArray".
 *
 * @author JacORB IDL compiler V 3.2, 07-Dec-2012
 * @version generated at 08.04.2013 20:08:34
 */

public abstract class MessageArrayHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;

	public static void insert (org.omg.CORBA.Any any, telco.general.gen.Message[] s)
	{
		any.type (type ());
		write (any.create_output_stream (), s);
	}

	public static telco.general.gen.Message[] extract (final org.omg.CORBA.Any any)
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
			synchronized(MessageArrayHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_alias_tc(telco.general.gen.MessageArrayHelper.id(), "MessageArray",org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CORBA.ORB.init().create_struct_tc(telco.general.gen.MessageHelper.id(),"Message",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("text", org.omg.CORBA.ORB.init().create_string_tc(0), null),new org.omg.CORBA.StructMember("sender", org.omg.CORBA.ORB.init().create_struct_tc(telco.general.gen.TelNrHelper.id(),"TelNr",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("prefix", org.omg.CORBA.ORB.init().create_string_tc(0), null),new org.omg.CORBA.StructMember("suffix", org.omg.CORBA.ORB.init().create_string_tc(0), null)}), null),new org.omg.CORBA.StructMember("time", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(23)), null),new org.omg.CORBA.StructMember("reciever", org.omg.CORBA.ORB.init().create_alias_tc(telco.general.gen.TelNrArrayHelper.id(), "TelNrArray",org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CORBA.ORB.init().create_struct_tc(telco.general.gen.TelNrHelper.id(),"TelNr",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("prefix", org.omg.CORBA.ORB.init().create_string_tc(0), null),new org.omg.CORBA.StructMember("suffix", org.omg.CORBA.ORB.init().create_string_tc(0), null)}))), null)})));
				}
			}
		}
		return _type;
	}

	public static String id()
	{
		return "IDL:telco/general/gen/MessageArray:1.0";
	}
	public static telco.general.gen.Message[] read (final org.omg.CORBA.portable.InputStream _in)
	{
		telco.general.gen.Message[] _result;
		int _l_result1 = _in.read_long();
		try
		{
			 int x = _in.available();
			 if ( x > 0 && _l_result1 > x )
				{
					throw new org.omg.CORBA.MARSHAL("Sequence length too large. Only " + x + " available and trying to assign " + _l_result1);
				}
		}
		catch (java.io.IOException e)
		{
		}
		_result = new telco.general.gen.Message[_l_result1];
		for (int i=0;i<_result.length;i++)
		{
			_result[i]=telco.general.gen.MessageHelper.read(_in);
		}

		return _result;
	}

	public static void write (final org.omg.CORBA.portable.OutputStream _out, telco.general.gen.Message[] _s)
	{
		
		_out.write_long(_s.length);
		for (int i=0; i<_s.length;i++)
		{
			telco.general.gen.MessageHelper.write(_out,_s[i]);
		}

	}
}
