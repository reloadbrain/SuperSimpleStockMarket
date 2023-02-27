package org.stock.market;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
@SpringBootTest
class StockMarketApplicationTest {

	@Test

	void contextLoads(ApplicationContext context) {
		assertThat(context).isNotNull();
	}

}
