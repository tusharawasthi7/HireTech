package hire.tech.utils;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;

import javax.sql.rowset.serial.SerialBlob;

import org.springframework.web.multipart.MultipartFile;

public class Utils {
	public static String generateSalt() {

		final int SALT_LENGTH = 10;
		String saltChars = "abcdefghijklmnopqrstuvwxyzQWERTYUIOPASDFGHJKLMNBVCXZ0123456789";
		StringBuilder strSalt = new StringBuilder(SALT_LENGTH);

		SecureRandom random = new SecureRandom();
		for (int i = 0; i < SALT_LENGTH; i++) {
			int randomIndex = random.nextInt(saltChars.length());
			char character = saltChars.charAt(randomIndex);
			strSalt.append(saltChars.charAt(randomIndex));
		}
		return strSalt.toString();
	}

	public static String generateHash(String inputString) {
		String strHash = "";
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			byte[] hashBytes = messageDigest.digest(inputString.getBytes());
			strHash = bytesToHex(hashBytes);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return strHash;
	}

	private static String bytesToHex(byte[] bytes) {
		StringBuilder hexString = new StringBuilder(2 * bytes.length);
		for (byte b : bytes) {
			String hex = Integer.toHexString(0xff & b);
			if (hex.length() == 1) {
				hexString.append("0");
			}
			hexString.append(hex);
		}
		return hexString.toString();
	}

	public static MultipartFile convertToMultipart(Blob image) {
		byte[] imageBytes = null;
		try {
			imageBytes = image.getBytes(1, (int) image.length());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		MultipartFile multipartImage = new MultiPartImpl(imageBytes, "image.png", "image/png");
		return multipartImage;
	}

	public static Blob convertToblob(MultipartFile image) {
		SerialBlob imageBlob = null;
		try {
			byte[] imagebytes = image.getBytes();
			imageBlob = new SerialBlob(imagebytes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return imageBlob;
	}

	public static String getBase64Image(MultipartFile image) {
		try {
			if (image != null && !image.isEmpty()) {
				byte[] imgBytes = image.getBytes();
				return Base64.getEncoder().encodeToString(imgBytes);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getBase64PdfDownloadLink(MultipartFile pdf) {
		try {
			byte[] pdfBytes = pdf.getBytes();
			String base64Pdf = Base64.getEncoder().encodeToString(pdfBytes);
			return "data:application/pdf;base64," + base64Pdf;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
