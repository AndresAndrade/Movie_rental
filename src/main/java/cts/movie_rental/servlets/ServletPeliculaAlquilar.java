package cts.movie_rental.servlets;

import cts.movie_rental.controller.PeliculaController;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ServletPeliculaAlquilar", value = "/ServletPeliculaAlquilar")
public class ServletPeliculaAlquilar extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public ServletPeliculaAlquilar() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PeliculaController pelicula = new PeliculaController();

        int id = Integer.parseInt(request.getParameter("id"));
        String username = request.getParameter("username");
        String peliculaStr = pelicula.alquilar(id, username);

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(peliculaStr);
        out.flush();
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
