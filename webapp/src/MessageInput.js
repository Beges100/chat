import React, { useState } from 'react';

function MessageInput({ addMessage }) {
    const [text, setText] = useState('');
    const [sender, setSender] = useState('');

    const handleSubmit = (e) => {
        e.preventDefault();
        if (!text.trim() || !sender.trim()) return;
        addMessage({ text, sender });
        setText('');
    };

    return (
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
    );
}

export default MessageInput;