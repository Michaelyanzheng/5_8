package zheng.com.tools;  
  
import java.security.MessageDigest;  
import java.security.NoSuchAlgorithmException;  
import java.io.UnsupportedEncodingException;  
  
public class Md5 {  
  
    public static String getMD5Str(String str) {  
        try {
			MessageDigest messageDigest = null;  
  
			messageDigest = MessageDigest.getInstance("MD5");  
  
			messageDigest.reset();  
  
			messageDigest.update(str.getBytes("UTF-8"));  
  
			byte[] byteArray = messageDigest.digest();  
  
			StringBuffer md5StrBuff = new StringBuffer();  
  
			for (int i = 0; i < byteArray.length; i++) {  
			    if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)  
			        md5StrBuff.append("0").append(  
			                Integer.toHexString(0xFF & byteArray[i]));  
			    else  
			        md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));  
			}  
  
			return md5StrBuff.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}  
        return null;
    }  
  
} 