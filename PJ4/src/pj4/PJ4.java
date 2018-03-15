/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pj4;

// author : Gabriel Vieira Equitz
// id: 915254839
public class PJ4 {

    public static void main(String args[]) 
    {
	PokerGame pokergame;
	if (args.length > 0)
		pokergame = new PokerGame(Integer.parseInt(args[0]));
	else
		pokergame = new PokerGame();
	pokergame.play();
    }
    
}
