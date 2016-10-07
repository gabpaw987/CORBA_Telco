package telco.general.gen;


/**
 * Generated from IDL interface "IPublicTelco".
 *
 * @author JacORB IDL compiler V 3.2, 07-Dec-2012
 * @version generated at 08.04.2013 20:08:34
 */

public abstract class IPublicTelcoPOA
	extends org.omg.PortableServer.Servant
	implements org.omg.CORBA.portable.InvokeHandler, telco.general.gen.IPublicTelcoOperations
{
	static private final java.util.HashMap<String,Integer> m_opsHash = new java.util.HashMap<String,Integer>();
	static
	{
		m_opsHash.put ( "deliverExplicit", Integer.valueOf(0));
		m_opsHash.put ( "deliver", Integer.valueOf(1));
		m_opsHash.put ( "getPrefix", Integer.valueOf(2));
		m_opsHash.put ( "getName", Integer.valueOf(3));
	}
	private String[] ids = {"IDL:telco/general/gen/IPublicTelco:1.0"};
	public telco.general.gen.IPublicTelco _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		telco.general.gen.IPublicTelco __r = telco.general.gen.IPublicTelcoHelper.narrow(__o);
		return __r;
	}
	public telco.general.gen.IPublicTelco _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		telco.general.gen.IPublicTelco __r = telco.general.gen.IPublicTelcoHelper.narrow(__o);
		return __r;
	}
	public org.omg.CORBA.portable.OutputStream _invoke(String method, org.omg.CORBA.portable.InputStream _input, org.omg.CORBA.portable.ResponseHandler handler)
		throws org.omg.CORBA.SystemException
	{
		org.omg.CORBA.portable.OutputStream _out = null;
		// do something
		// quick lookup of operation
		java.lang.Integer opsIndex = (java.lang.Integer)m_opsHash.get ( method );
		if ( null == opsIndex )
			throw new org.omg.CORBA.BAD_OPERATION(method + " not found");
		switch ( opsIndex.intValue() )
		{
			case 0: // deliverExplicit
			{
			try
			{
				telco.general.gen.Message _arg0=telco.general.gen.MessageHelper.read(_input);
				org.omg.CosTransactions.Control _arg1=org.omg.CosTransactions.ControlHelper.read(_input);
				_out = handler.createReply();
				_out.write_boolean(deliverExplicit(_arg0,_arg1));
			}
			catch(telco.general.gen.DeliverException _ex0)
			{
				_out = handler.createExceptionReply();
				telco.general.gen.DeliverExceptionHelper.write(_out, _ex0);
			}
				break;
			}
			case 1: // deliver
			{
			try
			{
				telco.general.gen.Message _arg0=telco.general.gen.MessageHelper.read(_input);
				_out = handler.createReply();
				_out.write_boolean(deliver(_arg0));
			}
			catch(telco.general.gen.DeliverException _ex0)
			{
				_out = handler.createExceptionReply();
				telco.general.gen.DeliverExceptionHelper.write(_out, _ex0);
			}
				break;
			}
			case 2: // getPrefix
			{
				_out = handler.createReply();
				java.lang.String tmpResult5 = getPrefix();
_out.write_string( tmpResult5 );
				break;
			}
			case 3: // getName
			{
				_out = handler.createReply();
				java.lang.String tmpResult6 = getName();
_out.write_string( tmpResult6 );
				break;
			}
		}
		return _out;
	}

	public String[] _all_interfaces(org.omg.PortableServer.POA poa, byte[] obj_id)
	{
		return ids;
	}
}
