package cts.movie_rental.servlets;

import cts.movie_rental.controller.UsuarioController;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ServletUsuarioPedir", value = "/ServletUsuarioPedir")
public class ServletUsuarioPedir extends HttpServlet {
    public ServletUsuarioPedir() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UsuarioController usuario = new UsuarioController();
        String username = request.getParameter("username");
        String usuarioStr =  usuario.pedir(username);

        PrintWriter out = response.getWriter();
        out.println(usuarioStr);
        out.flush();
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
