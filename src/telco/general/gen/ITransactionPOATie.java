package telco.general.gen;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "ITransaction".
 *
 * @author JacORB IDL compiler V 3.2, 07-Dec-2012
 * @version generated at 08.04.2013 20:08:34
 */

public class ITransactionPOATie
	extends ITransactionPOA
{
	private ITransactionOperations _delegate;

	private POA _poa;
	public ITransactionPOATie(ITransactionOperations delegate)
	{
		_delegate = delegate;
	}
	public ITransactionPOATie(ITransactionOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
	public telco.general.gen.ITransaction _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		telco.general.gen.ITransaction __r = telco.general.gen.ITransactionHelper.narrow(__o);
		return __r;
	}
	public telco.general.gen.ITransaction _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		telco.general.gen.ITransaction __r = telco.general.gen.ITransactionHelper.narrow(__o);
		return __r;
	}
	public ITransactionOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(ITransactionOperations delegate)
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
	public void rollback() throws org.omg.CosTransactions.HeuristicHazard,org.omg.CosTransactions.HeuristicMixed,org.omg.CosTransactions.HeuristicCommit
	{
_delegate.rollback();
	}

	public void commit() throws org.omg.CosTransactions.NotPrepared,org.omg.CosTransactions.HeuristicHazard,org.omg.CosTransactions.HeuristicMixed,org.omg.CosTransactions.HeuristicRollback
	{
_delegate.commit();
	}

	public void useMessage(telco.general.gen.Message m)
	{
_delegate.useMessage(m);
	}

	public org.omg.CosTransactions.Vote prepare() throws org.omg.CosTransactions.HeuristicHazard,org.omg.CosTransactions.HeuristicMixed
	{
		return _delegate.prepare();
	}

	public void commit_one_phase() throws org.omg.CosTransactions.HeuristicHazard
	{
_delegate.commit_one_phase();
	}

	public void forget()
	{
_delegate.forget();
	}

	public void useMessageExplicit(telco.general.gen.Message m, org.omg.CosTransactions.Control control_)
	{
_delegate.useMessageExplicit(m,control_);
	}

}
