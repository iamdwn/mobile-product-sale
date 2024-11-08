//package com.mobile.productsale.api;
//
//import com.mobile.productsale.model.ProductDTO;
//
//import java.util.List;
//
//import retrofit2.Call;
//import retrofit2.http.GET;
//
//public interface RequestProduct {
//    @GET("api/Product")
//    Call<ProductDTO> getProduct();
//}

package com.mobile.productsale.api;

import com.mobile.productsale.model.ProductDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RequestProduct {
    @GET("api/Product")
    Call<List<ProductDTO>> getProduct();
}
