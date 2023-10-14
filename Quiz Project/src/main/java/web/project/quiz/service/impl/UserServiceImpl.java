package web.project.quiz.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import web.project.quiz.dto.UserDto;
import web.project.quiz.dto.UserQuizHistoryDto;
import web.project.quiz.entity.Role;
import web.project.quiz.entity.User;
import web.project.quiz.entity.UserQuizHistory;
import web.project.quiz.repository.RoleRepository;
import web.project.quiz.repository.UserRepository;
import web.project.quiz.service.QuizDomainService;
import web.project.quiz.service.UserService;
import web.project.quiz.util.QuizUtil;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private  static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private UserRepository userRepository;

    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(UserDto userDto) throws Exception {
        try {
            User user = new User();
            user.setUserName(userDto.getUserName());
            user.setEmail(userDto.getEmail());

            // encrypt the password using spring security
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
            userRepository.save(user);

            Role role = new Role();
            role.setName("MEMBER");
            role.setUserId(user.getId());
            roleRepository.save(role);
        } catch (Exception exception) {
            logger.error(exception.getMessage(), exception);
            throw new Exception(exception.getMessage(), exception);
        }
    }

    @Override
    public User getByUserName(String userName) throws Exception {
        User user = null;
        try {
            user = userRepository.findByUserName(userName);
        } catch (Exception exception) {
            logger.error(exception.getMessage(), exception);
            throw new Exception(exception.getMessage(), exception);
        }
        return user;
    }

    @Override
    public  Map<String, List<UserQuizHistoryDto>> getHistory() throws Exception {
        User user = null;
        Map<String, List<UserQuizHistoryDto>> result = new HashMap<>();
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (!(authentication instanceof AnonymousAuthenticationToken)) {
                String currentUserName = authentication.getName();
                System.out.println("User history: " + currentUserName);
                user = userRepository.findByUserName(currentUserName);
            }
            List<UserQuizHistory> userQuizHistoryList = user.getUserQuizHistory();
            for(UserQuizHistory item : userQuizHistoryList) {
                UserQuizHistoryDto dto = new UserQuizHistoryDto();
                dto.setScore(item.getScore());
                dto.setQuizTitle(item.getQuiz().getTitle());
                String key = QuizUtil.domainValues.get(item.getDomainId());
                dto.setDomain(key);
                if(result.get(key) == null){
                    List<UserQuizHistoryDto> dtoList = new ArrayList<>();
                    dtoList.add(dto);
                    result.put(key, dtoList);
                } else {
                    result.get(key).add(dto);
                }
            }
        } catch (Exception exception) {
            logger.error(exception.getMessage(), exception);
            throw new Exception(exception.getMessage(), exception);
        }
        return  result;
    }

    private UserDto mapToUserDto(User user){
        UserDto userDto = new UserDto();
        userDto.setUserName(user.getUserName());
        userDto.setEmail(user.getEmail());
        return userDto;
    }

}
