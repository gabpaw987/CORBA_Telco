package telco.general.gen;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "IPrivateTelco".
 *
 * @author JacORB IDL compiler V 3.2, 07-Dec-2012
 * @version generated at 08.04.2013 20:08:34
 */

public class IPrivateTelcoPOATie
	extends IPrivateTelcoPOA
{
	private IPrivateTelcoOperations _delegate;

	private POA _poa;
	public IPrivateTelcoPOATie(IPrivateTelcoOperations delegate)
	{
		_delegate = delegate;
	}
	public IPrivateTelcoPOATie(IPrivateTelcoOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
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
	public IPrivateTelcoOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(IPrivateTelcoOperations delegate)
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
	public boolean sendExplicit(java.lang.String suffix, java.lang.String pin, telco.general.gen.Message m) throws telco.general.gen.InvalidLoginException,telco.general.gen.DeliverException
	{
		return _delegate.sendExplicit(suffix,pin,m);
	}

	public boolean deliverExplicit(telco.general.gen.Message m, org.omg.CosTransactions.Control control_) throws telco.general.gen.DeliverException
	{
		return _delegate.deliverExplicit(m,control_);
	}

	public boolean send(java.lang.String suffix, java.lang.String pin, telco.general.gen.Message m) throws telco.general.gen.InvalidLoginException,telco.general.gen.DeliverException
	{
		return _delegate.send(suffix,pin,m);
	}

	public boolean deliver(telco.general.gen.Message m) throws telco.general.gen.DeliverException
	{
		return _delegate.deliver(m);
	}

	public boolean logout(java.lang.String suffix, java.lang.String pin) throws telco.general.gen.InvalidLoginException
	{
		return _delegate.logout(suffix,pin);
	}

	public java.lang.String getPrefix()
	{
		return _delegate.getPrefix();
	}

	public telco.general.gen.Message[] login(java.lang.String suffix, java.lang.String pin, telco.general.gen.IClient callback) throws telco.general.gen.InvalidLoginException
	{
		return _delegate.login(suffix,pin,callback);
	}

	public java.lang.String getName()
	{
		return _delegate.getName();
	}

}
