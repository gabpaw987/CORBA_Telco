package telco.general.gen;

/**
 * Generated from IDL struct "Message".
 *
 * @author JacORB IDL compiler V 3.2, 07-Dec-2012
 * @version generated at 08.04.2013 20:08:34
 */

public final class Message
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public Message(){}
	public java.lang.String text = "";
	public telco.general.gen.TelNr sender;
	public long time;
	public telco.general.gen.TelNr[] reciever;
	public Message(java.lang.String text, telco.general.gen.TelNr sender, long time, telco.general.gen.TelNr[] reciever)
	{
		this.text = text;
		this.sender = sender;
		this.time = time;
		this.reciever = reciever;
	}
}
