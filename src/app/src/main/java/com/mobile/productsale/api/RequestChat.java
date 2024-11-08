package com.mobile.productsale.api;

import com.mobile.productsale.model.CartDTO;
import com.mobile.productsale.model.ChatMessage;
import com.mobile.productsale.model.ChatMessageDTO;
import com.mobile.productsale.model.ResponseMessageDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RequestChat {
    @GET("api/ChatMessage/{roomId}")
    Call<List<ChatMessage>> FetchMessageFromRoom(@Path("roomId") int roomId);

    @POST("api/ChatMessage")
    Call<ResponseMessageDTO> SendMessageToRoom(@Body ChatMessageDTO model);
}
