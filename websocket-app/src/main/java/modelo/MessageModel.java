/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author dawmi
 */
public class MessageModel {
    
    /**
     * La clase messageModel se encarga de representar la estructura de un mensaje.
     *
     * @param messageId Identificador único del mensaje.
     * @param userId    Identificador único del usuario que envió el mensaje.
     * @param content   Contenido del mensaje.
     * @param timestamp Fecha en la que se envió el mensaje.
     * 
     */
    
    private final String messageId; // Identificador único de mensaje en la base de datos
    private final String userId; // Este campo representa el remitente del mensaje
    private String content;
    private Date timestamp; //La fecha en la que se ha enviado el mensaje
    

    public MessageModel(String messageId, String content, Date timestamp, String userId) {
        this.messageId = messageId;
        this.content = content;
        this.timestamp = timestamp;
        this.userId = userId;
    }

    public String getMessageId() {
        return messageId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getUserId() {
        return userId;
    }


     @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = dateFormat.format(timestamp);

        return "Message ID: " + messageId +
               "\nContent: " + content +
               "\nTimestamp: " + formattedDate +
               "\nUser ID: " + userId;
    }
}
