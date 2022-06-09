import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.util.Random;

/**
 * This program runs a lottery and calculates the corresponding payout based on the number of matches 
 * on a randomly chosen ticket, as well as the total profit.
 * 
 * @author Nour Mihamou and Lulama Nyembe
 * @version 04/9/2021
 */
public class Lottery
{
    /*
     * Private global static (class) variables.
     * 
     * These variables can be used anywhere in this class.  You do not need to 
     * pass them as parameters between methods.
     * 
     * You may not remove or add any of these variables.
     */

    //The 5 winning lottery numbers.
    private static int ball1, ball2, ball3, ball4, ball5; 

    //The number of tickets that have 0 matches, 1 match, ..., 5 matches.
    private static int num0Match, num1Match, num2Match, num3Match, num4Match, num5Match;

    /**
     * This method is used to run the lottery.  
     * DO NOT EDIT THIS METHOD.
     * 
     * @param args No command line arguments are necessary.
     */
    public static void main(String args[])
    {
        setWinningNumbers();

        Scanner sc = new Scanner(System.in);
        System.out.print("\nEnter the input file name:  ");
        String fileName = sc.nextLine();
        sc.close();

        try
        {    
            readInData(fileName);
        }
        catch (FileNotFoundException fne)
        {
            System.out.println("The input data file cannot be found.");
            System.exit(0);
        }

        printOutput();
    }

    /**
     * The method sets the global static (class) variables ball1, ball2,
     * ..., ball5 to random values in [1, 39].  Each of the 5 numbers
     * selected must be unique.  E.g.,
     * 
     * {17, 9, 28, 12, 1}
     * 
     * is a valid selection, but 
     * 
     * {17, 9, 28, 12, 17}
     * 
     * is not a valid selection because 17 was selected twice.
     */
    private static void setWinningNumbers()
    {
        Random rand = new Random(); 

        ball1 = rand.nextInt(39) + 1;
        ball2 = rand.nextInt(39) + 1;
        ball3 = rand.nextInt(39) + 1;
        ball4 = rand.nextInt(39) + 1;
        ball5 = rand.nextInt(39) + 1;
        
        //for every digit, make sure it does not equal the last, if so then generate a new number

        for (int i = 0 ; i <= 5; i++){
            while (ball2 == ball1)
            {
                ball2 = rand.nextInt(39) + 1;
            }
            while ((ball3 == ball1) || (ball3 == ball2))
            {
                ball3 = rand.nextInt(39) + 1;
            }
            while ((ball4 == ball1) || (ball4 == ball2) || (ball4 == ball3))
            {
                ball4 = rand.nextInt(39) + 1;
            }
            while ((ball5 == ball1) || (ball5 == ball2) || (ball5 == ball3) || (ball5 == ball4))
            {
                ball5 = rand.nextInt(39) + 1;
            }
        }

        System.out.println(ball1 + " " + ball2 + " " + ball3 + " " + ball4 + " " + ball5);
        System.out.print("\f");
    }

    /**
     * This method reads all of the lottery tickets from the input file.
     * As each ticket is read, the number of matches is recorded by updating
     * the appropriate global static (class) variable.  E.g., if the ticket
     * read matches 3 of the winning numbers, num3Match is incremented by 1.
     * 
     * @param fileName The name of the input file.  This file must be a
     * CSV file in the format expected for this project.
     */
    private static void readInData(String fileName) throws FileNotFoundException
    {
        //outer loop reads through the entire csv file
        //inner loop reads one row, and every entry

        Scanner inFile = new Scanner(new File(fileName));
        String[] ticket;
        
        //reads until the end of the file
        while(inFile.hasNext()){
            ticket = inFile.nextLine().split(","); 

            int count = 0;
            for(int entry = 0; entry < 5; ++entry){
                int num = Integer.parseInt(ticket[entry]);

                if(ball1 == num){
                    count += 1;
                }
                if(ball2 == num){
                    count += 1;
                }
                if(ball3 == num){
                    count += 1;
                }
                if(ball4 == num){
                    count += 1;
                }
                if(ball5 == num){
                    count += 1;
                }
            }

            if(count == 0){
                num0Match += 1;
            }
            if(count == 1){
                num1Match += 1;
            }
            if(count == 2){
                num2Match += 1;
            }
            if(count == 3){
                num3Match += 1;
            }
            if(count == 4){
                num4Match += 1;
            }
            if(count == 5){
                num5Match += 1;
            }

        }
        System.out.println("5 " + num5Match + "\n4 " + num4Match + "\n3 " + num3Match + "\n2 " + num2Match + "\n1 " + num1Match + "\n0 " + num0Match);

        inFile.close();

    }

    /**
     * This method prints the output formatted as shown in the example
     * output.
     */
    private static void printOutput() 
    {
        System.out.print("\f");
        
        int totalTickets = num0Match + num1Match + num2Match + num3Match + num4Match + num5Match;
        
        //Header
        String matched = "Numbers Matched";
        String ticketsMatched = "Number Tickets";
        String payoutPerMatchedTicket = "Payout Per Matched Ticket";
        String total = "Total Payout";
        String matchedLines = "---------------";
        String ticketsMatchedLines = "--------------";
        String payoutPerMatchedTicketLines = "-------------------------";
        String totalLines = "------------";

        System.out.print("The winning numbers are: ");
        System.out.println(ball1 + " " + ball2 + " " + ball3 + " " + ball4 + " " + ball5);
        System.out.println();

        System.out.print("Number of tickets played: ");
        System.out.println(totalTickets);
        System.out.println();

        System.out.println();
        System.out.printf("%-19s %-18s %-29s %s", matched, ticketsMatched, payoutPerMatchedTicket, total);
        System.out.println();

        System.out.println();
        System.out.printf("%-19s %-18s %-29s %s", matchedLines, ticketsMatchedLines, payoutPerMatchedTicketLines, totalLines);
        System.out.println();
        
        
        //The payoutPerNMatchedTicket calculations
        double payoutPer5MatchedTicket = (double) (0.20 * num5Match / num5Match);
        double payoutPer4MatchedTicket = (double) (0.18 * num4Match / num4Match);
        double payoutPer3MatchedTicket = (double) (0.15 * num3Match / num3Match);
        double payoutPer2MatchedTicket = (double) (0.12 * num2Match / num2Match);
        double payoutPer1MatchedTicket = (double) (0.10 * num1Match / num1Match);
        
        
        
        //Loop ensuring it will not divide by 0 and cause an error
        if (num5Match == 0){
            payoutPer5MatchedTicket = 0.0;            
        }
        if (num4Match == 0){
            payoutPer4MatchedTicket = 0.0;            
        }
        if (num3Match == 0){
            payoutPer3MatchedTicket = 0.0;            
        }
        if (num2Match == 0){
            payoutPer2MatchedTicket = 0.0;            
        }
        if (num1Match == 0){
            payoutPer1MatchedTicket = 0.0;            
        }
        
        
        //The totalPayoutForNMatchedTicket calculations
        double totalPayoutFor5Matches = (double) num5Match * payoutPer5MatchedTicket; 
        double totalPayoutFor4Matches = (double) num4Match * payoutPer4MatchedTicket; 
        double totalPayoutFor3Matches = (double) num3Match * payoutPer3MatchedTicket; 
        double totalPayoutFor2Matches = (double) num2Match * payoutPer2MatchedTicket; 
        double totalPayoutFor1Matches = (double) num1Match * payoutPer1MatchedTicket; 
        
        //Total Payout and Profit calculations
        double totalPayout = totalPayoutFor5Matches + totalPayoutFor4Matches + totalPayoutFor3Matches + totalPayoutFor2Matches + totalPayoutFor1Matches;
        double profit = totalTickets - totalPayout;
        
        //Final output
        System.out.printf("%10d%,18d%18s%,10.2f%14s%,10.2f%n",5, num5Match,"$", payoutPer5MatchedTicket, "$", totalPayoutFor5Matches);
        System.out.printf("%10d%,18d%18s%,10.2f%14s%,10.2f%n",4, num4Match,"$", payoutPer4MatchedTicket, "$", totalPayoutFor4Matches);
        System.out.printf("%10d%,18d%18s%,10.2f%14s%,10.2f%n",3, num3Match,"$", payoutPer3MatchedTicket, "$", totalPayoutFor3Matches);
        System.out.printf("%10d%,18d%18s%,10.2f%14s%,10.2f%n",2, num2Match,"$", payoutPer2MatchedTicket, "$", totalPayoutFor2Matches);
        System.out.printf("%10d%,18d%18s%,10.2f%14s%,10.2f%n",1, num1Match,"$", payoutPer1MatchedTicket, "$", totalPayoutFor1Matches);
        System.out.printf("%10d%,18d%18s%,10.2f%14s%,10.2f%n",0, num0Match,"$", 0.0, "$", 0.0);

        System.out.println();
        System.out.printf("%-7s $%,8.2f%n", "Profit: ", profit);

    }
}
