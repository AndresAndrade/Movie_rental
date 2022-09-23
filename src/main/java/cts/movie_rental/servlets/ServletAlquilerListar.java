package cts.movie_rental.servlets;

import cts.movie_rental.controller.AlquilerController;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ServletAlquilerListar", value = "/ServletAlquilerListar")
public class ServletAlquilerListar extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ServletAlquilerListar() {
        super();
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        AlquilerController alquiler = new AlquilerController();
        String username = request.getParameter("username");

        String alquilerStr = alquiler.listarAlquileres(username);

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(alquilerStr);
        out.flush();
        out.close();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
