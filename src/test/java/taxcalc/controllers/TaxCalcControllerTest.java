package taxcalc.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TaxCalcControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void calculateTest() throws Exception {
        String sourceJson = "{ \"ID\":1, \"timestamp\":\"2018-11-3 10:00\", \"items\":[ " +
                "{ \"ID\":10, \"description\":\"none1\", \"pretaxAmount\":10.0, \"taxCategory\":\"Category #1\", \"totalAmount\":10.0 }" +
                " ] }";

        String targetJson = "{ \"ID\":1, \"timestamp\":\"2018-11-3 10:00\", \"items\":[ " +
                "{ \"ID\":10, \"description\":\"none1\", \"pretaxAmount\":10.0, \"taxCategory\":\"Category #1\", \"totalAmount\":11.0 }" +
                " ] }";

        this.mockMvc.perform(post("/api/v1/calc").param("correlationId", "something")
                .contentType(MediaType.APPLICATION_JSON)
                .content(sourceJson))
                .andExpect(status().isOk())
                .andExpect(content().json(targetJson));
    }
}
