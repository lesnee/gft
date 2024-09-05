package com.evaluation.gft;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class ApplicationIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @PersistenceContext
    private EntityManager entityManager;

    @BeforeEach
    public void setUp() {
        entityManager.createNativeQuery(
                "INSERT INTO prices (product_id, brand_id, start_date, end_date, price_list, priority, price, currency) " +
                    "VALUES ('35455', '1', '2020-06-14 00:00:00', '2020-12-31 23:59:59', 1, 0, 35.50, 'EUR')")
                .executeUpdate();
        entityManager.createNativeQuery(
                "INSERT INTO prices (product_id, brand_id, start_date, end_date, price_list, priority, price, currency) " +
                    "VALUES ('35455', '1', '2020-06-14 15:00:00', '2020-06-14 18:30:00', 2, 1, 25.45, 'EUR')")
                .executeUpdate();
        entityManager.createNativeQuery(
                "INSERT INTO prices (product_id, brand_id, start_date, end_date, price_list, priority, price, currency) " +
                    "VALUES ('35455', '1', '2020-06-15 00:00:00', '2020-06-15 11:00:00', 3, 1, 30.50, 'EUR')")
                .executeUpdate();
        entityManager.createNativeQuery(
                "INSERT INTO prices (product_id, brand_id, start_date, end_date, price_list, priority, price, currency) " +
                    "VALUES ('35455', '1', '2020-06-15 16:00:00', '2020-12-31 23:59:59', 4, 1, 38.95, 'EUR')")
                .executeUpdate();
    }

    @Test
    public void testGetProductPricesInformationScenario1() throws Exception {
        String url = "/products/price_info?productId=35455&brandId=1&requestedDate=2020-06-14-10.00.00";

        String expectedResponse = "{" +
                "\"productId\":\"35455\"," +
                "\"brandId\":\"1\"," +
                "\"priceList\":1," +
                "\"startDate\":\"2020-06-14T00:00:00\"," +
                "\"endDate\":\"2020-12-31T23:59:59\"," +
                "\"price\":35.50" +
                 "}";

        mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResponse));
    }

    @Test
    public void testGetProductPricesInformationScenario2() throws Exception {
        String url = "/products/price_info?productId=35455&brandId=1&requestedDate=2020-06-14-16.00.00";

        String expectedResponse = "{" +
                "\"productId\":\"35455\"," +
                "\"brandId\":\"1\"," +
                "\"priceList\":2," +
                "\"startDate\":\"2020-06-14T15:00:00\"," +
                "\"endDate\":\"2020-06-14T18:30:00\"," +
                "\"price\":25.45" +
                "}";

        mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResponse));
    }

    @Test
    public void testGetProductPricesInformationScenario3() throws Exception {
        String url = "/products/price_info?productId=35455&brandId=1&requestedDate=2020-06-14-21.00.00";
        String expectedResponse = "{" +
                "\"productId\":\"35455\"," +
                "\"brandId\":\"1\"," +
                "\"priceList\":1," +
                "\"startDate\":\"2020-06-14T00:00:00\"," +
                "\"endDate\":\"2020-12-31T23:59:59\"," +
                "\"price\":35.50" +
                "}";

        mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResponse));
    }

    @Test
    public void testGetProductPricesInformationScenario4() throws Exception {
        String url = "/products/price_info?productId=35455&brandId=1&requestedDate=2020-06-15-10.00.00";

        String expectedResponse = "{" +
                "\"productId\":\"35455\"," +
                "\"brandId\":\"1\"," +
                "\"priceList\":3," +
                "\"startDate\":\"2020-06-15T00:00:00\"," +
                "\"endDate\":\"2020-06-15T11:00:00\"," +
                "\"price\":30.50" +
                "}";

        mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResponse));
    }

    @Test
    public void testGetProductPricesInformationScenario5() throws Exception {
        String url = "/products/price_info?productId=35455&brandId=1&requestedDate=2020-06-16-21.00.00";

        String expectedResponse = "{" +
                "\"productId\":\"35455\"," +
                "\"brandId\":\"1\"," +
                "\"priceList\":4," +
                "\"startDate\":\"2020-06-15T16:00:00\"," +
                "\"endDate\":\"2020-12-31T23:59:59\"," +
                "\"price\":38.95" +
                "}";

        mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResponse));
    }

    @Test
    public void testGetProductPricesInformationNotFound() throws Exception {
        String url = "/products/price_info&productId=35455&brandId=2&requestedDate=2020-06-16-21.00.00";

        mockMvc.perform(get(url))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetProductPricesInformationParamMissing() throws Exception {
        String url = "/products/price_info&productId=35455&requestedDate=2020-06-16-21.00.00";

        mockMvc.perform(get(url))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetProductPricesInformationInvalidDate() throws Exception {
        String url = "/products/price_info&productId=35455&requestedDate=invalid";

        mockMvc.perform(get(url))
                .andExpect(status().isNotFound());
    }
}
