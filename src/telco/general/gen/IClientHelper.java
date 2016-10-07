package telco.general.gen;


/**
 * Generated from IDL interface "IClient".
 *
 * @author JacORB IDL compiler V 3.2, 07-Dec-2012
 * @version generated at 08.04.2013 20:08:34
 */

public abstract class IClientHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(IClientHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_interface_tc("IDL:telco/general/gen/IClient:1.0", "IClient");
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final telco.general.gen.IClient s)
	{
			any.insert_Object(s);
	}
	public static telco.general.gen.IClient extract(final org.omg.CORBA.Any any)
	{
		return narrow(any.extract_Object()) ;
	}
	public static String id()
	{
		return "IDL:telco/general/gen/IClient:1.0";
	}
	public static IClient read(final org.omg.CORBA.portable.InputStream in)
	{
		return narrow(in.read_Object(telco.general.gen._IClientStub.class));
	}
	public static void write(final org.omg.CORBA.portable.OutputStream _out, final telco.general.gen.IClient s)
	{
		_out.write_Object(s);
	}
	public static telco.general.gen.IClient narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof telco.general.gen.IClient)
		{
			return (telco.general.gen.IClient)obj;
		}
		else if (obj._is_a("IDL:telco/general/gen/IClient:1.0"))
		{
			telco.general.gen._IClientStub stub;
			stub = new telco.general.gen._IClientStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
		else
		{
			throw new org.omg.CORBA.BAD_PARAM("Narrow failed");
		}
	}
	public static telco.general.gen.IClient unchecked_narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof telco.general.gen.IClient)
		{
			return (telco.general.gen.IClient)obj;
		}
		else
		{
			telco.general.gen._IClientStub stub;
			stub = new telco.general.gen._IClientStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
	}
}
