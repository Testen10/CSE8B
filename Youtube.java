import tester.*;

class User{
    /*
        User - represents users, who are the authors of Videos/or just a User watching videos
            username, which is a String
            displayName, which is a String
     */
     String username;
     String displayName;

    User (String username, String displayName){
        this.username = username;
        this.displayName = displayName;
    }
}

interface Comment{
    /*
    To achieve this, we write an interface called Comment with three methods inside Youtube.java:

    public boolean isCommentByAuthor(User author);
    public int totalLikes();
    public String unrollCommentThread();
    */
    public boolean isCommentByAuthor(User author);
    public int totalLikes();
    public String unrollCommentThread();
}

class VideoComment implements Comment{
    /*
    VideoComment, implements Comment
        text, a String
        likes, an int
        replies an int
        author, a User
    */

    String text;
    int likes;
    int replies;
    User author;

    VideoComment(String text, int likes, int replies, User author){
        this.text = text;
        this.likes = likes;
        this.replies=replies;
        this.author = author;
    }

    public boolean isCommentByAuthor(User author){
        /*
         return true when the given author (in the argument) is the same as the author of this VideoComment
         false otherwise.
         */

        return author == this.author;
    }
    public int totalLikes(){
        /*
         totalLikes should return the number of likes on this VideoComment object
         */

         return this.likes;
    }
    public String unrollCommentThread(){
        /*
         unrollCommentThread should return a string in the following format:
            <author username>
            <l> likes; <r> replies
            <text>
         
         where <author> is replaced by the author’s name,
         <l> is replaced by the number of likes on the VideoComment,
         <r> is replaced by the number of replies on the VideoComment
         <text> is replaced by the text of the Comment
         The string should end in a new line ("\n" character).
         */

        return author.username+"\n"
                +likes+" likes; "+replies+" replies"
                +"\n"+text;
    }
    public int totalInteractions(){
        /*
          takes in no arguments
          return the sum of total number of replies and likes for the given Comment.
         */
        return replies+likes;
    }
}

class ReplyComment implements Comment{
    /*
    ReplyComment, which should implement Comment and has four fields:
        text, a String
        likes, an int
        author, a User
        replyTo, a Comment
    */
    String text;
    int likes;
    User author;
    Comment replyTo;
    public ReplyComment(String text, int likes, User author, Comment replyTo){
        this.text = text;
        this.likes = likes;
        this.author = author;
        this.replyTo = replyTo;
    }

    /* isCommentByAuthor
    return true when
        the given author is the same as the author of this ReplyComment
        and the replyTo Comment is also by the same author.
    */

    public boolean isCommentByAuthor(User author){
        return (this.author == author) && (replyTo.isCommentByAuthor(author));
    }


    /* totalLikes
    return the total number of likes on this ReplyComment object plus the total likes of its replyTo Comment.
    */
    public int totalLikes(){
        return this.likes + replyTo.totalLikes();
    }

    /*
     unrollCommentThread should return a string in the following format:

        <replyTo contents>
        <author username>
        <l> likes
        <text>
        where the bottom three parts are the same format as in VideoComment, and the first part is the unrolled version of the replyTo Comment. Similar to the method in VideoComment, this should also end in a new line character.
     */

    public String unrollCommentThread(){
        return replyTo.unrollCommentThread()
        +"\n"+author.username
        +"\n"+likes+" likes"
        +"\n"+text;
    }
}

class Youtube{
    User u1 = new User("tst1", "TU1");
    User u2 = new User("tst2", "TU2");

    VideoComment vc = new VideoComment("This is a great example to use the Tester Library!", 10, 5, u1);
    VideoComment vc2 = new VideoComment("Temp", 100, 51, u2);
    Comment rc1 = new ReplyComment("Yeah, I agree!", 7, u2, vc);
    Comment rc2 = new ReplyComment("Thanks for acknowledgment!", 4, u1, rc1);

    void testMethods(Tester t) {
        t.checkExpect(this.vc.totalLikes(), 10); //1
        t.checkExpect(this.vc2.totalLikes(), 100); //2
        t.checkExpect(this.rc1.totalLikes(), 17); //3
        t.checkExpect(this.rc2.totalLikes(), 21); //4

        t.checkExpect(this.vc.isCommentByAuthor(u1), true); //5
        t.checkExpect(this.vc2.isCommentByAuthor(u1), false); // 6
        t.checkExpect(this.rc1.isCommentByAuthor(u1), false); // 7
        t.checkExpect(this.rc2.isCommentByAuthor(u2), false); // 8

        t.checkExpect(this.vc.totalInteractions(),15); // 9
        t.checkExpect(this.vc2.totalInteractions(),151); // 10

        t.checkExpect(this.vc.unrollCommentThread(), 
        "tst1\n"
        +"10 likes; 5 replies\n"
        +"This is a great example to use the Tester Library!"); // 11

        t.checkExpect(this.vc2.unrollCommentThread(), 
        "tst2\n"
        +"100 likes; 51 replies\n"
        +"Temp"); // 12

        t.checkExpect(this.rc1.unrollCommentThread(),
        this.vc.unrollCommentThread()+"\n"
        +"tst2\n"
        +"7 likes\n"
        +"Yeah, I agree!"); // 13

        t.checkExpect(this.rc2.unrollCommentThread(),
        this.rc1.unrollCommentThread()+"\n"
        +"tst1\n"
        +"4 likes\n"
        +"Thanks for acknowledgment!"); // 14

    }

}