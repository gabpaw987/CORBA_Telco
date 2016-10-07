package telco.general.gen;


/**
 * Generated from IDL exception "DeliverException".
 *
 * @author JacORB IDL compiler V 3.2, 07-Dec-2012
 * @version generated at 08.04.2013 20:08:34
 */

public abstract class DeliverExceptionHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(DeliverExceptionHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_exception_tc(telco.general.gen.DeliverExceptionHelper.id(),"DeliverException",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("reason", org.omg.CORBA.ORB.init().create_string_tc(0), null)});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final telco.general.gen.DeliverException s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static telco.general.gen.DeliverException extract (final org.omg.CORBA.Any any)
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
		return "IDL:telco/general/gen/DeliverException:1.0";
	}
	public static telco.general.gen.DeliverException read (final org.omg.CORBA.portable.InputStream in)
	{
		String id = in.read_string();
		if (!id.equals(id())) throw new org.omg.CORBA.MARSHAL("wrong id: " + id);
		java.lang.String x0;
		x0=in.read_string();
		final telco.general.gen.DeliverException result = new telco.general.gen.DeliverException(id, x0);
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final telco.general.gen.DeliverException s)
	{
		out.write_string(id());
		java.lang.String tmpResult4 = s.reason;
out.write_string( tmpResult4 );
	}
}
