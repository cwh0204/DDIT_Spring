package kr.or.ddit.web;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.*;
import java.util.*;
import net.datafaker.Faker;
@WebServlet("/random-name")
public class randomName extends HttpServlet{
	public String getMessege(){
		Faker faker = new Faker();
		return faker.name().fullName();
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        Faker faker = new Faker();
        String html = "<html><body><h1>server time : %s</h1></body></html>";
        String content = String.format(html, faker.name().fullName());
        resp.getWriter().println(content);
    }
}