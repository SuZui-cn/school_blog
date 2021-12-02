package com.jueding;

import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.jueding.entity.Admin;
import com.jueding.entity.Article;
import com.jueding.mapper.AdminMapper;
import com.mysql.cj.result.LocalDateTimeValueFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Slf4j
@SpringBootTest
class SchoolBlogApplicationTests {
    @Autowired
    private AdminMapper adminMapper;

    @Test
    public void test(){
        Article article = new Article();
        LocalDate now = LocalDate.now();
        LocalDateTime now1 = LocalDateTime.now();
        article.setAtDate(now1);
        log.info(String.valueOf(article));
    }

}
