package com.dayang.share4;

import net.sf.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void test() throws Exception {
        JSONObject user = new JSONObject();
        user.put("name", "张三");
        user.put("phone", "123456");
        String userId = "123123123";
        //增加
        this.mockMvc.perform(post("/users/" + userId + "").contentType("application/json").content(user.toString()))
                .andDo(print())
                .andExpect(status().isOk());

        //检索
        this.mockMvc.perform(get("/users/" + userId + ""))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(userId));

        this.mockMvc.perform(get("/users").requestAttr("name", "张"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
