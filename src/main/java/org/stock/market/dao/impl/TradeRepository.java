package org.stock.market.dao.impl;

import org.stock.market.dao.StockMarketRepoBase;
import org.stock.market.model.entity.trade.Trade;
import org.springframework.stereotype.Repository;


/**
 * Repository to store trades
 */
@Repository
public class TradeRepository extends StockMarketRepoBase<Trade,String> {

}
