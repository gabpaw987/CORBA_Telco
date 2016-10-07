package telco.general.gen;


/**
 * Generated from IDL interface "IClient".
 *
 * @author JacORB IDL compiler V 3.2, 07-Dec-2012
 * @version generated at 08.04.2013 20:08:34
 */

public abstract class IClientPOA
	extends org.omg.PortableServer.Servant
	implements org.omg.CORBA.portable.InvokeHandler, telco.general.gen.IClientOperations
{
	static private final java.util.HashMap<String,Integer> m_opsHash = new java.util.HashMap<String,Integer>();
	static
	{
		m_opsHash.put ( "onSMS", Integer.valueOf(0));
		m_opsHash.put ( "onSMSExplicit", Integer.valueOf(1));
	}
	private String[] ids = {"IDL:telco/general/gen/IClient:1.0"};
	public telco.general.gen.IClient _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		telco.general.gen.IClient __r = telco.general.gen.IClientHelper.narrow(__o);
		return __r;
	}
	public telco.general.gen.IClient _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		telco.general.gen.IClient __r = telco.general.gen.IClientHelper.narrow(__o);
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
			case 0: // onSMS
			{
				telco.general.gen.Message _arg0=telco.general.gen.MessageHelper.read(_input);
				_out = handler.createReply();
				_out.write_boolean(onSMS(_arg0));
				break;
			}
			case 1: // onSMSExplicit
			{
				telco.general.gen.Message _arg0=telco.general.gen.MessageHelper.read(_input);
				org.omg.CosTransactions.Control _arg1=org.omg.CosTransactions.ControlHelper.read(_input);
				_out = handler.createReply();
				_out.write_boolean(onSMSExplicit(_arg0,_arg1));
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
