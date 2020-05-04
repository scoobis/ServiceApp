package security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class PasswordHasher {

	/**
	 * Generates a hashed version of any given String.
	 * @param data The string you want to hash
	 * @param algorithm
	 * @param salt
	 * @return String a hased version of the data entered
	 * @throws NoSuchAlgorithmException Is being cast if no hashing algorithm exists. 
	 */
	private String generateHash(String data, String algorithm, byte[] salt) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance(algorithm);
		md.reset();
		md.update(salt);
		
		byte[] hash = md.digest(data.getBytes());
		return bytesToStringHex(hash);
	}
	
	private final char[] hexArray = "0123456789ABCDEF".toCharArray(); // hexArray
	
	/**
	 * Converts bytes to a String.
	 * @param bytes
	 * @return String
	 */
	
	private String bytesToStringHex(byte[] bytes) {
		char[] hexChars = new char[bytes.length * 2];
		for (int i = 0; i < bytes.length; i++ ) {
			int v = bytes[i] & 0xFF;
			hexChars[i * 2] = hexArray[v >>> 4];
			hexChars[i * 2] = hexArray[v & 0x0F];
		}
		
		return new String(hexChars);
	}
	
	/**
	 * Generates a 20 bytes large salt for password hashing.
	 * @return byte[] with the generated salt.
	 */
	
	private byte[] generateSalt() {
		byte[] bytes = new byte[20];
		SecureRandom random = new SecureRandom();
		random.nextBytes(bytes);
		return bytes;
	}
	
	public String generateHashWithMD5(String data) throws NoSuchAlgorithmException {
		String algorithm = "MD5";
		byte[] salt = generateSalt();
		return generateHash(data, algorithm, salt);
	}
	
	
}
