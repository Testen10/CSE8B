import tester.*;

class Video{
    /*
    A Video should contain
        title of the video
        a reference to the User who posted the video
        the count of likes the video has
        a unique id for the Video represented as a String
     */
    String title;
    User reference;
    int cnt_like;
    String id;

    public Video(String title, User reference, int cnt_like, String id){
        this.title = title;
        this.reference = reference;
        this.cnt_like = cnt_like;
        this.id = id;
    }

    public boolean longerThan(Video other){      
        System.out.println("this video"
        + " ("+Integer.toString(this.title.length())+")"
        + " vs " + "other video"
        + " ("+Integer.toString(other.title.length())+") ");
    
        if(other.title.length()<this.title.length()){return true;}
        return false;
    }

    public boolean moreLikes(Video other){
        System.out.println("this video"
        + " ("+Integer.toString(this.cnt_like)+")"
        + " vs " + "other video"
        + " ("+Integer.toString(other.cnt_like)+") ");
        if(other.cnt_like<this.cnt_like){return true;}
        return false;
    }

    public String toText(){
        /*
          The returned string should have the toText of
          the user followed by the title
          followed by the number of likes
          
          "UC San Diego Admissions @UCSanDiegoAdmissions : Welcome to UC San Diego : 138 Likes"
         */

        return this.reference.toText()+" : "+this.title+" : "+cnt_like+" Likes";
    }

    public String toLink(){
        return "https://www.youtube.com/watch?v="+this.id;
    }
}

class User{
    /* A User should contain:
            username of the account
            the display name (also called full name) of the account
            the number of subscribers
            the number of videos posted by the account
            (there is more information we could store, but this is enough for some interesting work)
    */
    String userName;
    String displayName;
    int num_subscriber;
    int num_video;
    
    public User(String username,String displayName, int num_subscriber, int num_video){
        /* PLEASE MAKE SURE THAT THE ORDER OF THE PARAMETERS IN THE CONSTRUCTORS IS IN THE ORDER AS DESCRIBED IN THE TEXT
        (1. username, 2. display name, 3. number of subscribers, 4. number of videos)
        IF YOU CHANGE THIS ORDER, THEN YOUR CODE WON’T PASS ON GRADESCOPE AUTOGRADER
         */

        this.userName = username;
        this.displayName = displayName;
        this.num_subscriber = num_subscriber;
        this.num_video = num_video;
    }

    public String toText(){
        /*
            Takes no arguments
            returns a string which contains the fullname followed by the username of the user
            with a space between them and an "@" before the username
            
            for example: “UC San Diego Admissions @UCSanDiegoAdmissions”
         */

        return this.displayName+" @"+this.userName;
    }
}

class ExampleVideos{
    
    /* In order to test your classes and methods, find four videos from youtube.com with the following properties:
        Two of them are from the same user
        The other two are from two different users
        */

    // Users
    User user1 = new User("KenshiYonezu","Kenshi Yonezu 米津玄師", 7020000, 137);
    User user2 = new User("ghostandpals","GHOST",430000, 58);
    User user3 = new User("GuckkastenOfficial","Guckkasten Official", 38400, 64);

    // Two video from user1
    Video video1 = new Video("米津玄師 - Lemon Kenshi Yonezu", user1, 3100000, "SX_ViT4Ra7k"); // link: https://www.youtube.com/watch?v=SX_ViT4Ra7k , it can be represented by Video class
    Video video2 = new Video("米津玄師 - 感電 Kenshi Yonezu - KANDEN", user1, 3100000, "UFQEttrn6CQ"); // link: https://www.youtube.com/watch?v=UFQEttrn6CQ , it can be represented by Video class

    // video from user2
    Video video3 = new Video("Gumi Eng. & Yohioloid / The Chattering Lack of Common Sense [Original Song]", user2, 190000, "ksW7SuH6IAs"); // link: https://www.youtube.com/watch?v=ksW7SuH6IAs , it can be represented by Video class

    // video from user3
    Video video4 = new Video("Guckkasten 국카스텐 [사냥] Music Video", user3, 8200, "kuvQxTq3ccI"); // link: https://www.youtube.com/watch?v=kuvQxTq3ccI , it can be represented by Video class

    // 1 testing longerThan method in Video class
    boolean longerThan_test1 = video2.longerThan(video1);
    boolean longerThan_test2 = video1.longerThan(video3);

    // 2 testing moreLikes method in Video class
    boolean moreLikes_test1 = video2.moreLikes(video3);
    boolean moreLikes_test2 = video4.moreLikes(video2);

    // 3 testing toText method in Video class
    String toTextVideo_test1 = video3.toText();
    String toTextVideo_test2 = video4.toText();

    // 4 testing toLink method in Video class
    String toLink_test1 = video1.toLink();
    String toLink_test2 = video2.toLink();

    // 5 testing toText method in User class
    String toTextUser_test1 = user1.toText();
    String toTextUser_test2 = user2.toText();
    String toTextUser_test3 = user3.toText();


    // // 1 testing longerThan method in Video class
    // System.out.println(yellow+"Testing longerThan method"+exit);
    // System.out.println("-".repeat(10));
    // System.out.println(video2.longerThan(video1));
    // System.out.println(video1.longerThan(video3));
    // System.out.println("-".repeat(10));
    // System.out.println("");

    // // 2 testing moreLikes method in Video class
    // System.out.println(yellow+"Testing moreLikes method"+exit);
    // System.out.println("-".repeat(10));
    // System.out.println(video2.moreLikes(video3));
    // System.out.println(video4.moreLikes(video2));
    // System.out.println("-".repeat(10));
    // System.out.println("");

    // // 3 testing toText method in Video class
    // System.out.println(yellow+"Testing toText method"+exit);
    // System.out.println("-".repeat(10));
    // System.out.println(video3.toText());
    // System.out.println(video4.toText());
    // System.out.println("-".repeat(10));
    // System.out.println("");

    // // 4 testing toLink method in Video class
    // System.out.println(yellow+"Testing toLink method"+exit);
    // System.out.println("-".repeat(10));
    // System.out.println(video1.toLink());
    // System.out.println(video4.toLink());
    // System.out.println("-".repeat(10));
    // System.out.println("");

    // // 5 testing toText method in User class
    // System.out.println(yellow+"Testing toText method (User)"+exit);
    // System.out.println("-".repeat(10));
    // System.out.println(user1.toText());
    // System.out.println(user2.toText());
    // System.out.println(user3.toText());
    // System.out.println("-".repeat(10));
        
}
