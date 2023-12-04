/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package websocket;

/**
 *
 * @author dawmi
 */


import modelo.MessageModel;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.websocket.EncodeException;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;


@ServerEndpoint(value = "/chat", decoders = MessageModelDecoder.class, encoders = MessageModelEncoder.class)
public class ChatWebsocketEndpoint {
    
    private static Set<Session> sessions = Collections.synchronizedSet(new HashSet<Session>());
    

    @OnMessage
    public String onMessage(Session session, MessageModel message) {
        System.out.println("Handling message: " + message);
        for(Session s: sessions) {
            try {
                s.getBasicRemote().sendObject(message);
            } catch (IOException ex) {
                Logger.getLogger(ChatWebsocketEndpoint.class.getName()).log(Level.SEVERE, null, ex);
            } catch (EncodeException ex) {
                Logger.getLogger(ChatWebsocketEndpoint.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    
    @OnOpen
    public void onOpen(Session session) {
        System.out.println("On open: " + session.getId());
        sessions.add(session);
        
    }
    
    @OnClose
    public void onClose(Session session) {
        System.out.println("On close: " + session.getId());
        sessions.remove(session);
    }
    
}