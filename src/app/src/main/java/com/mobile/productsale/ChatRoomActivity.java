package com.mobile.productsale;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mobile.productsale.model.ChatMessage;
import com.mobile.productsale.model.ChatMessageDTO;
import com.mobile.productsale.model.ResponseMessageDTO;
import com.mobile.productsale.services.ChatService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatRoomActivity extends AppCompatActivity {
    private RecyclerView recyclerViewChat;
    private ChatMessageAdapter chatMessageAdapter;
    private List<ChatMessage> chatMessages;
    private EditText editTextMessage;
    private Button buttonSend;
    private ChatService chatService;
    private int roomId = 1; // Assuming room ID is 1 for example

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);

        recyclerViewChat = findViewById(R.id.recyclerViewChat);
        editTextMessage = findViewById(R.id.editTextMessage);
        buttonSend = findViewById(R.id.buttonSend);
        chatService = new ChatService();

        chatMessages = new ArrayList<>();
        chatMessageAdapter = new ChatMessageAdapter(chatMessages);
        recyclerViewChat.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewChat.setAdapter(chatMessageAdapter);

        // Fetch initial messages for the room
        fetchMessages();

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage();
            }
        });
    }

    private void fetchMessages() {
        chatService.fetchMessageFromRoom(roomId, new Callback<List<ChatMessage>>() {
            @Override
            public void onResponse(Call<List<ChatMessage>> call, Response<List<ChatMessage>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Clear the existing list and add the fetched messages
                    chatMessages.clear();
                    for (ChatMessage chat : response.body()) {
                        chatMessages.add(chat);
                    }
                    chatMessageAdapter.notifyDataSetChanged();
                    recyclerViewChat.scrollToPosition(chatMessages.size() - 1);
                }
            }

            @Override
            public void onFailure(Call<List<ChatMessage>> call, Throwable t) {
                Toast.makeText(ChatRoomActivity.this, "Failed to load messages", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void sendMessage() {
        String messageText = editTextMessage.getText().toString().trim();
        if (!messageText.isEmpty()) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault());
            dateFormat.setTimeZone(TimeZone.getTimeZone("UTC")); // Set timezone to UTC
            String formattedDate = dateFormat.format(new Date());
            // Create a new ChatMessageDTO object to send to the server
            ChatMessageDTO newMessageDTO = new ChatMessageDTO(1, 1, messageText, formattedDate);

            // Send message through the ChatService
            chatService.sendMessageToRoom(newMessageDTO, new Callback<ResponseMessageDTO>() {
                @Override
                public void onResponse(Call<ResponseMessageDTO> call, Response<ResponseMessageDTO> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        // Add the message to the local list and update RecyclerView
//                        ChatMessage newMessage = new ChatMessage(0, 1, roomId, messageText, new Date().toString());
//                        chatMessages.add(newMessage);
//                        chatMessageAdapter.notifyItemInserted(chatMessages.size() - 1);
//                        recyclerViewChat.scrollToPosition(chatMessages.size() - 1);
                        fetchMessages();

                        // Clear the message input
                        editTextMessage.setText("");
                    } else {
                        Toast.makeText(ChatRoomActivity.this, "Failed to send message", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseMessageDTO> call, Throwable t) {
                    Toast.makeText(ChatRoomActivity.this, "Failed to send message", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
