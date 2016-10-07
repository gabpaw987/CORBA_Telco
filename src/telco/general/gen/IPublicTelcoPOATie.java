package telco.general.gen;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "IPublicTelco".
 *
 * @author JacORB IDL compiler V 3.2, 07-Dec-2012
 * @version generated at 08.04.2013 20:08:34
 */

public class IPublicTelcoPOATie
	extends IPublicTelcoPOA
{
	private IPublicTelcoOperations _delegate;

	private POA _poa;
	public IPublicTelcoPOATie(IPublicTelcoOperations delegate)
	{
		_delegate = delegate;
	}
	public IPublicTelcoPOATie(IPublicTelcoOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
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
	public IPublicTelcoOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(IPublicTelcoOperations delegate)
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
	public boolean deliverExplicit(telco.general.gen.Message m, org.omg.CosTransactions.Control control_) throws telco.general.gen.DeliverException
	{
		return _delegate.deliverExplicit(m,control_);
	}

	public boolean deliver(telco.general.gen.Message m) throws telco.general.gen.DeliverException
	{
		return _delegate.deliver(m);
	}

	public java.lang.String getPrefix()
	{
		return _delegate.getPrefix();
	}

	public java.lang.String getName()
	{
		return _delegate.getName();
	}

}
