package org.ignou.pgdis.hybrid.encrypt;

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

public class StartEncryption {

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

	public static void initEncryptData() throws IOException, GeneralSecurityException, Exception {
		System.out.println("Encryption started...");
		StartEncryption startEnc = new StartEncryption();

		String secretKeyPath = Path.secretKeyPath;
		String filesPath = Path.filesPath;
		String keyPairPath = Path.keyPairPath;
		String messagePath = Path.messagePath;

		File originalKeyFile = new File(secretKeyPath + "secretKey");
		File encryptedKeyFile = new File(filesPath + "EncryptedFiles/encryptedSecretKey");
		new EncryptKey(startEnc.getPublic(keyPairPath + "publicKey_user2", "RSA"), originalKeyFile, encryptedKeyFile,
				"RSA");

		File originalFile = new File(messagePath + "message.txt");
		File encryptedFile = new File(filesPath + "EncryptedFiles/encryptedFile");
		new EncryptData(originalFile, encryptedFile, startEnc.getSecretKey(secretKeyPath + "secretKey", "AES"), "AES");
		System.out.println("Done.");
	}
}
