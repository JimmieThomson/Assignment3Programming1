public class A3_P1_2022 {
    public static void main(String[] args) {
        try{
        Movie[] movieList = new Movie[5];
        movieList[0] = new Movie("Top Gun: Maverick", 130, "M", 600) ;




        }catch(Exception e){

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

        //Validation the fee cost
        if(fee < 600){
            throw new Exception("Fee is too low");
        }
    }
}

class Session {
    private int capacity;
    private int ticketPrice;
    private int soldTickets;
    private Movie SelectedMovie;

    //initialize variables for for chaning price
    public Session(Movie movie, int price){
        capacity = 60;
        ticketPrice = price;
        SelectedMovie = movie;
        soldTickets = 0;
    }

    //initialize variables default
    public Session(Movie movie){
        capacity = 60;
        ticketPrice = 15;
        soldTickets = 0;
        SelectedMovie = movie;
    }

    public boolean sellTickets(int qty){
        if(capacity < qty){
            return false;
        }
        this.soldTickets = qty;
        return true;
    }
    
    public int profit(){
        return (ticketPrice * soldTickets);
    }
}
class SparseSession {
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

    public SparseSession(Movie movie){
        capacity = 20;
        ticketPrice = 15;
        startProfit = 1000;
        soldTickets = 0;
        SelectedMovie = movie;
    }
    public SparseSession(Movie movie, int price){
        capacity = 60;
        ticketPrice = price;
        soldTickets = 0;
        SelectedMovie = movie;
    }

    public boolean sellTickets(int qty){
        if(capacity < qty){
            return false;
        }
        this.soldTickets = qty;
        return true;
    }
    
    public int profit(){
        return (ticketPrice * soldTickets) + startProfit;
    }
}


class KidsSession {
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

    public KidsSession(Movie movie){
        SelectedMovie = movie;
        ValidateRating();
        capacity = 60;
        ticketPrice = 15;
        soldTickets = 0;
    }

    public KidsSession(Movie movie, int price){
        SelectedMovie = movie;
        ValidateRating();
        capacity = 60;
        ticketPrice = price;
        soldTickets = 0;
    }

    void ValidateRating(){
        //Make sure this is refered to later it needs to be as it's a requirement!

        String[] Ratings = {"M", "MA", "G", "PG"};
        boolean Inrating = false;

        //Validating if the rating is real
        for (String variable : Ratings){
            if(SelectedMovie.getRating() == "G" || SelectedMovie.getRating() == "PG"){
                Inrating = true;
                break;
            }
        }
    }

    public boolean sellTickets(int qty){
        if(capacity < qty){
            return false;
        }
        this.soldTickets = qty;
        return true;
    }
    
    public int profit(){
        return (ticketPrice * soldTickets);
    }
}

class WeeklyTimeTable{
    
    public WeeklyTimeTable(int weekNum){

    }
    
    public boolean checkAvailability(int day, int time){

    }
    
    public boolean checkAvailability(String day, String time){

    }
    
    public boolean addSession(Session s, int day, int time){

    }
    
    public boolean addSession(Session s, String day, String time){

    }

    public void showSessions(){

    }
    
    public void showTickets(){

    }
    
    public void showSales(){

    }
    
}