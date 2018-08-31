public class Easter {

    public Easter(int year){
        this.year = year;
        calculateEaster();
    }

    private String [] listMonths = new String[]{"January","February","March","April","May","June","July","August","September","October","November","December"};

    private int month;
    private int day;
    private int year;

    private String easterString;

    public int getMonth(){
        return month;
    }

    public int getDay(){
        return day;
    }

    private void calculateEaster(){

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

        this.month = (h+l-7*m+114)/31;
        this.day = ((h+l-7*m+114)%31)+1;
        this.easterString = "Easter: " + listMonths[month] + " " + day + ", " + year;
    }


}
