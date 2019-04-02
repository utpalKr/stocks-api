package com.mycompany.stock.resource;

import com.mycompany.stock.exception.StockException;
import com.mycompany.stock.domain.StockCreateRequest;
import com.mycompany.stock.domain.StockUpdateRequest;
import com.mycompany.stock.model.Stock;
import com.mycompany.stock.service.StockService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RestController
@Api(value = "Stocks Controller", description = "Operations on StockCreateRequest objects")
@RequestMapping("stocks")
@CrossOrigin
public class StockController {

    private final StockService stockService;

    @Autowired
    public StockController(final StockService stockService) {
        this.stockService = stockService;
        createStocks();
    }

    /**
     * This method returns all the stocks in the database.
     *
     * @return the {@link ResponseEntity} of type {@link Stock}
     */
    @ApiOperation(value = "Get all Stocks",
            response = Stock.class, produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping
    public ResponseEntity<Stock> getAllStocks() {
        return new ResponseEntity(stockService.getAllStocks(), HttpStatus.OK);
    }

    /**
     * This method returns the stock information of a given stock.
     *
     * @param stockId - the desired {@link String} stockId
     * @return the {@link ResponseEntity} of type {@link Stock}
     */
    @ApiOperation(value = "Get stock by stock Id",
            response = Stock.class, produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping(path = "{id}")
    public ResponseEntity<Stock> getStockById(@PathVariable("id") final String stockId) {
        return new ResponseEntity(stockService.getStockById(stockId), HttpStatus.OK);
    }

    /**
     * This method creates a new stock in the database.
     *
     * @param request - the {@link StockCreateRequest} request
     * @return - the {@link ResponseEntity} response
     */
    @PostMapping
    @ApiOperation(value = "Create a stock in the DB", response = String.class,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createStock(@RequestBody final StockCreateRequest request) {

        stockService.createStock(request);
        return ResponseEntity.status(HttpStatus.OK).body("Stock created successfully");
    }

    /**
     * This method updates the price of an existing stock in the database.
     * @param request
     * @return
     */
    @PutMapping
    @ApiOperation(value = "Updates the price of a stock in the DB", response = String.class,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateStock(@RequestBody final StockUpdateRequest request) {

        try {
            stockService.updateStock(request);
            return new ResponseEntity("Stock updated successfully", HttpStatus.OK);
        } catch (final StockException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(("Stock not found."));
        }
    }

    /**
     * Creating some default stocks in the database
     */
    private void createStocks() {
        StockCreateRequest request = StockCreateRequest.builder().currency("USD")
                .currentPrice(123.5f).stockName("Google").build();
        stockService.createStock(request);

        request = StockCreateRequest.builder().currency("USD")
                .currentPrice(100.5f).stockName("Yahoo").build();

        stockService.createStock(request);

        request = StockCreateRequest.builder().currency("USD")
                .currentPrice(90.0f).stockName("Microsoft").build();

        stockService.createStock(request);

        request = StockCreateRequest.builder().currency("USD")
                .currentPrice(50.5f).stockName("Coke").build();

        stockService.createStock(request);

    }
}
