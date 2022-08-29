package com.manikala.shop.endpoint;

import com.manikala.shop.dto.ProductDTO;
import com.manikala.shop.service.ProductService;
import com.manikala.shop.ws.products.GetProductsRequest;
import com.manikala.shop.ws.products.GetProductsResponse;
import com.manikala.shop.ws.products.ProductsWS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import javax.xml.datatype.DatatypeConfigurationException;

@Endpoint // конечная точна в url адресе //Endpoint – класс, который будет отвечать за обработку входящих запросов (своего рода точка входа).
public class ProductsEndpoint {

    public static final String NAMESPACE_URL = "http://manikala.com/shop/ws/products";

    private final ProductService productService;

    @Autowired
    public ProductsEndpoint(ProductService productService) {
        this.productService = productService;
    }

    @PayloadRoot(namespace = NAMESPACE_URL, localPart = "getProductsRequest") //@PayloadRoot перед методом определяет, при получении какого запроса будет вызван данный метод.
    @ResponsePayload //Метод также снабжен комментарием , указывающим, что возвращаемое значение используется в качестве полезной нагрузки ответного сообщени
    public GetProductsResponse getGreeting(@RequestPayload GetProductsRequest request)
            throws DatatypeConfigurationException {
        GetProductsResponse response = new GetProductsResponse();
        productService.getAll()
                .forEach(dto -> response.getProducts().add(createProductWS(dto)));
        return response;
    }

    private ProductsWS createProductWS(ProductDTO dto){
        ProductsWS ws = new ProductsWS();
        ws.setId(dto.getId());
        ws.setPrice(Double.parseDouble(dto.getPrice().toString()));
        ws.setTitle(dto.getTitle());
        return ws;
    }
}
