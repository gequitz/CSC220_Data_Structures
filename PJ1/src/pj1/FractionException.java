/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pj1;

/**
 *
 * @author Maria
 */
public class FractionException extends RuntimeException{
    public FractionException()
    {
        this("");
    }
    public FractionException(String errorMsg){
        super(errorMsg);
    }
}
