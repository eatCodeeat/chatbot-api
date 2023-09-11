package cn.bugstack.chatbot.api.test;

import cn.bugstack.chatbot.api.domain.zsxq.IzsxqApi;
import cn.bugstack.chatbot.api.domain.zsxq.model.aggregates.UnAnswerQuestionsAggregates;
import cn.bugstack.chatbot.api.domain.zsxq.model.vo.Question;
import cn.bugstack.chatbot.api.domain.zsxq.model.vo.Topics;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootRunTest {
    private Logger logger = LoggerFactory.getLogger(SpringBootRunTest.class);

   // @Value("${chatbot-api.groupId}")

    private String groupId = "28885518425541";
   // @Value("${chatbot-api.cookie}")
    private String cookie = "zsxq_access_token=F09814F9-DE90-8702-7D3B-905EFAC1B5BE_AE75EDD3DF5E8DDA; abtest_env=product; zsxqsessionid=66dd4e84f4944af0df36c7e7ee037d32; sajssdk_2015_cross_new_user=1; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%22184524585185522%22%2C%22first_id%22%3A%2218a81fe032e819-065bef6cca8d73-7f5d547e-1327104-18a81fe032fdbf%22%2C%22props%22%3A%7B%7D%2C%22identities%22%3A%22eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMThhODFmZTAzMmU4MTktMDY1YmVmNmNjYThkNzMtN2Y1ZDU0N2UtMTMyNzEwNC0xOGE4MWZlMDMyZmRiZiIsIiRpZGVudGl0eV9sb2dpbl9pZCI6IjE4NDUyNDU4NTE4NTUyMiJ9%22%2C%22history_login_id%22%3A%7B%22name%22%3A%22%24identity_login_id%22%2C%22value%22%3A%22184524585185522%22%7D%2C%22%24device_id%22%3A%2218a81fe032e819-065bef6cca8d73-7f5d547e-1327104-18a81fe032fdbf%22%7D";

    @Resource
    private IzsxqApi zsxqApi;

    @Test
    public void test_zsxqApi() throws IOException{
        UnAnswerQuestionsAggregates unAnswerQuestionsAggregates = zsxqApi.queryUnAnswerQuestionsTopicId(groupId, cookie);
        logger.info("测试结果：{}", JSON.toJSONString(unAnswerQuestionsAggregates));

        List<Topics> topics = unAnswerQuestionsAggregates.getResp_data().getTopics();
        logger.info("有没有啊啊啊啊啊 ;{}",topics);
        for (Topics topic : topics) {
            String topicId = topic.getTopic_id();
            logger.info("topicId:{} text:{}",topicId,text);
        }
    }
}
