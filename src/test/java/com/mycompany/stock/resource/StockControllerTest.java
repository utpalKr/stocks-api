package com.mycompany.stock.resource;

import com.mycompany.stock.model.Stock;
import com.mycompany.stock.service.StockService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RunWith(MockitoJUnitRunner.class)
public class StockControllerTest {

    @InjectMocks
    StockController stockController;

    @Mock
    StockService stockService;

    @Before
    public void init() {
        Stock stock = new Stock();
        stock.setId("stock-id");
        stock.setStockName("stock-name");
        stock.setCurrentPrice(100.0f);
        stock.setCurrency("EUR");
        Mockito.when(stockService.getStockById("stock-id")).thenReturn(stock);
    }

    @Test
    public void shouldGetAllStocks() {

       ResponseEntity<Stock> stocksResponse =  stockController.getAllStocks();
        Assert.assertNotNull(stocksResponse);
        Assert.assertEquals(HttpStatus.OK, stocksResponse.getStatusCode());
    }

    @Test
    public void shouldGetStockById() {

        ResponseEntity<Stock> stocksResponse =  stockController.getStockById("stock-id");
        Assert.assertNotNull(stocksResponse);
        Assert.assertEquals(HttpStatus.OK, stocksResponse.getStatusCode());
        Assert.assertEquals( "stock-id", stocksResponse.getBody().getId());
        Assert.assertEquals( "EUR", stocksResponse.getBody().getCurrency());
        Assert.assertEquals( "stock-name", stocksResponse.getBody().getStockName());
        Assert.assertEquals( Float.valueOf(100.0f), stocksResponse.getBody().getCurrentPrice());

    }
}
