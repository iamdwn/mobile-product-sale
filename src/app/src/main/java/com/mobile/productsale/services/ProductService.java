//package com.mobile.productsale.services;
//
//import com.mobile.productsale.api.RequestProduct;
//import com.mobile.productsale.model.ProductDTO;
//import com.mobile.productsale.network.ApiClient;
//
//import java.util.List;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//
//public class ProductService {
//    private final RequestProduct productApi;
//
//    public ProductService() {
//        productApi = ApiClient.getRetrofitInstance().create(RequestProduct.class);
//    }
//
//    public List<ProductDTO> getProduct(Callback<ProductDTO> callback) {
//        Call<ProductDTO> call = productApi.getProduct();
//        call.enqueue(callback);
//        return null;
//    }
//
//}
package com.mobile.productsale.services;

import com.mobile.productsale.api.RequestProduct;
import com.mobile.productsale.model.ProductDTO;
import com.mobile.productsale.network.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class ProductService {
    private final RequestProduct productApi;

    public ProductService() {
        productApi = ApiClient.getRetrofitInstance().create(RequestProduct.class);
    }

    public void getProduct(Callback<List<ProductDTO>> callback) { // Sửa kiểu trả về thành List<ProductDTO>
        Call<List<ProductDTO>> call = productApi.getProduct();
        call.enqueue(callback);
    }
}
