package cn.bugstack.chatbot.api.domain.zsxq.service;

import cn.bugstack.chatbot.api.domain.zsxq.IzsxqApi;
import cn.bugstack.chatbot.api.domain.zsxq.model.aggregates.UnAnswerQuestionsAggregates;
import cn.bugstack.chatbot.api.domain.zsxq.model.req.AnswerReq;
import cn.bugstack.chatbot.api.domain.zsxq.model.req.ReqData;
import cn.bugstack.chatbot.api.domain.zsxq.model.res.AnswerRes;
import com.alibaba.fastjson.JSON;
import net.sf.json.JSONObject;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
@Service
public class zsxqApi implements IzsxqApi {
    private Logger logger = LoggerFactory.getLogger(zsxqApi.class);//日志
    @Override
    public UnAnswerQuestionsAggregates queryUnAnswerQuestionsTopicId(String groupId, String cookie) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();//封装数据信息
        HttpGet get = new HttpGet("https://api.zsxq.com/v2/groups/"+ groupId +"/topics?scope=all&count=20");
        get.addHeader("cookie",cookie);
        get.addHeader("content-Type", "application/json;charset=utf8");

        CloseableHttpResponse response = httpClient.execute(get);//执行

        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String jsonStr = EntityUtils.toString(response.getEntity());//读取数据
            logger.info("拉取提问问题,groupId:{} jsonStr:{}",groupId,jsonStr);//日志
            return JSON.parseObject(jsonStr,UnAnswerQuestionsAggregates.class);
        } else {
            throw new RuntimeException("queryUnAnswerQuestionsTopicId ERR Code is "+ response.getStatusLine().getStatusCode());
        }
    }

    @Override
    public boolean comments(String groupId, String cookie, String topicId, boolean silenced,String text) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        //注意
        HttpPost post = new HttpPost("https://api.zsxq.com/v2/topics/"+topicId+"/comments");

        //注意

        post.addHeader("cookie",cookie);
        post.addHeader("content-Type", "application/json;charset=utf8");
        post.addHeader("user-content","\n" +
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36 Edg");
/*        String paramJson = "{\n" +
                "  \"req_data\": {\n" +
                "    \"text\": \"自己去百度！\\n\",\n" +
                "    \"image_ids\": [],\n" +
                "    \"silenced\": false\n" +
                "  }\n" +
                "}";*/
        //测试用

        AnswerReq answerReq = new AnswerReq(new ReqData(text,silenced));//回复文本和回复后是否展示
        String paramJson = JSONObject.fromObject(answerReq).toString();//将文本格式封装成Json格式
        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json", "UTF-8"));//入参信息
        post.setEntity(stringEntity);//加入实体对象

        CloseableHttpResponse response = httpClient.execute(post);//执行

        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String jsonStr = EntityUtils.toString(response.getEntity());
            logger.info("回答星球问题,groupId:{} topicId:{} jsonStr:{}",groupId,topicId,jsonStr);//日志
            AnswerRes answerRes = JSON.parseObject(jsonStr, AnswerRes.class);
            return answerRes.isSucceeded();
        } else {
            throw new RuntimeException("answer ERR Code is "+ response.getStatusLine().getStatusCode());
        }
    }
}
