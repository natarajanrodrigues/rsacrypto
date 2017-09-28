///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package ifpb.seguranca.rsacripto;
//
///**
// *
// * @author natarajan
// */
//public class CryptoKeys {
//
//    
//    
//    private int p;
//    private int q;
//    private int n;
//    private int z;
//    private int d;
//    private int e;
//    
//    public void getN(){
//        
//        if ((p != 0) && (q != 0)) {
//            this.n = p * q;
//        }
//    }
//    
////    public static int getZ(){
////        
////        if (n != 0)
////        
////    }
////    
////    public static int getD(){
////    }
////    
////    public static int getE() {
////        
////    }
//    
//    public static void main(String[] args) {
//        
//        getKeys();
//        
//    }
//    
//    
//    public static void getKeys() {
//        
//        int p, q, n, z, d, e;
//        // escolher p e q, números primos extensos
//        p = 3;
//        q = 11;
//        
//        //calcula n e z
//        n = p * q;
//        z = (p - 1) * (q - 1);
//        
//        // escolher d, tal que d e z sejam primos entre si
//        d = z / 2 ;
//        do {
//            d++;
//        } while (!primosEntreSi(d, z)); // acha d e sai do loop
//        
//        // agora encontrar 'e'
//        e = d + 1;
//        
//        int modEDZ;
//        do {
//            modEDZ = (e * d) % z;
//            if (modEDZ != 1) {
//                e++;
//            } // else achou e
//        } while (modEDZ != 1);
//        
//        System.out.println("As chaves são: ");
//        System.out.println("Z: " + z);
//        System.out.println("E: " + e);
//        System.out.println("D: " + d);
//        System.out.println("N: " + n);
//        
//        
//        String encrypt = encrypt("Nata", e, n);
//        System.out.println("crypt: Natarajan: " + encrypt);
//        
//        System.out.println("Decrypt: " + decrypt(encrypt, d, n));
//        
//        
//    }
//    
//    public static boolean primosEntreSi(int a, int b) {
//        
//        int dividendo = a;
//        int divisor = b;
//        
//        int resto;
//        do {
//            resto = dividendo % divisor;
//            if ( resto != 0) {
//                dividendo = divisor;
//                divisor = resto;
//            }
//        } while (resto != 0);
//        
//        if (divisor == 1)
//            return true;
//        return false;
//        
//    }
//    
//    
//    public static String encrypt(String mensagem, int e, int n){
//        
//        char[] cypher = new char[mensagem.length()];
//        String result;
//        
//        for(int i = 0; i < mensagem.length(); i++) {
//            char charAt = mensagem.charAt(i);
//
//            int charAtInt = charToInt(charAt);
//            double pow = Math.pow( charAtInt, e);
//            
//            double name = (pow) % n;
//            char intToChar = intToChar((int) name);
//            
//            cypher[i] = intToChar;
//        }
//        
//        result = new String(cypher);
//        
//        return result;
//    }
//    
//    public static String decrypt(String mensagem, int d, int n){
//        
//        char[] cypher = new char[mensagem.length()];
//        String result;
//        
//        for(int i = 0; i < mensagem.length(); i++) {
//            char charAt = mensagem.charAt(i);
//            
//            int charAtInt = charToInt(charAt);
//            double pow = Math.pow( charAtInt, d);
//            
////            int name = (int) (Math.pow((double) charAtInt, (double) d)) % n;
//            double name = (pow) % n;
//            cypher[i] = (char) intToChar((int) name);
//        }
//        
//        result = new String(cypher);
//        
//        return result;
//    }
//    
//    
//    public static int charToInt(char aChar) {
//        return ((int) aChar) - 64;
//    }
//    
//    public static char intToChar(int intValue) {
//        
//        return (char) (intValue + 64);
//    }
//    
//    
//}
