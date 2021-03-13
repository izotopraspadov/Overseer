package edu.born.overseer;

import edu.born.overseer.model.Company;
import edu.born.overseer.model.Employee;
import edu.born.overseer.web.AuthorizedUser;
import edu.born.overseer.web.json.JsonUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.RequestPostProcessor;

import java.io.UnsupportedEncodingException;
import java.util.List;

import static edu.born.overseer.util.PageUtil.getFirstByPage;
import static edu.born.overseer.util.PageUtil.setPageLength;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestUtil {

    public static String getContent(MvcResult result) throws UnsupportedEncodingException {
        return result.getResponse().getContentAsString();
    }

    public static ResultActions print(ResultActions action) throws UnsupportedEncodingException {
        System.out.println(getContent(action.andReturn()));
        return action;
    }

    public static <T> T readFromJsonResultActions(ResultActions action, Class<T> clazz) throws UnsupportedEncodingException {
        return readFromJsonMvcResult(action.andReturn(), clazz);
    }

    public static <T> T readFromJsonMvcResult(MvcResult result, Class<T> clazz) throws UnsupportedEncodingException {
        return JsonUtil.readValue(getContent(result), clazz);
    }

    public static <T> List<T> readListFromJsonMvcResult(MvcResult result, Class<T> clazz) throws UnsupportedEncodingException {
        return JsonUtil.readValues(getContent(result), clazz);
    }

    public static void mockAuthorize(Employee user) {
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(new AuthorizedUser(user), null, user.getRoles()));
    }

    public static RequestPostProcessor userHttpBasic(Employee user) {
        return SecurityMockMvcRequestPostProcessors.httpBasic(user.getLogin(), user.getPassword());
    }

    public static RequestPostProcessor userAuth(Employee user) {
        return SecurityMockMvcRequestPostProcessors.authentication(new UsernamePasswordAuthenticationToken(user.getLogin(), user.getPassword()));
    }

    @SafeVarargs
    public static <T> ResultMatcher getMatcher(Class<T> typeClass, T... expected) {
        return result -> assertThat(readListFromJsonMvcResult(result, typeClass), contains(expected));
    }

    public static <T> ResultMatcher getMatcher(Class<T> typeClass, T expected) {
        return result -> assertEquals(readFromJsonMvcResult(result, typeClass), expected);
    }

    public static ResultMatcher getRestaurantMatcher(Company expected) {
        return result -> assertEquals(readFromJsonMvcResult(result, Company.class), expected);
    }
}