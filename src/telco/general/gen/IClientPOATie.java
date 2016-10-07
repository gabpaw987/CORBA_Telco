package telco.general.gen;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "IClient".
 *
 * @author JacORB IDL compiler V 3.2, 07-Dec-2012
 * @version generated at 08.04.2013 20:08:34
 */

public class IClientPOATie
	extends IClientPOA
{
	private IClientOperations _delegate;

	private POA _poa;
	public IClientPOATie(IClientOperations delegate)
	{
		_delegate = delegate;
	}
	public IClientPOATie(IClientOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
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
	public IClientOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(IClientOperations delegate)
	{
		_delegate = delegate;
	}
	public POA _default_POA()
	{
		if (_poa != null)
		{
			return _poa;
		}
		return super._default_POA();
	}
	public boolean onSMS(telco.general.gen.Message m)
	{
		return _delegate.onSMS(m);
	}

	public boolean onSMSExplicit(telco.general.gen.Message m, org.omg.CosTransactions.Control control_)
	{
		return _delegate.onSMSExplicit(m,control_);
	}

}
