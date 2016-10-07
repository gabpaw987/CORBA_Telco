package telco.general.gen;


/**
 * Generated from IDL struct "Message".
 *
 * @author JacORB IDL compiler V 3.2, 07-Dec-2012
 * @version generated at 08.04.2013 20:08:34
 */

public abstract class MessageHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(MessageHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_struct_tc(telco.general.gen.MessageHelper.id(),"Message",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("text", org.omg.CORBA.ORB.init().create_string_tc(0), null),new org.omg.CORBA.StructMember("sender", org.omg.CORBA.ORB.init().create_struct_tc(telco.general.gen.TelNrHelper.id(),"TelNr",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("prefix", org.omg.CORBA.ORB.init().create_string_tc(0), null),new org.omg.CORBA.StructMember("suffix", org.omg.CORBA.ORB.init().create_string_tc(0), null)}), null),new org.omg.CORBA.StructMember("time", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(23)), null),new org.omg.CORBA.StructMember("reciever", org.omg.CORBA.ORB.init().create_alias_tc(telco.general.gen.TelNrArrayHelper.id(), "TelNrArray",org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CORBA.ORB.init().create_struct_tc(telco.general.gen.TelNrHelper.id(),"TelNr",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("prefix", org.omg.CORBA.ORB.init().create_string_tc(0), null),new org.omg.CORBA.StructMember("suffix", org.omg.CORBA.ORB.init().create_string_tc(0), null)}))), null)});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final telco.general.gen.Message s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static telco.general.gen.Message extract (final org.omg.CORBA.Any any)
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
		return "IDL:telco/general/gen/Message:1.0";
	}
	public static telco.general.gen.Message read (final org.omg.CORBA.portable.InputStream in)
	{
		telco.general.gen.Message result = new telco.general.gen.Message();
		result.text=in.read_string();
		result.sender=telco.general.gen.TelNrHelper.read(in);
		result.time=in.read_longlong();
		result.reciever = telco.general.gen.TelNrArrayHelper.read(in);
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final telco.general.gen.Message s)
	{
		java.lang.String tmpResult2 = s.text;
out.write_string( tmpResult2 );
		telco.general.gen.TelNrHelper.write(out,s.sender);
		out.write_longlong(s.time);
		telco.general.gen.TelNrArrayHelper.write(out,s.reciever);
	}
}
