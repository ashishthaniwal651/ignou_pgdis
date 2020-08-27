package org.ignou.pgdis.keypair;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;

import org.ignou.pgdis.modules.Path;

public class GenerateKeys {
	private KeyPairGenerator keyGen;
	private KeyPair pair;
	private PrivateKey privateKey;
	private PublicKey publicKey;

	public GenerateKeys(int keylength) throws NoSuchAlgorithmException, NoSuchProviderException {
		this.keyGen = KeyPairGenerator.getInstance("RSA");
		this.keyGen.initialize(keylength);
	}

	public void createKeys() {
		this.pair = this.keyGen.generateKeyPair();
		this.privateKey = pair.getPrivate();
		this.publicKey = pair.getPublic();
	}

	public PrivateKey getPrivateKey() {
		return this.privateKey;
	}

	public PublicKey getPublicKey() {
		return this.publicKey;
	}

	public void writeToFile(String path, byte[] key) throws IOException {
		File f = new File(path);
		f.getParentFile().mkdirs();

		FileOutputStream fos = new FileOutputStream(f);
		fos.write(key);
		fos.flush();
		fos.close();
	}

	public static void initPublicPrivateKeys() throws NoSuchAlgorithmException, NoSuchProviderException, IOException {
		System.out.println("Generating Public-Private keys....");

		GenerateKeys gk_user1;
		GenerateKeys gk_user2;
		String path = Path.keyPairPath;

		gk_user1 = new GenerateKeys(1024);
		gk_user1.createKeys();
		gk_user1.writeToFile(path + "publicKey_user1", gk_user1.getPublicKey().getEncoded());
		gk_user1.writeToFile(path + "privateKey_user1", gk_user1.getPrivateKey().getEncoded());

		gk_user2 = new GenerateKeys(1024);
		gk_user2.createKeys();
		gk_user2.writeToFile(path + "publicKey_user2", gk_user2.getPublicKey().getEncoded());
		gk_user2.writeToFile(path + "privateKey_user2", gk_user2.getPrivateKey().getEncoded());
		System.out.println("Done.");
	}
}
