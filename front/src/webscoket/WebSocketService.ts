// src/services/WebSocketService.ts
import { Client } from '@stomp/stompjs';
import SockJS from 'sockjs-client';
import { CodeMessage } from '../types/Code';

class WebSocketService {
  private client: Client;

  constructor() {
    this.client = new Client({
      brokerURL: 'http://localhost:8080/ws', // Replace with your WebSocket endpoint
      connectHeaders: {
        // Add cookies here (if any are available in the document)
        Cookie: document.cookie, // Sends all cookies available for the domain
      },
      debug: (str) => {
        console.log(str);
      },
      reconnectDelay: 5000, // Retry delay if connection is lost
      webSocketFactory: () => new SockJS('http://localhost:8080/ws'), // WebSocket fallback using SockJS
    });
  }

  // Connect to WebSocket
  connect = (onConnect: () => void) => {
    this.client.onConnect = onConnect;
    this.client.activate();
  };

  // Subscribe to a specific topic
  subscribeToTopic = (roomId: number, language: string, callback: (message: any) => void) => {
    const destination = `/topic/roomCode/${roomId}/${language}`;
    this.client.subscribe(destination, (message) => {
      if (message.body) {
        callback(JSON.parse(message.body));
      }
    });
  };

  sendMessage = (message:CodeMessage) => {
    this.client.publish({
      destination: '/app/sendCodeLineOperation',
      body: JSON.stringify(message),
    });
  };

  disconnect = () => {
    this.client.deactivate();
  };
}

export default new WebSocketService();
