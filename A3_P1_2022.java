import java.text.Format;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;

public class A3_P1_2022 {
    public static void main(String[] args) {
        try{
            Movie[] movieList = new Movie[5];
            
            movieList[0] = new Movie("Snowpiercer", 126, "MA", 400);
            movieList[1] = new Movie("Top Gun: Maverick", 130, "M", 600);
            movieList[2] = new Movie("Monsters, Inc.", 92, "G", 90);
            movieList[3] = new Movie("Coraline", 100, "PG", 40);
            movieList[4] = new Movie("Goodbye", 140, "PG", 60);
            
            WeeklyTimeTable week1 = new WeeklyTimeTable(1);
            // WeeklyTimeTable week2 = new WeeklyTimeTable(1);  // Exception!

            System.out.println(week1.checkAvailability(1,1));
            //System.out.println(week1.checkAvailability(1,9));  // Exception!

            System.out.println(week1.checkAvailability("Monday", "14:00"));
           //System.out.println(week1.checkAvailability("Monday", "8:00")); //Exception!

            Session s1 = new Session(movieList[0], 18);
            week1.addSession(s1, 2, 2);
            
            //Session s2 = new KidsSession(movieList[0]);  // Exception!
            Session s2 = new KidsSession(movieList[2]); 
            
            week1.addSession(s2, 4, 6);  // not successful!
            week1.addSession(s2, 4, 5);
            week1.showSessions();
            
            s1.sellTickets(90);          // not successful!
            s1.sellTickets(30);
            s2.sellTickets(25);
            
            week1.showSales();
        }
        catch(Exception e){
            System.err.println(e.getMessage());
        }
    }
}

class Movie {
//This class still needs fuctions and private int for putting things in!!!!!
    private String Movtitle;
    private int Movduration;
    private String Movrating;
    private int Movfee;

    public Movie(String title, int duration, String rating, int fee) throws Exception{
        //Validating the variables
        Validation(duration, rating, fee);
        this.Movtitle = title;
        this.Movduration = duration;
        this.Movrating = rating;
        this.Movfee = fee;
    }

    public String getRating(){
        return Movrating;
    }
    public String getTitle(){
        return Movtitle;
    }

    void Validation (int duration, String rating, int fee) throws Exception{
        String[] Ratings = {"M", "MA", "G", "PG"};

        //Validating the duration
        if (duration > 220){
            throw new Exception("Longer than 220 minutes");
        }
        
        //Validating if the rating is real
        boolean Exist = false;

        for (String variable : Ratings){
            if(rating == variable){
                Exist = true;
            }
        }
        if (Exist == false){
            throw new Exception("Rating does not exist");
        }
    }
}
class Session{
    private int capacity;
    private int ticketPrice;
    private int soldTickets;
    private Movie SelectedMovie;
    private boolean Kids;

    //initialize variables for for chaning price
    public Session(Movie movie, int price){
        capacity = 60;
        ticketPrice = price;
        SelectedMovie = movie;
        soldTickets = 0;
        Kids = false;
    }

    //initialize variables default
    public Session(Movie movie){
        capacity = 60;
        ticketPrice = 15;
        soldTickets = 0;
        SelectedMovie = movie;
    }
    public int getTickets(){
        return soldTickets;
    }

    public boolean sellTickets(int qty){
        if(capacity < qty){
            System.out.println("not successful!");
            return false;
        }
        this.soldTickets = qty;
        return true;
    }
    
    public int profit(){
        if(SelectedMovie.getTitle() == "Top Gun: Maverick"){
            return (ticketPrice * soldTickets) - 600;
        }
        return (ticketPrice * soldTickets);
    }

    public String getTitle(){
        return SelectedMovie.getTitle();
    }
    public boolean getIsKidsSession(){
        return Kids;
    }

}
class SparseSession extends Session{
     /*
        SparseSession Function Used when imposing social distancing laws
        Capacity is 1/3 = 20 Seats
        Subsity from the goverment up to $1000
    */
    private int capacity;
    private int ticketPrice;
    private int startProfit;
    private int soldTickets;
    private Movie SelectedMovie;
    private boolean Kids;

    public SparseSession(Movie movie){
        super(movie);
        ValidateRating();
        capacity = 20;
        ticketPrice = 15;
        startProfit = 1000;
        soldTickets = 0;
        SelectedMovie = movie;
        Kids = false;
    }
    public SparseSession(Movie movie, int price){
        super(movie, price);
        SelectedMovie = movie;
        capacity = 60;
        ticketPrice = price;
        soldTickets = 0;
    }
    public int getTickets(){
        return soldTickets;
    }
    public String getTitle(){
        return SelectedMovie.getTitle();
    }

    public boolean sellTickets(int qty){
        if(capacity < qty){
            System.out.println("not successful!");
            return false;
        }
        this.soldTickets = qty;
        return true;
    }
    void ValidateRating(){}
    public int profit(){
        if(SelectedMovie.getTitle() == "Top Gun: Maverick"){
            return (ticketPrice * soldTickets) - 600 +startProfit;
        }
        return (ticketPrice * soldTickets);
    }
    public boolean getIsKidsSession(){
        return Kids;
    }

}
class KidsSession extends Session{
    /*
        KidsSession Function will be used when there are kids involved in the movies
        price has a 40% discount
        rating must only be "G" or "PG"
        Cannot run past the time 6pm
     */
    private int capacity;
    private int ticketPrice;
    private int soldTickets;
    private Movie SelectedMovie;
    private boolean Kids;

    public KidsSession(Movie movie) throws Exception{
        super(movie);
        SelectedMovie = movie;
        ValidateRating();
        capacity = 60;
        ticketPrice = 15;
        soldTickets = 0;
        Kids = true;
    }

    public boolean getIsKidsSession(){
        return Kids;
    }

    public KidsSession(Movie movie, int price) throws Exception{
        super(movie, price);
        SelectedMovie = movie;
        ValidateRating();
        capacity = 60;
        ticketPrice = price;
        soldTickets = 0;
    }

    void ValidateRating() throws Exception{
        //Make sure this is refered to later it needs to be as it's a requirement!

        String[] Ratings = {"M", "MA", "G", "PG"};

        //Validating if the rating is real
        for (String variable : Ratings){
            if(SelectedMovie.getRating() == "G" || SelectedMovie.getRating() == "PG"){
                break;
            }else{
                throw new Exception("Rating is too high");
            }
        }
    }
    public int getTickets(){
        return soldTickets;
    }
    public boolean sellTickets(int qty){
        if(capacity < qty){
            System.out.println("not successful!");
            return false;
        }
        this.soldTickets = qty;
        return true;
    }
    
    public int profit(){
        if(SelectedMovie.getTitle() == "Top Gun: Maverick"){
            return (ticketPrice * soldTickets) - 600;
        }
        return (ticketPrice * soldTickets);
    }
}

class WeeklyTimeTable{
    private int WeeklyNumber;
    private String WeekRoster[][] = new String[7][7];
    private String[] Times = {"08:00", "10:00", "12:00", "14:00", "16:00", "18:00", "20:00"};
    Session BookedShowings[][] = new Session[7][7];
    
    public WeeklyTimeTable(int weekNum){
        WeeklyNumber = weekNum;
        for (String[] row: WeekRoster){
            Arrays.fill(row, "    ---    ");
        }
    }
    
    public boolean checkAvailability(int day, int time) throws Exception{
        day--;
        time--;
        if(day > 7 || time > 7){
            throw new Exception("Time or day is invalid");
        }else{
            if (BookedShowings[time][day] == null){
                return true;
            }
        }
        return false;
    }
    
    public boolean checkAvailability(String day, String time) throws Exception{
        String[] Days = {"Monday","Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

        int numericalDay = 1;
        boolean dayFound = false;
        boolean timeFound = false;

        for(String DayinWeek : Days){
            if (DayinWeek == day){
                dayFound = true;
                break;
            }
            numericalDay++;
        }
        int numericalTime = 1;
        for(String SingleTime : Times){
            if (SingleTime == time){
                timeFound = true;
                break;
            }
            numericalTime++;
        }
        
        if(dayFound == true && timeFound == true){
            checkAvailability(numericalDay, numericalTime);
            return true;
        }else{
            throw new Exception("Incorrect usage of time or day");
        }
    }
    
    public boolean addSession(Session s, int day, int time){

        day--;
        time--;
        if(s.getIsKidsSession() != true && time < 5){
            String TitleFixed = s.getTitle().substring(0,7);
            WeekRoster[time][day] = String.format("%s  ", TitleFixed);


            BookedShowings[time][day] = s;
            return true;
        }
        System.out.println("not successful!");
        return false;
    }
    
    public boolean addSession(Session s, String day, String time){
        String[] Days = {"Moday","Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

        int numericalDay = 1;
        boolean dayFound = false;
        boolean timeFound = false;

        for(String DayinWeek : Days){
            if (DayinWeek == day){
                dayFound = true;
                break;
            }
            numericalDay++;
        }
        int numericalTime = 1;
        for(String SingleTime : Times){
            if (SingleTime == time){
                timeFound = true;
                break;
            }
            numericalTime++;
        }
        
        if(dayFound == true && timeFound == true){
        }else{
            return false;
        }

        addSession(s, numericalDay, numericalTime);
        return true;
    }

    public void showSessions(){
        //Formatting the start table
        System.out.println(String.format("%20s%15s%15s%15s%15s%15s%15s","Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"));
        System.out.println("-----------------------------------------------------------------------------------------------------------------");


        //Printing out the Table
        int i = 0;
        for (final Object[] row : WeekRoster) {
            System.out.print(Times[i]);
            System.out.format("%19s%15s%15s%15s%15s%15s%15s%n", row);
            i++;
        }
    }
    
    public void showTickets(){

    }
    
    public void showSales(){
        //Formatting the start table
        System.out.println(String.format("%20s%15s%15s%15s%15s%15s%15s","Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"));
        System.out.println("-----------------------------------------------------------------------------------------------------------------");


        //Printing out the Table
        int i = 0;
        for (final Object[] row : WeekRoster) {
            System.out.print(Times[i]);
            System.out.format("%19s%15s%15s%15s%15s%15s%15s%n", row);
            for(int j=0; j<7; j++){
                if(j == 0){
                    System.out.format("%19s", ((BookedShowings[i][j] == null) ? "       "  :  String.format("x %s", String.valueOf(BookedShowings[i][j].getTickets()))));
                }
                else if (j  == 6){
                    System.out.format("%15s%n", ((BookedShowings[i][j] == null) ? "       "  :  String.format("x %s", String.valueOf(BookedShowings[i][j].getTickets()))));
                }else{
                    System.out.format("%15s", ((BookedShowings[i][j] == null) ? "       "  :  String.format("x %s", String.valueOf(BookedShowings[i][j].getTickets()))));
                }
            }
            for(int j=0; j<7; j++){
                if(j == 0){
                    System.out.format("%19s", ((BookedShowings[i][j] == null) ? "       "  :  String.format("$%s",String.valueOf(BookedShowings[i][j].profit()))));
                }
                else if (j  == 6){
                    System.out.format("%15s%n", ((BookedShowings[i][j] == null) ? "       "  :  String.format("$%s",String.valueOf(BookedShowings[i][j].profit()))));
                }else{
                    System.out.format("%15s", ((BookedShowings[i][j] == null) ? "       "  :  String.format("$%s",String.valueOf(BookedShowings[i][j].profit()))));
                }
            }
            i++;
        }
        int Total = 0;
        for(int k=0; k<7; k++){
            for(int j=0; j<7; j++){
                Total += ((BookedShowings[k][j] == null) ? 0  : BookedShowings[k][j].profit());
            }
        }

        System.out.format("The total of this week is $%d", Total);
    }
    
}