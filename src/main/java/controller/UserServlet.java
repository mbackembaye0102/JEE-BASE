package controller;

import model.User;
import services.UserDao;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name="user",urlPatterns={"/user"})
public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
       // resp.getWriter().println("Bonjour Mbacke Mbaye");

        getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(req,resp);
    }




    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
       String log= req.getParameter("log");
       String pwd =req.getParameter("pwd");
        UserDao userDao = new UserDao();

       User u = userDao.logOn(log, pwd);
       if (u!=null){
           HttpSession session=req.getSession();
           session.setAttribute("connectedUser", u);
           if(u.getProfil().equalsIgnoreCase("user")){
               getServletContext().getRequestDispatcher("/WEB-INF/user.jsp").forward(req,resp);
           }
           else{
               getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(req,resp);
           }
       }
       else{
           req.setAttribute("error", "Login ou password incorrect");
           getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(req,resp);
       }


    }
}
