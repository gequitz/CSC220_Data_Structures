/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pj2;

public class LispExpressionException extends RuntimeException
{
    public LispExpressionException()
    {
	this("");
    }

    public LispExpressionException(String errorMsg) 
    {
	super(errorMsg);
    }

}
