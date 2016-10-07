package telco.general.gen;


/**
 * Generated from IDL interface "ITransaction".
 *
 * @author JacORB IDL compiler V 3.2, 07-Dec-2012
 * @version generated at 08.04.2013 20:08:34
 */

public abstract class ITransactionPOA
	extends org.omg.PortableServer.Servant
	implements org.omg.CORBA.portable.InvokeHandler, telco.general.gen.ITransactionOperations
{
	static private final java.util.HashMap<String,Integer> m_opsHash = new java.util.HashMap<String,Integer>();
	static
	{
		m_opsHash.put ( "rollback", Integer.valueOf(0));
		m_opsHash.put ( "commit", Integer.valueOf(1));
		m_opsHash.put ( "useMessage", Integer.valueOf(2));
		m_opsHash.put ( "prepare", Integer.valueOf(3));
		m_opsHash.put ( "commit_one_phase", Integer.valueOf(4));
		m_opsHash.put ( "forget", Integer.valueOf(5));
		m_opsHash.put ( "useMessageExplicit", Integer.valueOf(6));
	}
	private String[] ids = {"IDL:telco/general/gen/ITransaction:1.0","IDL:omg.org/CosTransactions/TransactionalObject:1.0","IDL:omg.org/CosTransactions/Resource:1.0"};
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
			case 0: // rollback
			{
			try
			{
				_out = handler.createReply();
				rollback();
			}
			catch(org.omg.CosTransactions.HeuristicHazard _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.CosTransactions.HeuristicHazardHelper.write(_out, _ex0);
			}
			catch(org.omg.CosTransactions.HeuristicMixed _ex1)
			{
				_out = handler.createExceptionReply();
				org.omg.CosTransactions.HeuristicMixedHelper.write(_out, _ex1);
			}
			catch(org.omg.CosTransactions.HeuristicCommit _ex2)
			{
				_out = handler.createExceptionReply();
				org.omg.CosTransactions.HeuristicCommitHelper.write(_out, _ex2);
			}
				break;
			}
			case 1: // commit
			{
			try
			{
				_out = handler.createReply();
				commit();
			}
			catch(org.omg.CosTransactions.NotPrepared _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.CosTransactions.NotPreparedHelper.write(_out, _ex0);
			}
			catch(org.omg.CosTransactions.HeuristicHazard _ex1)
			{
				_out = handler.createExceptionReply();
				org.omg.CosTransactions.HeuristicHazardHelper.write(_out, _ex1);
			}
			catch(org.omg.CosTransactions.HeuristicMixed _ex2)
			{
				_out = handler.createExceptionReply();
				org.omg.CosTransactions.HeuristicMixedHelper.write(_out, _ex2);
			}
			catch(org.omg.CosTransactions.HeuristicRollback _ex3)
			{
				_out = handler.createExceptionReply();
				org.omg.CosTransactions.HeuristicRollbackHelper.write(_out, _ex3);
			}
				break;
			}
			case 2: // useMessage
			{
				telco.general.gen.Message _arg0=telco.general.gen.MessageHelper.read(_input);
				_out = handler.createReply();
				useMessage(_arg0);
				break;
			}
			case 3: // prepare
			{
			try
			{
				_out = handler.createReply();
				org.omg.CosTransactions.VoteHelper.write(_out,prepare());
			}
			catch(org.omg.CosTransactions.HeuristicHazard _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.CosTransactions.HeuristicHazardHelper.write(_out, _ex0);
			}
			catch(org.omg.CosTransactions.HeuristicMixed _ex1)
			{
				_out = handler.createExceptionReply();
				org.omg.CosTransactions.HeuristicMixedHelper.write(_out, _ex1);
			}
				break;
			}
			case 4: // commit_one_phase
			{
			try
			{
				_out = handler.createReply();
				commit_one_phase();
			}
			catch(org.omg.CosTransactions.HeuristicHazard _ex0)
			{
				_out = handler.createExceptionReply();
				org.omg.CosTransactions.HeuristicHazardHelper.write(_out, _ex0);
			}
				break;
			}
			case 5: // forget
			{
				_out = handler.createReply();
				forget();
				break;
			}
			case 6: // useMessageExplicit
			{
				telco.general.gen.Message _arg0=telco.general.gen.MessageHelper.read(_input);
				org.omg.CosTransactions.Control _arg1=org.omg.CosTransactions.ControlHelper.read(_input);
				_out = handler.createReply();
				useMessageExplicit(_arg0,_arg1);
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
