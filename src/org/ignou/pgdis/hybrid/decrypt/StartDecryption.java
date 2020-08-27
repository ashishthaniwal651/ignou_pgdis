package org.ignou.pgdis.hybrid.decrypt;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.spec.SecretKeySpec;

import org.ignou.pgdis.modules.Path;

public class StartDecryption {

	public PrivateKey getPrivate(String filename, String algorithm) throws Exception {
		byte[] keyBytes = Files.readAllBytes(new File(filename).toPath());
		PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory kf = KeyFactory.getInstance(algorithm);
		return kf.generatePrivate(spec);
	}

	public PublicKey getPublic(String filename, String algorithm) throws Exception {
		byte[] keyBytes = Files.readAllBytes(new File(filename).toPath());
		X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
		KeyFactory kf = KeyFactory.getInstance(algorithm);
		return kf.generatePublic(spec);
	}

	public SecretKeySpec getSecretKey(String filename, String algorithm) throws IOException {
		byte[] keyBytes = Files.readAllBytes(new File(filename).toPath());
		return new SecretKeySpec(keyBytes, algorithm);
	}

	public static void initDecryptData() throws IOException, GeneralSecurityException, Exception {
		System.out.println("Decryption started...");
		StartDecryption startEnc = new StartDecryption();

		String filesPath = Path.filesPath;
		String keyPairPath = Path.keyPairPath;
		File encryptedKeyReceived = new File(filesPath + "EncryptedFiles/encryptedSecretKey");
		File decreptedKeyFile = new File(filesPath + "DecryptedFiles/SecretKey");
		new DecryptKey(startEnc.getPrivate(keyPairPath + "privateKey_user2", "RSA"), encryptedKeyReceived,
				decreptedKeyFile, "RSA");

		File encryptedFileReceived = new File(filesPath + "EncryptedFiles/encryptedFile");
		File decryptedFile = new File(filesPath + "DecryptedFiles/decryptedFile");
		new DecryptData(encryptedFileReceived, decryptedFile,
				startEnc.getSecretKey(filesPath + "DecryptedFiles/SecretKey", "AES"), "AES");
		System.out.println("Done.");
	}
}
