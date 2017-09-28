///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package ifpb.seguranca.rsacripto;
//
//import java.math.BigInteger;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// *
// * @author natarajan
// */
//public class RSACrypto2 {
//    
//    public static void main(String[] args) {
//        
//        String palavra = "nata";
//        
////        RSACrypto rsa = new RSACrypto(16);
//        RSACrypto rsa = new RSACrypto(new BigInteger("251"),new BigInteger("1001459339"),new BigInteger("1890060467"));
//        
//        System.out.println("E: " + rsa.getE());
//        System.out.println("D: " + rsa.getD());
//        System.out.println("N: " + rsa.getN());
//        System.out.println("p: " + rsa.getP());
//        System.out.println("q: " + rsa.getQ());
//        System.out.println("z: " + rsa.getZ());
//        
//        
////        List<BigInteger> encryptString = dsencryptString(palavra, rsa);
//        List<BigInteger> encryptString = rsa.encryptString(palavra);
//        
//        for (int i = 0; i < palavra.length(); i++) {
//            System.out.println(encryptString.get(i) + " ");   
//        }
//        
//        String decrypt = rsa.decrypt(encryptString);
//        System.out.println("Decrypted: " +  decrypt);
//        
////        for (int i = 0; i < palavra.length(); i++) {
////            System.out.println(palavraDecriptada.get(i) + " ->  " + decrypt(palavraDecriptada.get(i), d, N));
////        }
//        
//    }
//    
//    
//    
//    
//}
//    
//    
//     