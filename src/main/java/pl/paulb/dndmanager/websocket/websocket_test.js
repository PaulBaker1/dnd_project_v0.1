import ws from 'k6/ws';
import {check, sleep} from 'k6';

// Configuration for the load test
export const options = {
    stages: [
        {duration: '30s', target: 50}, // Ramp up to 50 users over 30 seconds
        {duration: '1m', target: 100}, // Keep 100 users for 1 minute
        {duration: '30s', target: 0},  // Ramp down to 0 users
    ],
};

// Test execution
export default function () {
    const url = 'ws://localhost:8080/ws';  // Replace with your WebSocket server URL
    const params = {tags: {my_tag: 'test'}};

    // Connecting to WebSocket
    const res = ws.connect(url, params, function (socket) {
        socket.on('open', function open() {
            console.log('Connected to WebSocket server');
            // Send a message to the WebSocket server
            socket.send(JSON.stringify({message: 'Hello from client'}));

            // Close connection after sending the message
            socket.close();
        });

        socket.on('message', function (data) {
            console.log('Received message: ' + data);
        });

        socket.on('close', function () {
            console.log('WebSocket connection closed');
        });

        socket.on('error', function (e) {
            console.log('WebSocket error:', e);
        });
    });

    check(res, {'Connected successfully': (r) => r && r.status === 101});
    sleep(1);  // Simulate a small delay for the next user
}
