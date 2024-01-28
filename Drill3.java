import java.util.*;

class VideoComment{
    String text;
    int likes;
    int replies;

    VideoComment(String text, int likes, int replies){
        this.text = text;
        this.likes = likes;
        this.replies = replies;
    }

    VideoComment(){}
    
    boolean hasMention(String username){
    /*
        takes a String called username
        checks if the string @ followed by that username apppears in the Comment contents
        returning true if it does and false otherwise.
        (There are some interesting cases for this method.
        For example, to check if we have the username “dummy” in “@dummy1 @dummy2”,
            hasMention() should return false in this case because username dummy1 and dummy2 is not the same as dummy,
            while “hello @dummy world” and “CSE 11 is a cool class @dummy” should return true)
    */
        ArrayList<String> text_AList = new ArrayList<String>(Arrays.asList(text.split(" ")));;
        if(text_AList.contains("@"+username)){ return true; }
        return false;
    }

    boolean hasReply(){
        // which takes no arguments and returns true if the comment has one or more replies, false otherwise.
        if(replies==0){return false;}
        return true;
    }


    String firstMention(){
        /*
         firstMention
         takes no arguments
         returns a String
            containing the substring between the first appearance of the @ character in the text
            and the first space character after that.
            Do not include the @ character and the space character.
            If there is no space after the @, or if there’s no @, then an empty string "" should be returned.
         */

        ArrayList<String> text_AList = new ArrayList<String>(Arrays.asList(text.split(" ")));;
        for(int loc=0;loc<text_AList.size()-1;loc++ ){
            String elem = text_AList.get(loc);

            if(elem.contains("@")){
                return elem.substring(1, elem.length()).strip();
            }
        }

        return "";
        
    }
    
     
}

class CommentReply{
    /*
     four fields:
        one called replyTo of type VideoComment,
        one called text of type String,
        one called likes of type int,
        one field called replies of type int.
        
        Give it a constructor of four arguments that initializes these fields.
        
        In it, write the following methods:
        morePopularReply
            takes no arguments
            returns true if this CommentReply has more likes than the VideoComment it is replying to
            
        allLikes
            takes no arguments
            returns the sum of the likes on this CommentReply and on the VideoComment it is replying to
        
        hasMention 
            takes a String called username
            and checks if the string @ followed by that username apppears
            in this CommentReply’s contents or in the VideoComment that is being replied to.
     */

    VideoComment replyto;
    String text;
    int likes;
    int replies;

    CommentReply(VideoComment replyto, String text, int likes, int replies){
        this.replyto = replyto;
        this.text = text;
        this.likes = likes;
        this.replies = replies;
    }

    boolean morePopularReply(){
        if(likes>replyto.likes){return true;}
        return false;
    }

    int allLikes(){
        return replies+replyto.replies;
    }

    boolean hasMention(String username){
        ArrayList<String> text_AList = new ArrayList<String>(Arrays.asList(text.split(" ")));;
        if(replyto.hasMention(username)){return true;}
        if(text_AList.contains("@"+username)){ return true; }
        return false;
    }


}
class Drill3{
    VideoComment test1 = new VideoComment("Hello", 1,0);
    boolean test1_1 = test1.hasMention("adfs");
    boolean test1_2 = test1.hasReply();
    String test1_3 = test1.firstMention();

    VideoComment test2 = new VideoComment("Hello @adfs aaldfjk", 1,1);
    boolean test2_1 = test2.hasMention("adfs");
    boolean test2_2 = test2.hasReply();
    String test2_3 = test2.firstMention();

    VideoComment test3 = new VideoComment("Hello @adfs111aaldfjk", 1,1);
    boolean test3_1 = test3.hasMention("adfs");
    boolean test3_2 = test3.hasReply();
    String test3_3 = test3.firstMention();

    CommentReply test4 = new CommentReply(test3, "@adfs HAHAHAHAHAHA", 12, 12);
    boolean test4_1 = test4.morePopularReply();
    int test4_2 = test4.allLikes();
    boolean test4_3 = test4.hasMention("adfs");
}