package com.mobile.productsale.services;

import com.mobile.productsale.api.RequestCart;
import com.mobile.productsale.api.RequestChat;
import com.mobile.productsale.model.CartDTO;
import com.mobile.productsale.model.ChatMessage;
import com.mobile.productsale.model.ChatMessageDTO;
import com.mobile.productsale.model.ResponseMessageDTO;
import com.mobile.productsale.network.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class ChatService {
    private final RequestChat chatApi;

    public ChatService() {
        chatApi = ApiClient.getRetrofitInstance().create(RequestChat.class);
    }

    public void fetchMessageFromRoom(int roomId, Callback<List<ChatMessage>> callback) {
        Call<List<ChatMessage>> call = chatApi.FetchMessageFromRoom(roomId);
        call.enqueue(callback);
    }

    public void sendMessageToRoom(ChatMessageDTO model, Callback<ResponseMessageDTO> callback) {
        Call<ResponseMessageDTO> call = chatApi.SendMessageToRoom(model);
        call.enqueue(callback);
    }
}
