package org.ignou.pgdis.servlets;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import javax.crypto.NoSuchPaddingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ignou.pgdis.hybrid.decrypt.StartDecryption;
import org.ignou.pgdis.hybrid.encrypt.StartEncryption;
import org.ignou.pgdis.keypair.GenerateKeys;
import org.ignou.pgdis.onekey.GenerateSymmetricKey;

@WebServlet("/init")
public class InitServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String button = request.getParameter("button");

		if ("generateKeys".equals(button)) {
			try {
				GenerateKeys.initPublicPrivateKeys();
			} catch (NoSuchAlgorithmException | NoSuchProviderException | IOException e) {
				e.printStackTrace();
			}
		} else if ("generateSecretKey".equals(button)) {
			try {
				GenerateSymmetricKey.initSymmetricKey();
			} catch (NoSuchAlgorithmException | NoSuchPaddingException | IOException e) {
				e.printStackTrace();
			}
		} else if ("encryptData".equals(button)) {
			try {
				StartEncryption.initEncryptData();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if ("decryptData".equals(button)) {
			try {
				StartDecryption.initDecryptData();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("no button");
		}

		request.getRequestDispatcher("index.jsp").forward(request, response);
	}
}
