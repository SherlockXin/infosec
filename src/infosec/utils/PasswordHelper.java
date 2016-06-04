package infosec.utils;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import sun.security.provider.SHA;
import java.io.IOException;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class PasswordHelper {
	
	public static final String getSaltedMD5Password(String password, Object salt){
		Md5PasswordEncoder md5 = new Md5PasswordEncoder();
		return md5.encodePassword(password, salt);
	}
	

	public static final String getSaltedSHAPassword(String password, Object salt){
		ShaPasswordEncoder sha = new ShaPasswordEncoder();
		return sha.encodePassword(password, salt);
	}
	
	public static final String getBCryptPassword(String password){
		BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
		return bCrypt.encode(password);
		
	} 
	
	/**
	 * BASE64Ω‚√‹
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] decryptBASE64(String key) throws Exception {
		return (new BASE64Decoder()).decodeBuffer(key);
	}

	/**
	 * BASE64º”√‹
	 * 
	 * @param pwd_plaintext
	 * @return
	 * @throws Exception
	 */
	public static String encryptBASE64(String key) throws Exception {
		byte[] key1 = key.getBytes();
		return (new BASE64Encoder()).encode(key1);
	}

	public static void main(String[] args) throws Exception {
				
		String pwd_plaintext = "5038";
		
		//String encoded_password = PasswordHelper.getSaltedSHAPassword(pwd_plaintext, null);
		//String encoded_password = PasswordHelper.getSaltedMD5Password(pwd_plaintext,null);
		
		String encoded_password = encryptBASE64(pwd_plaintext);
		byte[] decoded_password1 = decryptBASE64(encoded_password);
		String decoded_password =new String(decoded_password1);  
		System.out.println(encoded_password);
		System.out.println(decoded_password);
		//System.out.println(encoded_password);

	}
	
}
