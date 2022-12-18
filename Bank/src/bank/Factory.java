/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bank;

/**
 *
 * @author Mark
 */

/**
 * A factory class that creates (1) the bank and (2) each customer at the bank.
 *
 * Usage:
 *	java Factory <one or more resources>
 *
 * I.e.
 *	java Factory 10 5 7
 */

import java.io.*;
import java.util.*;

public class Factory {
    public static void main(String[] args) {
        int numOfResources;
        int[] resources;
        if (args.length == 0) {
            numOfResources = 3;
            resources = new int[] { 10, 10, 10 };
        } else {
            numOfResources = args.length;
            resources = new int[numOfResources];
            for (int i = 0; i < numOfResources; i++)
                resources[i] = Integer.parseInt(args[i].trim());
        }

        BankImpl theBank = new BankImpl(resources);
        int[] maxDemand = new int[numOfResources];

        // the customers
        Thread[] workers = new Thread[Customer.COUNT];

        // read initial values for maximum array
        String line;
        try {
            try (BufferedReader inFile = new BufferedReader(new FileReader("C:\\Users\\lenovo\\OneDrive\\Desktop\\Bank\\build\\classes\\bank\\infile.txt"))) {
                int threadNum = 0;
                int resourceNum = 0;

                for (int i = 0; i < Customer.COUNT; i++) {
                    line = inFile.readLine();
                    StringTokenizer tokens = new StringTokenizer(line, ",");

                    while (tokens.hasMoreTokens()) {
                        int amt = Integer.parseInt(tokens.nextToken().trim());
                        maxDemand[resourceNum++] = amt;
                    }
                    workers[threadNum] = new Thread(new Customer(threadNum, maxDemand, (Bank) theBank));
                    theBank.addCustomer(threadNum, maxDemand);
                    // theBank.getCustomer(threadNum);
                    ++threadNum;
                    resourceNum = 0;
                }
            } catch (NumberFormatException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } catch (FileNotFoundException fnfe) {
            throw new Error("Unable to find file \"infile.txt\"");
        } catch (IOException ioe) {
            throw new Error("Error processing \"infile.txt\"");
        }

        // start all the customers
        System.out.println("FACTORY: created threads");

        for (int i = 0; i < Customer.COUNT; i++)
            workers[i].start();

        System.out.println("FACTORY: started threads");

        try {
            Thread.sleep(10000);
        } catch (InterruptedException ie) {
        }
        System.out.println("FACTORY: interrupting threads");
        for (int i = 0; i < Customer.COUNT; i++)
            workers[i].interrupt();

    }
}

