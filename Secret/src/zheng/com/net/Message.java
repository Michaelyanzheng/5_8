/**
 * 
 */
package zheng.com.net;

/**
 * @author michael
 *
 */
public class Message {
	
	private String msg ;
	private String phone_md5;
	private String msgId;
	
	public Message(String msg,String phone_md5,String msgId){
		this.msg = msg;
		this.phone_md5 = phone_md5;
		this.msgId = msgId;
	}

	public String getMsg() {
		return msg;
	}

	public String getPhone_md5() {
		return phone_md5;
	}

	public String getMsgId() {
		return msgId;
	}
	
}
