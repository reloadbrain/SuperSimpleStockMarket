package org.stock.market.dao.impl;

import org.springframework.stereotype.Repository;
import org.stock.market.dao.StockMarketRepoBase;
import org.stock.market.model.entity.stock.Stock;

/**
 * Repository to store stocks
 */
@Repository
public class StockRepository extends StockMarketRepoBase<Stock, String> {

}
