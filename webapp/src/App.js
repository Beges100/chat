// ChatApp.js

import React, { useState } from 'react';
import MessageList from './MessageList';


function ChatApp() {
    const [messages, setMessages] = useState([]);
    const [text, setText] = useState('');
    const [sender, setSender] = useState('');

    const addMessage = (message) => {
        setMessages([...messages, message]);
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        if (!text.trim() || !sender.trim()) return;
        addMessage({ text, sender });
        setText('');
    };

    return (
        <div className="chat-app">
            <form className="message-input" onSubmit={handleSubmit}>
                <input
                    type="text"
                    placeholder="Your Name"
                    value={sender}
                    onChange={(e) => setSender(e.target.value)}
                />
                <input
                    type="text"
                    placeholder="Type your message"
                    value={text}
                    onChange={(e) => setText(e.target.value)}
                />
                <button type="submit">Send</button>
            </form>
            <h1>React Chat App</h1>
            <MessageList messages={messages} />
            {/* <div className="message-list">
                {messages.map((message, index) => (
                    <div key={index} className="message-list">
                        <span className="message-text">{message.text}</span>
                        <span className="message-sender">{message.sender}</span>
                    </div>
                ))}
            </div> */}
        </div>
    );
}

export default ChatApp;
