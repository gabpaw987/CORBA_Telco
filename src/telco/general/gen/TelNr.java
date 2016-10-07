package telco.general.gen;

/**
 * Generated from IDL struct "TelNr".
 *
 * @author JacORB IDL compiler V 3.2, 07-Dec-2012
 * @version generated at 08.04.2013 20:08:34
 */

public final class TelNr
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public TelNr(){}
	public java.lang.String prefix = "";
	public java.lang.String suffix = "";
	public TelNr(java.lang.String prefix, java.lang.String suffix)
	{
		this.prefix = prefix;
		this.suffix = suffix;
	}
}
