//package PJ3;

// DO NOT ADD NEW METHODS OR DATA FIELDS!

package pj3;

// author : Gabriel Vieira Equitz
// id: 915254839






public class Customer {
    private int customerID;
    private int transactionTime;
    private int arrivalTime;

    // default constructor
    Customer()
    {
	// add statements
        // define inital values to zero
        customerID = 0;
        transactionTime = 0;
        arrivalTime = 0;
    }

    // constructor to set customerID, transactionTime and arrivalTime
    Customer(int customerid, int duration, int arrivaltime)
    {
	// add statements
        // set inital values
        customerID = customerid;
        transactionTime = duration;
        arrivalTime = arrivaltime;
    }

    int getTransactionTime() 
    {
	// add statements
  	//return 0; 
        //return transaction time
        return transactionTime;
    }

    int getArrivalTime() 
    {
	// add statements
  	//return 0; 
        
        // return arrivalTime
        return arrivalTime;

    }

    int getCustomerID() 
    {
	// add statements
        // return customerID
  	return customerID; 
    }

    public String toString()
    {
        return "customerID="+customerID+":transactionTime="+
               transactionTime+":arrivalTime="+arrivalTime;


    }

    public static void main(String[] args) {
        // quick check!
	Customer mycustomer = new Customer(1,15,18);
	System.out.println("Customer Info:"+mycustomer);
        

    }
}

