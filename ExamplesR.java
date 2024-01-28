import java.util.*;

class R{
    String text;
    R r;

    R(String text, R r){
        this.text = text;
        this.r = r;
    }
}


class ExamplesR{
    // R example = R("hello", new R("world", new R(...))); -> impossible
    // there should be object R created first to initiallize R, which is impossible

    // CommentReply(VideoComment replyto, String text, int likes, int replies)
    // -> as it can be seen in the constructor, it requires VideoComment object to create new CommentReply object, thus CommentReply object that has CommentReply object as its "replyto" parameter
    // therefore, it is impossible to construct an example of reply of reply.
    
}