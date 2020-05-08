package vn.my_project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//@WebServlet(value= {"/home","/"})
//public class Home extends HttpServlet{
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		resp.setContentType("text/html;charset=utf-8");
//		req.getRequestDispatcher("homepage.jsp").include(req, resp);
//	}
//}

@Controller
public class Home{
	
	@RequestMapping(value= {"/","/home","/homepage"})
	String getHomePage() {
		return "homepage";
	}
}