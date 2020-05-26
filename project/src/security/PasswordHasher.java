package security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * A class that handles password Hashing.
 */

public class PasswordHasher {
	
	/**
	 * Hashes a String.
	 * @param password
	 * @return String
	 * @throws NoSuchAlgorithmException
	 */
	
	public String hashPassword(String password) {
		try {
		MessageDigest md = MessageDigest.getInstance("MD5"); // Change MD5 to any Hashing Algorithm.
		md.update(password.getBytes());
		byte[] b = md.digest();
		StringBuffer sb = new StringBuffer();
		for(byte b1: b) {
			sb.append(Integer.toHexString(b1 & 0xff).toString());
		}
		
		return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
