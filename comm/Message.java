package comm;

// ¿serializable?
public class Message implements MessageTypes {

    // type of message
    int messageType; 

    // content to the message
    Object messageContent; 

    /*
        Constructor that takes in both message type and its content
    */
    public Message (int msgType, Object content) {
        messageType = msgType; 
        content = content; 
    }

    /*
        Constructor that takes in only message type
    */
    public Message (int givenType) {
        this(type, null); 
    }
    
    /*
        Setter for messageType
    */
    public void setType(int msgType) {
        messageType = msgType;
    }

    /*
        Getter for messageType
    */
    public int getType() {
        return messageType; 
    }

    /*
        Setter for content
    */
    public void setContent(int content) {
        messageType = msgType;
    }

    /*
        Getter for content
    */
    public int getType() {
        return content; 
    }
}
