package telco.general.gen;


/**
 * Generated from IDL interface "IPrivateTelco".
 *
 * @author JacORB IDL compiler V 3.2, 07-Dec-2012
 * @version generated at 08.04.2013 20:08:34
 */

public abstract class IPrivateTelcoHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(IPrivateTelcoHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_interface_tc("IDL:telco/general/gen/IPrivateTelco:1.0", "IPrivateTelco");
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final telco.general.gen.IPrivateTelco s)
	{
			any.insert_Object(s);
	}
	public static telco.general.gen.IPrivateTelco extract(final org.omg.CORBA.Any any)
	{
		return narrow(any.extract_Object()) ;
	}
	public static String id()
	{
		return "IDL:telco/general/gen/IPrivateTelco:1.0";
	}
	public static IPrivateTelco read(final org.omg.CORBA.portable.InputStream in)
	{
		return narrow(in.read_Object(telco.general.gen._IPrivateTelcoStub.class));
	}
	public static void write(final org.omg.CORBA.portable.OutputStream _out, final telco.general.gen.IPrivateTelco s)
	{
		_out.write_Object(s);
	}
	public static telco.general.gen.IPrivateTelco narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof telco.general.gen.IPrivateTelco)
		{
			return (telco.general.gen.IPrivateTelco)obj;
		}
		else if (obj._is_a("IDL:telco/general/gen/IPrivateTelco:1.0"))
		{
			telco.general.gen._IPrivateTelcoStub stub;
			stub = new telco.general.gen._IPrivateTelcoStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
		else
		{
			throw new org.omg.CORBA.BAD_PARAM("Narrow failed");
		}
	}
	public static telco.general.gen.IPrivateTelco unchecked_narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof telco.general.gen.IPrivateTelco)
		{
			return (telco.general.gen.IPrivateTelco)obj;
		}
		else
		{
			telco.general.gen._IPrivateTelcoStub stub;
			stub = new telco.general.gen._IPrivateTelcoStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
	}
}
