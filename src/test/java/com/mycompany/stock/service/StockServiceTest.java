package com.mycompany.stock.service;

import com.mycompany.stock.model.Stock;
import com.mycompany.stock.repository.StockRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class StockServiceTest {

    @InjectMocks
    StockService stockService;

    @Mock
    StockRepository stockRepository;

    List<Stock> stocks = new ArrayList<>();

    @Before
    public void init() {
        Stock stock = new Stock();
        stock.setId("stock-id");
        stock.setStockName("stock-name");
        stock.setCurrentPrice(100.0f);
        stock.setCurrency("EUR");
        stocks.add(stock);
        Mockito.when(stockRepository.findAll()).thenReturn(stocks);
        Mockito.when(stockRepository.findById("stock-id")).thenReturn(Optional.of(stock));

    }

    @Test
    public void shouldGetAllStocks() {

        List<Stock> stockList = stockService.getAllStocks();
        Assert.assertNotNull(stockList);
        Assert.assertEquals(1, stockList.size());
        Assert.assertEquals("stock-name", stockList.get(0).getStockName());
        Assert.assertEquals("stock-id", stockList.get(0).getId());
        Assert.assertEquals("EUR", stockList.get(0).getCurrency());
        Assert.assertEquals(Float.valueOf(100.0f), stockList.get(0).getCurrentPrice());

    }

    @Test
    public void shouldGetStockById() {

        Stock stock = stockService.getStockById("stock-id");
        Assert.assertNotNull(stock);
        Assert.assertEquals("stock-name", stock.getStockName());
        Assert.assertEquals("stock-id", stock.getId());
        Assert.assertEquals("EUR", stock.getCurrency());
        Assert.assertEquals(Float.valueOf(100.0f), stock.getCurrentPrice());

    }
}
