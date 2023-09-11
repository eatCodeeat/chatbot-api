package cn.bugstack.chatbot.api.test;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class ApiTest {
    @Test
    public void query_unanswered_question() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();//封装数据信息
        HttpGet get = new HttpGet("https://api.zsxq.com/v2/groups/28885518425541/topics?scope=all&count=1");
        get.addHeader("cookie","zsxq_access_token=F09814F9-DE90-8702-7D3B-905EFAC1B5BE_AE75EDD3DF5E8DDA; abtest_env=product; zsxqsessionid=66dd4e84f4944af0df36c7e7ee037d32; sajssdk_2015_cross_new_user=1; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%22184524585185522%22%2C%22first_id%22%3A%2218a81fe032e819-065bef6cca8d73-7f5d547e-1327104-18a81fe032fdbf%22%2C%22props%22%3A%7B%7D%2C%22identities%22%3A%22eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMThhODFmZTAzMmU4MTktMDY1YmVmNmNjYThkNzMtN2Y1ZDU0N2UtMTMyNzEwNC0xOGE4MWZlMDMyZmRiZiIsIiRpZGVudGl0eV9sb2dpbl9pZCI6IjE4NDUyNDU4NTE4NTUyMiJ9%22%2C%22history_login_id%22%3A%7B%22name%22%3A%22%24identity_login_id%22%2C%22value%22%3A%22184524585185522%22%7D%2C%22%24device_id%22%3A%2218a81fe032e819-065bef6cca8d73-7f5d547e-1327104-18a81fe032fdbf%22%7D");

        get.addHeader("content-Type", "application/json;charset=utf8");

        CloseableHttpResponse response = httpClient.execute(get);//执行

        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String res = EntityUtils.toString(response.getEntity());//读取数据
            System.out.println(res);
        } else {
            System.out.println(response.getStatusLine().getStatusCode());
        }
    }

    @Test
    public void answer() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost post = new HttpPost("https://api.zsxq.com/v2/topics/411821228524288/comments");

        post.addHeader("cookie","zsxq_access_token=F09814F9-DE90-8702-7D3B-905EFAC1B5BE_AE75EDD3DF5E8DDA; abtest_env=product; zsxqsessionid=66dd4e84f4944af0df36c7e7ee037d32; sajssdk_2015_cross_new_user=1; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%22184524585185522%22%2C%22first_id%22%3A%2218a81fe032e819-065bef6cca8d73-7f5d547e-1327104-18a81fe032fdbf%22%2C%22props%22%3A%7B%7D%2C%22identities%22%3A%22eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMThhODFmZTAzMmU4MTktMDY1YmVmNmNjYThkNzMtN2Y1ZDU0N2UtMTMyNzEwNC0xOGE4MWZlMDMyZmRiZiIsIiRpZGVudGl0eV9sb2dpbl9pZCI6IjE4NDUyNDU4NTE4NTUyMiJ9%22%2C%22history_login_id%22%3A%7B%22name%22%3A%22%24identity_login_id%22%2C%22value%22%3A%22184524585185522%22%7D%2C%22%24device_id%22%3A%2218a81fe032e819-065bef6cca8d73-7f5d547e-1327104-18a81fe032fdbf%22%7D");

        post.addHeader("content-Type", "application/json;charset=utf8");
        String paramJson = "{\n" +
                "  \"req_data\": {\n" +
                "    \"text\": \"自己去百度！\\n\",\n" +
                "    \"image_ids\": [],\n" +
                "    \"silenced\": false\n" +
                "  }\n" +
                "}";

        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json", "UTF-8"));//入参信息
        post.setEntity(stringEntity);//加入实体对象

        CloseableHttpResponse response = httpClient.execute(post);//执行
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        } else {
            System.out.println(response.getStatusLine().getStatusCode());
        }
    }

}
