package Bitlab.Final.Project.Servlets;

import BItlab.FInal.Project.DB.Comment;
import BItlab.FInal.Project.DB.DBManager;
import BItlab.FInal.Project.DB.News;
import BItlab.FInal.Project.DB.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(value = "/add-comments")
public class AddCommentServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) (request.getSession().getAttribute("currentUser"));

        if (user != null) {
            String comment1 = request.getParameter("comment");
            Long id = Long.parseLong(request.getParameter("news_id"));

            News news = DBManager.getNewsByID(id);

            if (news != null) {
                Comment comment = new Comment();
                comment.setComment(comment1);
                comment.setNews(news);
                comment.setUser(user);
                DBManager.addComment(comment);
                response.sendRedirect("/new_details?id=" + id);
            } else {
                response.sendRedirect("/home");
            }


        } else {
            response.sendRedirect("/login");
        }
    }
}
