/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifpb.seguranca.rsacripto.exception;

/**
 *
 * @author natarajan
 */
public class DecryptException extends Exception {

    public DecryptException() {
        super("Ocorreu um erro na decriptação. Por favor, verifique se os números estão corretos");
    }
    
    
    
}
