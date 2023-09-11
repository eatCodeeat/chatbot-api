package cn.bugstack.chatbot.api.domain.zsxq;

import cn.bugstack.chatbot.api.domain.zsxq.model.aggregates.UnAnswerQuestionsAggregates;

import java.io.IOException;

//知识星球的API接口
public interface IzsxqApi {

    UnAnswerQuestionsAggregates queryUnAnswerQuestionsTopicId(String groupId , String cookie) throws IOException;

    boolean comments(String groupId , String cookie,String topicId,boolean silenced,String text) throws IOException;
}
