package telco.general.gen;


/**
 * Generated from IDL interface "IPrivateTelco".
 *
 * @author JacORB IDL compiler V 3.2, 07-Dec-2012
 * @version generated at 08.04.2013 20:08:34
 */

public abstract class IPrivateTelcoPOA
	extends org.omg.PortableServer.Servant
	implements org.omg.CORBA.portable.InvokeHandler, telco.general.gen.IPrivateTelcoOperations
{
	static private final java.util.HashMap<String,Integer> m_opsHash = new java.util.HashMap<String,Integer>();
	static
	{
		m_opsHash.put ( "sendExplicit", Integer.valueOf(0));
		m_opsHash.put ( "deliverExplicit", Integer.valueOf(1));
		m_opsHash.put ( "send", Integer.valueOf(2));
		m_opsHash.put ( "deliver", Integer.valueOf(3));
		m_opsHash.put ( "logout", Integer.valueOf(4));
		m_opsHash.put ( "getPrefix", Integer.valueOf(5));
		m_opsHash.put ( "login", Integer.valueOf(6));
		m_opsHash.put ( "getName", Integer.valueOf(7));
	}
	private String[] ids = {"IDL:telco/general/gen/IPrivateTelco:1.0","IDL:telco/general/gen/IPublicTelco:1.0"};
	public telco.general.gen.IPrivateTelco _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		telco.general.gen.IPrivateTelco __r = telco.general.gen.IPrivateTelcoHelper.narrow(__o);
		return __r;
	}
	public telco.general.gen.IPrivateTelco _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		telco.general.gen.IPrivateTelco __r = telco.general.gen.IPrivateTelcoHelper.narrow(__o);
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
			case 0: // sendExplicit
			{
			try
			{
				java.lang.String _arg0=_input.read_string();
				java.lang.String _arg1=_input.read_string();
				telco.general.gen.Message _arg2=telco.general.gen.MessageHelper.read(_input);
				_out = handler.createReply();
				_out.write_boolean(sendExplicit(_arg0,_arg1,_arg2));
			}
			catch(telco.general.gen.InvalidLoginException _ex0)
			{
				_out = handler.createExceptionReply();
				telco.general.gen.InvalidLoginExceptionHelper.write(_out, _ex0);
			}
			catch(telco.general.gen.DeliverException _ex1)
			{
				_out = handler.createExceptionReply();
				telco.general.gen.DeliverExceptionHelper.write(_out, _ex1);
			}
				break;
			}
			case 1: // deliverExplicit
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
			case 2: // send
			{
			try
			{
				java.lang.String _arg0=_input.read_string();
				java.lang.String _arg1=_input.read_string();
				telco.general.gen.Message _arg2=telco.general.gen.MessageHelper.read(_input);
				_out = handler.createReply();
				_out.write_boolean(send(_arg0,_arg1,_arg2));
			}
			catch(telco.general.gen.InvalidLoginException _ex0)
			{
				_out = handler.createExceptionReply();
				telco.general.gen.InvalidLoginExceptionHelper.write(_out, _ex0);
			}
			catch(telco.general.gen.DeliverException _ex1)
			{
				_out = handler.createExceptionReply();
				telco.general.gen.DeliverExceptionHelper.write(_out, _ex1);
			}
				break;
			}
			case 3: // deliver
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
			case 4: // logout
			{
			try
			{
				java.lang.String _arg0=_input.read_string();
				java.lang.String _arg1=_input.read_string();
				_out = handler.createReply();
				_out.write_boolean(logout(_arg0,_arg1));
			}
			catch(telco.general.gen.InvalidLoginException _ex0)
			{
				_out = handler.createExceptionReply();
				telco.general.gen.InvalidLoginExceptionHelper.write(_out, _ex0);
			}
				break;
			}
			case 5: // getPrefix
			{
				_out = handler.createReply();
				java.lang.String tmpResult15 = getPrefix();
_out.write_string( tmpResult15 );
				break;
			}
			case 6: // login
			{
			try
			{
				java.lang.String _arg0=_input.read_string();
				java.lang.String _arg1=_input.read_string();
				telco.general.gen.IClient _arg2=telco.general.gen.IClientHelper.read(_input);
				_out = handler.createReply();
				telco.general.gen.MessageArrayHelper.write(_out,login(_arg0,_arg1,_arg2));
			}
			catch(telco.general.gen.InvalidLoginException _ex0)
			{
				_out = handler.createExceptionReply();
				telco.general.gen.InvalidLoginExceptionHelper.write(_out, _ex0);
			}
				break;
			}
			case 7: // getName
			{
				_out = handler.createReply();
				java.lang.String tmpResult16 = getName();
_out.write_string( tmpResult16 );
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
