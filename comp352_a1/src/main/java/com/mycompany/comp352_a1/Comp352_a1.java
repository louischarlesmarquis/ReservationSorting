
package com.mycompany.comp352_a1;

public class Comp352_a1 {

    public static int getDay(String date){
        String parts[] = date.split("-");
        int day = Integer.parseInt(parts[2]);
        return day;
    }

    public static int getMonth(String date){
        String parts[] = date.split("-");
        int month = Integer.parseInt(parts[1]);
        return month;
    }

    public static int getYear(String date){
        String parts[] = date.split("-");
        int year = Integer.parseInt(parts[0]);
        return year;
    }

    public static int activeReservations(String[] rDate, String currentDate){
        int active=0;
        String parts[] = currentDate.split("-");
        int day = Integer.parseInt(parts[2]);
        int month = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[0]);
        
        for(int i=0; i<rDate.length; i++){
            if (getYear(rDate[i]) == year){
                if (getMonth(rDate[i]) == month){
                    if (getDay(rDate[i]) >= day){
                        active++;
                    }
                }
                else if(getMonth(rDate[i]) > month){
                    active++;
                }
            }
            else if (getYear(rDate[i]) > year){
                active++;
            }
        }
        return active;
    }
    
    public static void swap(String[] rDate, int[] rSlot, String[] rMedicare, String currentDate){
        int indexOfCurrentDate=0;
        String parts[] = currentDate.split("-");
        int day = Integer.parseInt(parts[2]);
        int month = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[0]);
        for(int i=0; i< rDate.length; i++){
            if(getYear(rDate[i]) >= year && getMonth(rDate[i]) >= month && getDay(rDate[i]) >= day){
                indexOfCurrentDate=i;
                break;
            }
        }
        
        for (int i = 0; i < indexOfCurrentDate; i++) {
            String d = rDate[0];
            int s = rSlot[0];
            String m = rMedicare[0];
            for (int j = 0; j < (rDate.length - 1); ++j){
                rDate[j] = rDate[j + 1];
                rSlot[j] = rSlot[j + 1];
                rMedicare[j] = rMedicare[j + 1];
            }
            rDate[(rDate.length - 1)] = d;
            rSlot[(rDate.length - 1)] = s;
            rMedicare[(rDate.length - 1)] = m;
        }
    }
    
    public static int rearrangeReservations(String[] rDate, int[] rSlot, String[] rMedicare, 
            String currentDate, int numReservations){
        
        if (numReservations == 1){
            swap(rDate, rSlot, rMedicare, currentDate);
            return activeReservations(rDate, currentDate);
        }
        
        for (int i=0; i<numReservations-1; i++){
            if (getYear(rDate[i]) == getYear(rDate[i+1])){
                if (getMonth(rDate[i]) == getMonth(rDate[i+1])){
                    if (getDay(rDate[i]) == getDay(rDate[i+1])){
                        if(rSlot[i] > rSlot[i+1]){
                            String temp = rDate[i];rDate[i] = rDate[i+1];rDate[i+1] = temp;
                            int temp2 = rSlot[i];rSlot[i] = rSlot[i+1];rSlot[i+1] = temp2;
                            String temp3 = rMedicare[i];rMedicare[i] = rMedicare[i+1];rMedicare[i+1] = temp3;
                        }
                    }
                    else if(getDay(rDate[i]) > getDay(rDate[i+1])){
                        String temp = rDate[i];rDate[i] = rDate[i+1];rDate[i+1] = temp;
                        int temp2 = rSlot[i];rSlot[i] = rSlot[i+1];rSlot[i+1] = temp2;
                        String temp3 = rMedicare[i];rMedicare[i] = rMedicare[i+1];rMedicare[i+1] = temp3;
                    }
                }
                else if(getMonth(rDate[i]) > getMonth(rDate[i+1])){
                    String temp = rDate[i];rDate[i] = rDate[i+1];rDate[i+1] = temp;
                    int temp2 = rSlot[i];rSlot[i] = rSlot[i+1];rSlot[i+1] = temp2;
                    String temp3 = rMedicare[i];rMedicare[i] = rMedicare[i+1];rMedicare[i+1] = temp3;
                }
            }
            else if (getYear(rDate[i]) > getYear(rDate[i+1])){
                String temp = rDate[i];rDate[i] = rDate[i+1];rDate[i+1] = temp;
                int temp2 = rSlot[i];rSlot[i] = rSlot[i+1];rSlot[i+1] = temp2;
                String temp3 = rMedicare[i];rMedicare[i] = rMedicare[i+1];rMedicare[i+1] = temp3;
            }
        }
        return rearrangeReservations(rDate, rSlot, rMedicare, currentDate, numReservations-1);
    }
    
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        String[] rDate = {"2021-11-25", "2022-03-08", "2021-12-02", "2022-02-01", "2021-12-02", "2021-12-03", "2022-01-25", "2022-01-14", "2022-04-01", "2022-03-01"};
	String[] rMedicare = {"CCC11", "FFF22", "DDD77", "HHH33", "KKK77", "AAA66", "JJJ44", "EEE99", "GGG55", "BBB66"};
	int[] rSlot = {25, 10, 7, 15, 3, 40, 36, 11, 10, 15};
        displayReservations(rDate, rSlot, rMedicare, 6);
        System.out.println("-----------------------------------");
        displayPastReservationsIncreasingOrder(rDate, rSlot, rMedicare, 4);
        System.out.println("-----------------------------------");
        displayPastReservationsDecreasingOrder(rDate, rSlot, rMedicare, 4);
        long end = System.currentTimeMillis();
        long time = end-start;
        System.out.println("Total run-time: " + time + "milliseconds");
    }
    
    public static void displayReservations(String[] rDate, int[] rSlot, String[] rMedicare, int futureReservations) {
        rearrangeReservations(rDate, rSlot, rMedicare, "2022-01-01", 10);
        System.out.println("Future reservations in increasing order:");
        for (int i = 0; i < futureReservations; i++) {
                System.out.println("Date: " + rDate[i] + " Slot: " + rSlot[i] + " Medicare: " + rMedicare[i]);
        }
    }
    
    public static void displayPastReservationsIncreasingOrder(String[] rDate, int[] rSlot, String[] rMedicare, int pastReservations) {
        rearrangeReservations(rDate, rSlot, rMedicare, "2022-01-01", 4);
        System.out.println("Past reservations in increasing order:");
        for (int i = rDate.length-pastReservations; i < rDate.length; i++) {
            System.out.println("Date: " + rDate[i] + " Slot: " + rSlot[i] + " Medicare: " + rMedicare[i]);
        }
    }
	
    public static void displayPastReservationsDecreasingOrder(String[] rDate, int[] rSlot, String[] rMedicare, int pastReservations) {
        rearrangeReservations(rDate, rSlot, rMedicare, "2022-01-01", 4);
        System.out.println("Past reservations in decreasing order:");
        for (int i = (rDate.length-1); i > rDate.length-1-pastReservations; i--) {
            System.out.println("Date: " + rDate[i] + " Slot: " + rSlot[i] + " Medicare: " + rMedicare[i]);
        }

    }
     
}
