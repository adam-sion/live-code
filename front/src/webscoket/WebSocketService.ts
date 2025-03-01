import { Client, StompSubscription } from '@stomp/stompjs';
import SockJS from 'sockjs-client';

class WebSocketService {
  private client: Client;
  private subscriptions: Map<string, StompSubscription> = new Map(); // Store active subscriptions

  constructor() {
    this.client = new Client({
      brokerURL: `${import.meta.env.VITE_SERVER_URL}/ws`, // Replace with your WebSocket endpoint
      connectHeaders: {
        Cookie: document.cookie, // Sends all cookies available for the domain
      },
      debug: (str) => {
      },
      reconnectDelay: 5000, // Retry delay if connection is lost
      webSocketFactory: () => new SockJS(`${import.meta.env.VITE_SERVER_URL}/ws`), // WebSocket fallback using SockJS
    });
  }

  // Connect to WebSocket, only activates if not already active
  connect = (onConnect: () => void) => {
    if (!this.client.active) { // Only activate if not already active
      this.client.onConnect = onConnect;
      this.client.activate();
    } else {
      onConnect(); // Call the onConnect callback immediately if already active
    }
  };

  // Subscribe to a specific topic with dynamic destination
  subscribeToTopic = (destination: string, callback: (message: any) => void) => {
    if (!this.client.connected) {
      return;
    }
    // Unsubscribe from the same topic if already subscribed to avoid duplicate subscriptions
    this.unsubscribeFromTopic(destination);

    const subscription = this.client.subscribe(destination, (message) => {
      if (message.body) {
        callback(JSON.parse(message.body));
      }
    }); 

    // Store the new subscription
    this.subscriptions.set(destination, subscription);
  };

  // Unsubscribe from a specific topic
  unsubscribeFromTopic = (destination: string) => {
    if (this.subscriptions.has(destination)) {
      this.subscriptions.get(destination)?.unsubscribe(); // Unsubscribe from topic
      this.subscriptions.delete(destination); // Remove from subscriptions map
    }
  };

  // Send a message to a specific destination with dynamic body
  sendMessage = (destination: string, messageBody: any) => {
    this.client.publish({
      destination,
      body: JSON.stringify(messageBody),
    });
  };

  // Disconnect from WebSocket and clean up subscriptions
  disconnect = () => {
    this.subscriptions.forEach((subscription) => subscription.unsubscribe());
    this.subscriptions.clear();
    this.client.deactivate();
  };
}

export default new WebSocketService();
