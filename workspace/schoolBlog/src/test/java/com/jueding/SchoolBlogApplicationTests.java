package com.jueding;

import com.jueding.entity.Article;
import com.jueding.entity.User;
import com.jueding.mapper.AdminMapper;
import com.jueding.service.ArticleService;
import com.jueding.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@SpringBootTest
class SchoolBlogApplicationTests {
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private UserService userService;

    @Test
    public void test() {
        Article article = new Article();
        article.setAtDate(LocalDateTime.now());
        article.setAtTitle("测试用例文章");
        article.setAtAbstract("这是一篇测试用例");
        article.setAtContent("第一次认识王赟老师是在知乎上，当时看到他的问答，觉得清晰受用。后来发现他居然在图灵出书了，小编甚是激动呀！\n" +
                "\n" +
                "翻了两天，终于把这本书都看完了。虽然里面部分题目的知识点已经忘得差不多了，但是有些题目看到王老师的解题思路，忍不住拍大腿：“居然还能这么解！”看书过程中还不停地点头，“原来是这样”“我咋就没想到”“不错不错这次会了”......就这样沉醉了两天，看到最后一页的时候竟然有点忧伤，因为后边没有了，也确实没看够。\n" +
                "\n" +
                "就是这样一本小书，不那么厚，但是内容绝对会让你意犹未尽，并且不断发出“柳岸花明又一村”的感叹。如果你对数学有兴趣不妨来跟着作者的思路一起研究，保证你收获满满。\n" +
                "\n" +
                "如此有意思的一本书，我们来近距离了解一下吧~，但凡遇到数学问题，总是让一部分人很挠头。有时候很羡慕那些数学好的学霸，他们总能轻轻松松搞定一切数学问题，更重要的是还能将复杂的问题用最简单的话讲解清楚。而王赟就是这样一位学霸。");
        article.setAtType("测试文章");
        article.setUId(11);
        List<User> user = userService.findUserById(11);
        if (user.size() != 0) {
            boolean save = articleService.save(article);
            log.info("文章添加测试" + save);
        } else {
            log.info("文章添加失败");
        }

    }

}
