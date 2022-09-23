package cts.movie_rental.servlets;

import cts.movie_rental.controller.PeliculaController;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ServletPeliculaDevolver", value = "/ServletPeliculaDevolver")
public class ServletPeliculaDevolver extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ServletPeliculaDevolver() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PeliculaController pelicula = new PeliculaController();

        String username = request.getParameter("username");
        int id = Integer.parseInt(request.getParameter("id"));

        String libroStr = pelicula.devolver(id,username);

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(libroStr);
        out.flush();
        out.close();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
