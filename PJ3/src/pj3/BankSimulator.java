

package pj3;



// author : Gabriel Vieira Equitz
// id: 915254839

//package PJ3;

import java.util.*;
import java.io.*;

// You may add new functions or data in this class 
// You may modify any functions or data members here
// You must use Customer, Teller and ServiceArea
// to implement Bank simulator

class BankSimulator {

  // input parameters
  private int numTellers, customerQLimit;
  private int simulationTime, dataSource;
  private int chancesOfArrival, maxTransactionTime;

  // statistical data
  private int numGoaway, numServed, totalWaitingTime;

  // internal data
  private int customerIDCounter;   // customer ID counter
  private ServiceArea servicearea; // service area object
  private Scanner dataFile;	   // get customer data from file
  private Random dataRandom;	   // get customer data using random function

  // most recent customer arrival info, see getCustomerData()
  private boolean anyNewArrival;  
  private int transactionTime;

 
  // initialize data fields
  private BankSimulator()
  {
      numTellers = 0;
      customerQLimit = 50;
      simulationTime = 0;
      dataSource = 0;
      chancesOfArrival = 0;
      maxTransactionTime = 500;
      numGoaway= 0;
      numServed = 0;
      totalWaitingTime = 0;
      customerIDCounter = 0;
      servicearea = new ServiceArea();
      dataFile = null;
      anyNewArrival = false;
      transactionTime = 0;
      dataRandom = new Random();
  }

  private void setupParameters()
  {
	// read input parameters
      Scanner aInput = new Scanner(System.in); 
 
 
     do {
      System.out.print("Enter simulation time (integer 1 to 10000)  : "); 
      simulationTime = aInput.nextInt(); 
     }
     while(simulationTime < 1 || simulationTime > 10000);
 
 
     do {
       System.out.print("Enter maximum transaction time of customers (integer 1 to 500)  : "); 
       maxTransactionTime = aInput.nextInt(); 
     } 
     while(maxTransactionTime < 1 || maxTransactionTime > 500);
 
     do {
       System.out.print("Enter chances of arrival of new customer (integer 1 to 100)  : "); 
       chancesOfArrival = aInput.nextInt(); 
     }
     while(chancesOfArrival < 1 || chancesOfArrival > 100 );
 
     do {
      System.out.print("Enter the number of tellers  (integer 1 to 10)  : "); 
      numTellers = aInput.nextInt(); 
     }
     while(numTellers < 1 || numTellers > 10);
 
     do {
     System.out.print("Enter customer queue limit   (integer 1 to 50)  : "); 
     customerQLimit = aInput.nextInt(); 
     }
     while(customerQLimit < 1 || customerQLimit > 50);
 
 
     do {
      System.out.print("Enter 1 or 0 to get data from file (1) or Random (0)  : "); 
      dataSource = aInput.nextInt(); 
      
     }
     while(dataSource != 0 && dataSource != 1);
 
 
     String fileName = aInput.nextLine(); 
     if(dataSource == 0){ 
      
         System.out.print("Using Random Data"); 
         
     }    
     else{ 
 
         
 
         System.out.print("Enter filename   : "); 
 
 
        fileName = aInput.nextLine().trim(); // filename 
        
 
         System.out.println(fileName);
         //try to get the file 
         try{ 
           File file = new File(fileName); 
           dataFile = new Scanner(file); 
          
         } 
         catch (FileNotFoundException e){
                 System.out.println("Error: file not found. " 
                + e.getMessage()); 
         }  
         
  
     } 
	// setup dataFile or dataRandom
	// add statements
 
  }
  // Refer to step 1 in doSimulation()
  private void getCustomerData()
  {
	// get next customer data : from file or random number generator
	// set anyNewArrival and transactionTime
      
        if (dataSource == 1) { 
 			int data1 = dataFile.nextInt(); 
 			int data2 = dataFile.nextInt(); 
 
 
 			anyNewArrival = (((data1 % 100) + 1) <= chancesOfArrival); // boolean 
 			transactionTime = (data2 % maxTransactionTime) + 1; 
                       
 	} else { 
 			anyNewArrival = ((dataRandom.nextInt(100) + 1) <= chancesOfArrival); 
 			transactionTime = dataRandom.nextInt(maxTransactionTime) + 1; 
 	} 
      }
    
	// add statements
  

private void doSimulation()
  {
	// add statements

	// Initialize ServiceArea
         servicearea = new ServiceArea(numTellers, customerQLimit);

	// Time driver simulation loop
  	for (int currentTime = 0; currentTime < simulationTime; currentTime++) {

            System.out.println("-------------------------------------------------");
            System.out.println("Time : "  + currentTime);
    		// Step 1: any new customer enters the bank?
    		getCustomerData();

    		if (anyNewArrival) {

      		    // Step 1.1: setup customer data
      		    // Step 1.2: check customer waiting queue too long?
                    //           customer goes away or enters queue
                    customerIDCounter++;
		    Customer newCustomer = new Customer(customerIDCounter, transactionTime, currentTime);
		    System.out.println("\tCustomer #" + customerIDCounter
						+ " arrives with transaction time "
						+ newCustomer.getTransactionTime()+ " units.");
		    if (servicearea.isCustomerQTooLong()) {
				System.out.println("\tCustomer Queue is too long: Customer #"
							+ customerIDCounter + " leaves the queue.");
				numGoaway++;
		    } else {
				servicearea.insertCustomerQ(newCustomer);
				System.out.println("\tCustomer # " + customerIDCounter
							+ " waits in the customer queue.");
		    } 
    		} else {
      		    System.out.println("\tNo new customer!");
    		}

    		// Step 2: free busy tellers, add to free tellerQ
                for (int i = 0; i < servicearea.numBusyTellers(); i++) {
				Teller newTeller = servicearea.getFrontBusyTellerQ();
				if (newTeller.getEndBusyIntervalTime() <= currentTime) {
					Customer newCustomer;
					newTeller = servicearea.removeBusyTellerQ();
					newCustomer = newTeller.busyToFree();
					System.out.println("\tCustomer # " + newCustomer.getCustomerID()
							+ " is done.");
					servicearea.insertFreeTellerQ(newTeller);
					System.out.println("\tTeller #" + newTeller.getTellerID()
							+ " is free.");
				}
		}
    		// Step 3: get free tellers to serve waiting customers 
                for (int i = 0; i < servicearea.numFreeTellers(); i++) {
				if (servicearea.numWaitingCustomers() != 0) {
					Customer newCustomer = servicearea.removeCustomerQ();
					Teller newTeller = servicearea.removeFreeTellerQ();
					newTeller.freeToBusy(newCustomer, currentTime);
					servicearea.insertBusyTellerQ(newTeller);
					System.out.println("\tCustomer # " + newCustomer.getCustomerID()
							+ " gets a teller.");
					System.out
							.println("\tTeller # " + newTeller.getTellerID()
									+ " starts serving customer # "
									+ newCustomer.getCustomerID() + " for "
									+ newCustomer.getTransactionTime() + " units.");
					
					numServed++;
					totalWaitingTime = totalWaitingTime
							+ (currentTime - newCustomer.getArrivalTime());
				}
                }
  	} // end simulation loop

  	// clean-up
  }

  private void printStatistics()
  {
	// add statements into this method!
	// print out simulation results
	// see the given example in README file 
        // you need to display all free and busy tellers
                System.out.println("End of simulation report.");
		System.out.println("# total arrival customers: " + customerIDCounter);
		System.out.println("# customers gone away: " + numGoaway);
		System.out.println("# customers served: " + numServed);
                System.out.println(" " );
		System.out.println("*** Current Teller Info. ***");
		System.out.println("# waiting customers: " + servicearea.numWaitingCustomers());
		System.out.println("# busy tellers: " + servicearea.numBusyTellers());
		System.out.println("# free tellers: " + servicearea.numFreeTellers());
		System.out.println("Total waiting time: " + totalWaitingTime);
                if (numServed != 0)
                 System.out.format("Average waiting time: %.2f%n\n" , 1.0*totalWaitingTime/numServed);
                System.out.println("  " );
                
		System.out.println("*** Busy Teller info ***");
		while (servicearea.numBusyTellers() > 0) {
			Teller aTeller = servicearea.removeBusyTellerQ();
			aTeller.setEndIntervalTime(simulationTime, 1);
                       
			aTeller.printStatistics();
		}
		System.out.println("*** Free Teller info ***");
		
		while (servicearea.numFreeTellers() > 0) {
			Teller aTeller = servicearea.removeFreeTellerQ();
			aTeller.setEndIntervalTime(simulationTime, 0);
			aTeller.printStatistics();
		}
  }

  // *** main method to run simulation ****
  public static void main(String[] args) {
   	BankSimulator runBankSimulator=new BankSimulator();
   	runBankSimulator.setupParameters();
   	runBankSimulator.doSimulation();
   	runBankSimulator.printStatistics();
  }

}

