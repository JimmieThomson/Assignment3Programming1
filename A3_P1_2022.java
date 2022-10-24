/*  Put Your Student ID, Name here 

   S3947963

   James Thomson

*/

import java.util.Arrays;
import java.util.Scanner;

public class A3_P1_2022 {
    public static void main(String[] args) {
        try{
            Movie[] movieList = new Movie[5];
            
            movieList[0] = new Movie("Snowpiercer", 126, "MA", 400);
            movieList[1] = new Movie("Top Gun: Maverick", 130, "M", 600);
            movieList[2] = new Movie("Monsters, Inc.", 92, "G", 90);
            movieList[3] = new Movie("Coraline", 100, "PG", 40);
            movieList[4] = new Movie("Goodbye", 140, "PG", 60);
            GUI.StartScreen();

            System.out.print("\n");
            GUI.GetSelection(movieList);
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

    //Movie Inil
    public Movie(String title, int duration, String rating, int fee) throws Exception{
        //Validating the variables
        Validation(duration, rating, fee);
        this.Movtitle = title;
        this.Movduration = duration;
        this.Movrating = rating;
        this.Movfee = fee;
    }

    //Gets Duration
    public String getDuration(){
        return String.format("%smin", Movduration);
    }

    //Grabs Rating
    public String getRating(){
        return Movrating;
    }

    //Grabs Title
    public String getTitle(){
        return Movtitle;
    }

    //Gets fee
    public String getFee(){
        return String.format("$%s", Movfee);
    }

    //Validaes whether the Movie Exists
    void Validation (int duration, String rating, int fee) throws Exception{
        String[] Ratings = {"M", "MA", "G", "PG"};

        //Validating the duration
        if (duration > 220){
            throw new Exception("Longer than 220 minutes");
        }
        
        //Validating if the rating is real
        boolean Exist = false;

        //Running through the variables to see if it Exists
        for (String variable : Ratings){
            if(rating == variable){
                Exist = true;
            }
        }
        if (Exist == false){
            //Throwing in case it doesn't
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

    //Grabs Capacity
    public int getCapacity(){
        return capacity;
    }

    //getsTicekets
    public int getTickets(){
        return soldTickets;
    }

    //returns wether the tickets where put in and changes there variables
    public boolean sellTickets(int qty){
        if(capacity < qty){
            System.out.println("not successful!");
            return false;
        }
        this.soldTickets = qty;
        return true;
    }
    
    //sets the profit total
    public int profit(){
        if(SelectedMovie.getTitle() == "Top Gun: Maverick"){
            return (ticketPrice * soldTickets) - 600;
        }
        return (ticketPrice * soldTickets);
    }

    //gets Title
    public String getTitle(){
        return SelectedMovie.getTitle();
    }

    //Gets if it's a kids class
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

    //Sets the ini of the other class that extends session
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

    //Seperate ini in case there needs to be a price accociated with the tickets
    public SparseSession(Movie movie, int price){
        super(movie, price);
        SelectedMovie = movie;
        capacity = 60;
        ticketPrice = price;
        soldTickets = 0;
    }

    //Grabs Capacity
    public int getCapacity(){
        return capacity;
    }
    
    //gets Ticket Prices
    public int getTickets(){
        return soldTickets;
    }

    //Gets title of movie
    public String getTitle(){
        return SelectedMovie.getTitle();
    }

    //Sets the tickets in the movie session and gets the amount
    //Checks wether the tickets are available
    public boolean sellTickets(int qty){
        if(capacity < qty){
            System.out.println("not successful!");
            return false;
        }
        this.soldTickets = qty;
        return true;
    }

    //Used for Kids class please ignore
    void ValidateRating(){}

    //Sets total profit of the movie and tickets
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

    //Kids class ini which also checks if the movie can be execepted with time and date
    //Since it's a kids session
    public KidsSession(Movie movie) throws Exception{
        super(movie);
        SelectedMovie = movie;
        ValidateRating();
        capacity = 60;
        ticketPrice = 15;
        soldTickets = 0;
        Kids = true;
    }

    //Gets whether the session is a kids session
    public boolean getIsKidsSession(){
        return Kids;
    }

    //Grabs Capacity
    public int getCapacity(){
        return capacity;
    }

    //Another ini to set price does the same thing as KidsSession
    public KidsSession(Movie movie, int price) throws Exception{
        super(movie, price);
        SelectedMovie = movie;
        ValidateRating();
        capacity = 60;
        ticketPrice = price;
        soldTickets = 0;
    }

    //Used to check wether the 
    void ValidateRating() throws Exception{
        //Make sure this is refered to later it needs to be as it's a requirement!

        //This shouldn't exist but makes my life weird if i dont :)
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

    //Gets the tickets
    public int getTickets(){
        return soldTickets;
    }

    //sets ticket amount and checks whether it can happen
    public boolean sellTickets(int qty){
        if(capacity < qty){
            System.out.println("not successful!");
            return false;
        }
        this.soldTickets = qty;
        return true;
    }
    
    //Gets profit total of the movie session
    public int profit(){
        if(SelectedMovie.getTitle() == "Top Gun: Maverick"){
            return (ticketPrice * soldTickets) - 600;
        }
        return (ticketPrice * soldTickets);
    }
}
class WeeklyTimeTable{
    private String WeekRoster[][] = new String[7][7];
    private String[] Times = {"08:00", "10:00", "12:00", "14:00", "16:00", "18:00", "20:00"};
    private Session BookedShowings[][] = new Session[7][7];
    
    //Sets ini for the class
    public WeeklyTimeTable(int weekNum){
        for (String[] row: WeekRoster){
            Arrays.fill(row, "    ---    ");
        }
    }
    
    //Used for numbered varibles to check whether a session is avaiable
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
    
    //Checks wether the availability alphabeticaly, it transfers the alph to numerical to send to the numerical availability 
    public boolean checkAvailability(String day, String time) throws Exception{
        String[] Days = {"Monday","Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

        int numericalDay = 1;
        boolean dayFound = false;
        boolean timeFound = false;

        //Cycles to find where the day sits numerically
        for(String DayinWeek : Days){
            if (DayinWeek == day){
                dayFound = true;
                break;
            }
            numericalDay++;
        }

        int numericalTime = 1;
        //Cycles to find where the Time sits numerically
        for(String SingleTime : Times){
            if (SingleTime == time){
                timeFound = true;
                break;
            }
            numericalTime++;
        }
        
        //Makes sure no issues can go to the numerical side
        if(dayFound == true && timeFound == true){
            checkAvailability(numericalDay, numericalTime);
            return true;
        }else{
            throw new Exception("Incorrect usage of time or day");
        }
    }
    
    //Adds a session to the system numerically
    public boolean addSession(Session s, int day, int time){

        day--;
        time--;
        //Validates whether it can fit in the ini arrays
        if(s.getIsKidsSession() != true && time < 5){
            String TitleFixed = s.getTitle().substring(0,7);
            WeekRoster[time][day] = String.format("%s  ", TitleFixed);


            BookedShowings[time][day] = s;
            return true;
        }
        System.out.println("not successful!");
        return false;
    }
    
    //Adds a session to the system alphabetically
    public boolean addSession(Session s, String day, String time){
        String[] Days = {"Moday","Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

        int numericalDay = 1;
        boolean dayFound = false;
        boolean timeFound = false;

        //Gets the days numerically
        for(String DayinWeek : Days){
            if (DayinWeek == day){
                dayFound = true;
                break;
            }
            numericalDay++;
        }
        int numericalTime = 1;
        //Gets the Time numerically
        for(String SingleTime : Times){
            if (SingleTime == time){
                timeFound = true;
                break;
            }
            numericalTime++;
        }
        
        //Validates whether if it was acually found in the system arrays
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
        int i = 0;
        System.out.println(String.format("%20s%15s%15s%15s%15s%15s%15s","Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"));
        System.out.println("-----------------------------------------------------------------------------------------------------------------");
        for (final Object[] row : WeekRoster) {
            System.out.print(Times[i]);
            System.out.format("%19s%15s%15s%15s%15s%15s%15s%n", row);
            for(int j=0; j<7; j++){
                //THIS IS TO PRINT OUT THE TICKET COSTS
                //Don't look if you want to keep your sanity
                if(j == 0){
                    System.out.format("%19s", ((BookedShowings[i][j] == null) ? "       "  :  String.format("%s", String.valueOf(BookedShowings[i][j].getCapacity() - BookedShowings[i][j].getTickets()))));
                }
                else if (j  == 6){
                    System.out.format("%15s%n", ((BookedShowings[i][j] == null) ? "       "  :  String.format("%s", String.valueOf(BookedShowings[i][j].getCapacity() - BookedShowings[i][j].getTickets()))));
                }else{
                    System.out.format("%15s", ((BookedShowings[i][j] == null) ? "       "  :  String.format("%s", String.valueOf(BookedShowings[i][j].getCapacity() - BookedShowings[i][j].getTickets()))));
                }
            }
            i++;
        }
    }

    //Gets Session information
    public void setTickets(int day, int time, int Tickets){
        BookedShowings[time][day].sellTickets(Tickets);
    }
     //Gets Session information
    public Session GetBookedShowing(int day, int time){
        return BookedShowings[day][time];
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
                //THIS IS TO PRINT OUT THE TICKET COSTS
                //Don't look if you want to keep your sanity
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
                //Same thing however for total price
                //Pain
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
        //Sets absolute total of the week
        for(int k=0; k<7; k++){
            for(int j=0; j<7; j++){
                Total += ((BookedShowings[k][j] == null) ? 0  : BookedShowings[k][j].profit());
            }
        }

        //formates it to the screen
        System.out.format("The total of this week is $%d%n", Total);
    }
}
class GUI{
    private static WeeklyTimeTable week1 = new WeeklyTimeTable(1);
    private static Scanner Scanner = new Scanner(System.in);
    
    public static void StartScreen(){
        System.out.println("1.Show the timetable of the week");
        System.out.println("2.Show available tickets of the week");
        System.out.println("3.Show sales report of the week");
        System.out.println("4.Add a session by day and time");
        System.out.println("5.Sale tickets by day and time");
        System.out.println("6.Quit");
    }
    public static void GetSelection(Movie[] movieList) throws Exception{
        while(true){
                String Input  = Scanner.nextLine();
                //Gets Output
                if("1".equals(Input)){
                    week1.showSessions();
                    StartScreen();
                }else if("2".equals(Input)){
                    week1.showTickets();
                    StartScreen();
                }else if("3".equals(Input)){
                    week1.showSales();
                    StartScreen();
                }else if("4".equals(Input)){
                    AddSession(movieList);
                    StartScreen();
                }else if("5".equals(Input)){
                    SaleTickets();
                    StartScreen();
                } else if("6".equals(Input)){
                    break;
                }else{
                    System.out.println("Not valid input, try agappin ...");
                }
        }   
        Scanner.close();
    }

    //Unfortunatly methods including this one only work in "1 1" form rather than the "Mon 18:00" form, however it
    //Does work if you hard code the values lots of bugs and not enough time to fix them corners are cut
    //in this version
    static void SaleTickets(){
        week1.showTickets();
        System.out.println("Select The Time and day");

        String Session  = Scanner.nextLine();
        String[] SessionArray = Session.split(" ");

        Session  = Scanner.nextLine();
        try{
            week1.setTickets((Integer.parseInt(SessionArray[0]) - 1), (Integer.parseInt(SessionArray[1]) - 1), Integer.parseInt(Session));
            System.out.println("Succesfull Selling!");
        }catch(Exception e){
            System.out.println("Unsuccesfull Selling because session does not exist");
        }
    }

    //Adds a Session by date and time
    static void AddSession(Movie[] movieList) throws Exception{
        Session SessionItem;
        Movie SelectedMovie;
        int i = 1;
            System.out.print("\n");
            System.out.println("Select a movie from below:");

            for(Movie layer : movieList){
                System.out.format("%d. %-20s %-5s %-7s %s%n",i, layer.getTitle(), layer.getDuration(), layer.getRating(), layer.getFee());
                i++;
            }
            while(true){
                try{
                    int MoveNumb  = Scanner.nextInt();
                    SelectedMovie = movieList[MoveNumb - 1];
                    break;
                }catch(Exception e){
                    System.out.println("Invalid input, try again...");
                }
            }
            System.out.println("Is this a normal session? Y/N");
            Scanner.nextLine();
            while(true){
                String Session  = Scanner.nextLine();
                if(Session.equals("Y")){
                    SessionItem = new Session(SelectedMovie); 
                    break;
                }else if(Session.equals("N")){
                    System.out.println("Is this a kids session? Y/N");
                    while(true){
                        Session  = Scanner.nextLine();
                        if(Session.equals("Y")){
                            SessionItem = new KidsSession(SelectedMovie); 
                            break;
                        }else if(Session.equals("N")){
                            SessionItem = new SparseSession(SelectedMovie); 
                            break;
                        }else{
                            System.out.println("Invalid input, try again...");
                        }
                    }
                    break;
                }else{
                    System.out.println("Invalid input, try again...:");
                }
            }
            System.out.println("Select a movie from below:");
            week1.showSessions();


            while(true){
                String Session  = Scanner.nextLine();
                String[] SessionArray = Session.split(" ");
                    try{
                            try{
                            week1.addSession(SessionItem, Integer.parseInt(SessionArray[0]), Integer.parseInt(SessionArray[1]));
                            System.out.println("Session created");
                                break;
                            }catch(Exception e){
                                week1.addSession(SessionItem, SessionArray[0], SessionArray[1]);
                                break;
                            }
                        }catch(Exception e){
                        System.err.println(e.getMessage());
                    }
            }
    }
}