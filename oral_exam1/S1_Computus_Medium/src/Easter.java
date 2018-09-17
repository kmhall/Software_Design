public class Easter {

    //Call static methods in constructor

    private final String [] listMonths;
    private int month;
    private int day;
    private int year;

    public Easter(int year){
        if(year<0){
            throw new IllegalArgumentException("Year is invalid");
        }
        this.listMonths = new String[]{"January","February","March","April","May","June","July","August","September","October","November","December"};

        int [] date = calculateEaster(year);

        this.day = date[0];
        this.month = date[1];
        this.year = year;

    }

    public int getMonth(){
        return month;
    }

    public int getDay(){
        return day;
    }

    private static int[] calculateEaster(int year){
        int []date = new int[2];

        int a = year%19;
        int b = year/100;
        int c = year%100;
        int d = b/4;
        int e = b%4;
        int f = (b+8)/25;
        int g = (b-f+1)/3;
        int h = (19*a+b-d-g+15)%30;
        int i = c/4;
        int k = c%4;
        int l = (32+2*e+2*i-h-k)%7;
        int m = (a+11*h+22*l)/451;

        date[1] = (h+l-7*m+114)/31;
        date[0] = ((h+l-7*m+114)%31)+1;

        return date;
    }

    public String toString(){
        return "Easter: " + listMonths[month-1] + " " + day + ", " + year;
    }

}
