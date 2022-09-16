package cts.movie_rental.servlets;

import cts.movie_rental.controller.PeliculaController;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ServletPeliculaListar", value = "/ServletPeliculaListar")
public class ServletPeliculaListar extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    public ServletPeliculaListar() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PeliculaController pelicula = new PeliculaController();

        boolean ordenar = Boolean.parseBoolean(request.getParameter("ordenar"));
        String orden = request.getParameter("orden");

        String peliculaStr = pelicula.listarPeliculas(ordenar, orden);

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
