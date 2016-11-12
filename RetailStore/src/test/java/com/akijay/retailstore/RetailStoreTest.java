package com.akijay.retailstore;

//option-enter to choose import
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by vijay on 10/17/16.
 */

//Need both @AutoConfigureMockMvc together with @SpringBootTest to inject a MockMvc instance.

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class RetailStoreTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void testCreateStore() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Hello from RetailStore1")));
        }

}
