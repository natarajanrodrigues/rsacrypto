/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifpb.seguranca.rsacripto;

import ifpb.seguranca.rsacripto.exception.DecryptException;
import ifpb.seguranca.rsacripto.exception.NotPrimeException;
import ifpb.seguranca.rsacripto.gui.MainScreen;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author natarajan
 */
public final class RSACrypto {
    private BigInteger p;
    private BigInteger q;
    private BigInteger N;
    private BigInteger z;
    private BigInteger e;
    private BigInteger d;
    private int        bitlength = 1024;
//    private int        bitlength = 64;
    private Random     r;
 
    private static BigInteger DOIS = new BigInteger("2");
    private static BigInteger TRES = new BigInteger("3");
    private static BigInteger ZERO = new BigInteger("0");
    
    public RSACrypto() {
        autoCreateVariables();
    }
    
    public RSACrypto(int bitlength) {
        this.bitlength = bitlength;
        autoCreateVariables();
    }
 
    public RSACrypto(BigInteger e, BigInteger d, BigInteger N) {
        this.e = e;
        this.d = d;
        this.N = N;
    }
    
    public RSACrypto(BigInteger p, BigInteger q) throws NotPrimeException {
        createVariables(p, q);
    }
    
    public void autoCreateVariables(){
        r = new Random();
        p = BigInteger.probablePrime(bitlength, r);
        q = BigInteger.probablePrime(bitlength, r);
        N = p.multiply(q);
        z = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        e = BigInteger.probablePrime(bitlength / 2, r);
        
        // e tem que ser menor que z e o MDC 
        while (z.gcd(e).compareTo(BigInteger.ONE) > 0 && e.compareTo(z) < 0) {
            e.add(BigInteger.ONE);  // incrementa e até encontrar um número que satisfaça
        }
        
        /**
         *      d = (1/e) mod z
         *      ao mesmo tempo em que 
         *      e = (1/d) mod z
         */
        d = e.modInverse(z);
    }
    
    public void createVariables(BigInteger p, BigInteger q) throws NotPrimeException{
        
        this.p = p;
        this.q = q;
        
        
        if (p.compareTo(BigInteger.valueOf(Integer.MAX_VALUE)) > 0 || q.compareTo(BigInteger.valueOf(Integer.MAX_VALUE)) > 0) 
            throw new IllegalArgumentException("Um dos valores informados "
                    + "ultrapassa o limite permitido. Maior número permitido é " + Integer.MAX_VALUE); 
        
        //verificar se são primos
        if (!isPrime(p)) {
            throw new NotPrimeException(p);
        }
        
        if (!isPrime(q)) {
            throw new NotPrimeException(q);
        }
        
//        if (!p.isProbablePrime(1024)) {
//            throw new NotPrimeException(p);
//        }
//        
//        if (!q.isProbablePrime(1024)) {
//            throw new NotPrimeException(q);
//        }
        
        N = p.multiply(q);
        z = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
//        e = BigInteger.probablePrime(bitlength / 2, r);
//        e = p.divide(DOIS);
//        this.e = new BigInteger("3");
        this.e = z.subtract(BigInteger.ONE);
        
        // e tem que ser menor que z e o MDC 
        while ((z.gcd(e).compareTo(BigInteger.ONE) > 0 && e.compareTo(z) < 0 && !isPrime(e)) ||  z.gcd(e).equals(e)) {
//            e = e.add(BigInteger.ONE);  // incrementa e até encontrar um número que satisfaça
            e = e.subtract(BigInteger.ONE);  // incrementa e até encontrar um número que satisfaça
            System.out.println(z.gcd(e).compareTo(BigInteger.ONE));
        }
        
        /**
         *      d = (1/e) mod z
         *      ao mesmo tempo em que 
         *      e = (1/d) mod z
         */
        d = e.modInverse(z);
    }
    
    
//    public static void main(String[] args) {
//        RSACrypto rsa = new RSACrypto();
//        
//        String text = "n";
//        
//        System.out.println("Texto: " + text);
//        System.out.println("Texto em Bytes: "
//                + bytesToString(text.getBytes()));
//        
//        // encrypt
//        byte[] encrypted = rsa.encrypt(text.getBytes());
//        
//        // decrypt
//        byte[] decrypted = rsa.decrypt(encrypted);
//        
//        System.out.println("Texto encriptado em Bytes: " + bytesToString(encrypted));
//       
//        System.out.println("Texto decriptado: " + new String(decrypted));
//    }
 
    public static String bytesToString(byte[] encrypted) {
        String result = "";
        for (byte b : encrypted) {
            result += Byte.toString(b);
        }
        return result;
    }
 
    // Encrypt message
    public byte[] encrypt(byte[] message) {
        return (new BigInteger(message)).modPow(e, N).toByteArray();
    }
 
    // Decrypt message
    public byte[] decrypt(byte[] message) {
        return (new BigInteger(message)).modPow(d, N).toByteArray();
    }
    
    // Encrypt message
    public List<BigInteger> encryptString (String mensage) {
        
        List<BigInteger> resultList = new ArrayList<>();
                
        for (int i = 0; i < mensage.length(); i++) {
            long charAt = (long) mensage.charAt(i);
            String s = Long.toString(charAt);
            byte[] encrypt = encrypt(s.getBytes());
//            System.out.println("B1: " + bytesToString(encrypt));
//            System.out.println("B2: " + bytesToString(new BigInteger(encrypt).toByteArray()));
            resultList.add(new BigInteger(encrypt));
        }
        
        return resultList;
    }
    
    public String decrypt(List<BigInteger> encryptedMensage) throws DecryptException {    

        try {
            
            StringBuilder sb = new StringBuilder();
                
            for (int i = 0; i < encryptedMensage.size(); i++) {

                byte[] toByteArray = encryptedMensage.get(i).toByteArray();
                byte[] decrypt = decrypt(toByteArray);
                String bytesToString = new String(decrypt);

                char letter = (char) Long.parseLong(bytesToString);
                sb.append(letter);

            }

            return sb.toString();
            
        } catch (Exception e) {
            Logger.getLogger(RSACrypto.class.getName()).log(Level.SEVERE, null, e);
            throw new DecryptException();
        } 
        
    }

    public BigInteger getN() {
        return N;
    }

    public BigInteger getE() {
        return e;
    }

    public BigInteger getD() {
        return d;
    }

    public BigInteger getP() {
        return p;
    }

    public BigInteger getQ() {
        return q;
    }

    public BigInteger getZ() {
        return z;
    }

    public int getBitlength() {
        return bitlength;
    }
    
    
    public String toStringEncryptedArray(List<BigInteger> encryptedMensage) {
        
        StringBuilder sb = new StringBuilder();
        
        for (BigInteger i : encryptedMensage) {
            sb.append(i).append("\n");
        }
        
        return sb.toString();
        
    }
    
    public static boolean isPrime(BigInteger num) {
        
        if (num.compareTo(DOIS) < 0) return false;
        if (num.equals(DOIS)) return true;
        
        
        if (num.mod(DOIS).equals(ZERO)) return false;
        
        for (int i = 3; i * i <= num.intValue(); i += 2)
//            if (num % i == 0) return false;
            if (num.intValue() % i == 0) return false;
        return true;
    }
    
}
