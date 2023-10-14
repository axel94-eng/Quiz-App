package web.project.quiz.service;

import web.project.quiz.dto.UserDto;
import web.project.quiz.dto.UserQuizHistoryDto;
import web.project.quiz.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    void saveUser(UserDto userDto) throws Exception;

    User getByUserName(String userName) throws Exception;

    Map<String, List<UserQuizHistoryDto>> getHistory() throws Exception;
}
