package com.mycompany.stock.service;

import com.mycompany.stock.exception.StockException;
import com.mycompany.stock.domain.StockCreateRequest;
import com.mycompany.stock.domain.StockUpdateRequest;
import com.mycompany.stock.model.Stock;
import com.mycompany.stock.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StockService {

    private final StockRepository stockRepository;

    /**
     * Arg-constructor.
     *
     * @param stockRepository - the {@link StockRepository} to set
     */
    @Autowired
    public StockService(final StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    /**
     * This method returns all the stocks.
     *
     * @return - the {@link List} of {@link Stock}
     */
    public List<Stock> getAllStocks() {
        return stockRepository.findAll();
    }

    /**
     * This method returns stock by id.
     *
     * @param - the {@link String} id of the required stock
     * @return - the {@link List} of {@link Stock}
     */
    public Stock getStockById(final String id) {
        return stockRepository.findById(id).orElse(null);
    }

    /**
     * This methods creates an entity object from the request and persists the stock into the database.
     *
     * @param stockCreateRequest - the {@link StockCreateRequest} request
     */
    public void createStock(final StockCreateRequest stockCreateRequest) {
        final Stock stock = createStockEntity(stockCreateRequest);
        stockRepository.save(stock);
    }

    /**
     * This methods creates an entity object from the request and persists the stock into the database.
     *
     * @param stockUpdateRequest - the {@link StockUpdateRequest} request
     */
    public void updateStock(final StockUpdateRequest stockUpdateRequest) throws StockException{
        final Stock stock = getStockById(stockUpdateRequest.getStockId());
        if(null == stock) {
            throw new StockException("Stock not found", HttpStatus.NOT_FOUND);
        }
        updateStockEntity(stock, stockUpdateRequest);
        stockRepository.save(stock);
    }

    /**
     * This method creates an entity object out of the stock create request.
     *
     * @param stockCreateRequest - the {@link StockCreateRequest} request
     * @return
     */
    private Stock createStockEntity(StockCreateRequest stockCreateRequest) {

        final Stock stock = new Stock();
        stock.setCurrency(stockCreateRequest.getCurrency());
        stock.setStockName(stockCreateRequest.getStockName());
        stock.setCurrentPrice(stockCreateRequest.getCurrentPrice());
        stock.setLastUpdate(LocalDateTime.now());
        stock.setCreatedAt(LocalDateTime.now());
        return stock;
    }

    /**
     * This method creates an entity object out of the stock update request.
     *
     * @return
     */
    private void updateStockEntity(final Stock stock, final StockUpdateRequest stockUpdateRequest) {

        stock.setCurrency(stockUpdateRequest.getCurrency());
        stock.setCurrentPrice(stockUpdateRequest.getCurrentPrice());
        stock.setLastUpdate(LocalDateTime.now());
    }
}
