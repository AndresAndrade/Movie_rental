package cts.movie_rental.servlets;

import cts.movie_rental.controller.PeliculaController;
import cts.movie_rental.controller.UsuarioController;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ServletUsuarioRestarDinero", value = "/ServletUsuarioRestarDinero")
public class ServletUsuarioRestarDinero extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public ServletUsuarioRestarDinero() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UsuarioController usuario = new UsuarioController();

        String username = request.getParameter("username");
        double saldo = Double.parseDouble(request.getParameter("saldo"));
        String usuarioStr = usuario.restarDinero(username, saldo);

        response.setContentType("text/html;charset=UTF-8");
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
