/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifpb.seguranca.rsacripto.exception;

import java.math.BigInteger;

/**
 *
 * @author natarajan
 */
public class NotPrimeException extends Exception {

    public NotPrimeException(BigInteger number) {
        super("O número " + number + " não é primo");
    }
    
    
    
}
