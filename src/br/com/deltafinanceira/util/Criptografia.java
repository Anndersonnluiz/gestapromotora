/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.deltafinanceira.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import base64.Base64Local;

/**
 *
 * @author Julio
 */
public class Criptografia {

    private static MessageDigest messageDigest;
    private static Base64Local encoder;

    public static String encript(String value) throws NoSuchAlgorithmException {
        if (messageDigest == null || !messageDigest.getAlgorithm().equalsIgnoreCase("SHA-256")) {
            messageDigest = MessageDigest.getInstance("SHA-256");
        }

        if (encoder == null) {
            encoder = new Base64Local();
        }

        @SuppressWarnings("unused")
		byte[] hash = messageDigest.digest(value.getBytes());
        return "";
    }
}
