import React from 'react';
import './Message.css'; // Import your CSS file for styling

function Message({ text, sender }) {
    return (
        <div className="message-container">
            <div className="message-sender">{sender}</div>
            <div className="message-bubble">{text}</div>
        </div>
    );
}

export default Message;