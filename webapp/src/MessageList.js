import React from 'react';
import Message from './Message';

function MessageList({ messages }) {
    return (
        <div className="message-list">
            {messages.map((message, index) => (
                <Message key={index} text={message.text} sender={message.sender} />
            ))}
        </div>
    );
}

export default MessageList;