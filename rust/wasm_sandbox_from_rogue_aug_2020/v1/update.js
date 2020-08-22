
export function update_message(message) {
    console.log(`TRACER hello from update message: ${message.message}`);
    document.getElementById("engine-id").textContent = message.engine_id;
    document.getElementById("message-id").textContent = message.message_id;
    document.getElementById("message").textContent = message.message;
}
