package com.Mysite.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.Mysite.model.User;
import com.Mysite.service.UserService;

public class LoginFilter implements Filter {
	ApplicationContext context = null;

	public void init(FilterConfig filterConfig) throws ServletException {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		resp.setContentType("text/html;charset=utf-8");
		String username = req.getParameter("username");
		String password = req.getParameter("password");

		if (context == null) {
			context = new ClassPathXmlApplicationContext("applicationContext.xml");
			System.out.println("Khoi tao Spring Bean");
		}

		HttpSession session = req.getSession();
		Object userSession = session.getAttribute("USER_SESSION");
		req.setAttribute("App_Context", context);
		
		// neu co session
		if (userSession != null) {
			// neu co ss ma di den trang login -> chuyen huong den home
			if ("/index.jsp".equals(req.getServletPath()))
				resp.sendRedirect("/MySpringCoreSite/home");
			else
				// co ss ma khac trang login -> tiep tuc di tiep
				chain.doFilter(req, resp);
		}
		// khong co session
		else {
			if (username != null && password != null) {
				try {
					UserService userService = (UserService) context.getBean("userService");
					List<User> listUser = userService.getAll();
					boolean flag = false;
					for (User u : listUser) {
						if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
							flag = true;
							req.setAttribute("user", u);
						}
					}
					// dang nhap thanh cong
					if (flag) {
						HttpSession ss = req.getSession();
						ss.setAttribute("USER_SESSION", username);
						ss.setMaxInactiveInterval(60 * 10);
						
						// neu nhu o trang login ma login thanh cong -> move den home
						if ("/index.jsp".equals(req.getServletPath())) {
							resp.sendRedirect("/MySpringCoreSite/home");
						}
						// TH o trang # - yeu cau login(success) -> move den trang do
						else {
							chain.doFilter(req, resp);
						}
					}
					// dang nhap khong thanh cong
					else {
						resp.getWriter().print("<script>alert('Tài khoản hoặc mật khẩu không đúng')</script>");
						req.getRequestDispatcher("index.jsp").include(req, resp);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				// th ko co session-tk-mk(vao tu trang #login)
				if ("/index.jsp".equals(req.getServletPath())) {
					// neu vao tu trang login -> tiep tuc
					chain.doFilter(req, resp);
				} else {
					// neu vao khac trang login -> chuyen den trang login
					resp.getWriter().print("<script>alert('Bạn cần đăng nhập trước')</script>");
					req.getRequestDispatcher("index.jsp").include(req, resp);
				}

			}
		}

	}

	public void destroy() {
	}

}
