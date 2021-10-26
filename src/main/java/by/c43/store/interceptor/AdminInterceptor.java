package by.c43.store.interceptor;

import by.c43.store.entity.TypeOfUser;
import by.c43.store.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AdminInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(request.getSession().getAttribute("user") != null){
            User user = (User) request.getSession().getAttribute("user");
            if(user.getTypeOfUser().equals(TypeOfUser.ADMIN)){
                return true;
            }
        }
        response.sendRedirect("/home");
        return false;
    }
}
