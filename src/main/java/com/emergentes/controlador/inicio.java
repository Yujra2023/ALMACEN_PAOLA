
package com.emergentes.controlador;

import com.emergentes.dao.almacenDAO;
import com.emergentes.dao.almacenDAOimpl;
import com.emergentes.modelo.almacen;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "inicio", urlPatterns = {"/inicio"})
public class inicio extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try{
            int id;
         almacen alma=new almacen();
         almacenDAO dao = new almacenDAOimpl();
        
        String action = (request.getParameter("action") !=null)?request.getParameter("action"):"view";
        
        switch(action){
            case"add":
                request.setAttribute("almacen",alma);
                request.getRequestDispatcher("frmalmacen.jsp").forward(request, response);
            break;
            case"edit":
                id = Integer.parseInt(request.getParameter("id"));
                alma = dao.getById(id);
                
                request.setAttribute("almacen",alma);
                request.getRequestDispatcher("frmalmacen.jsp").forward(request, response);
                
            break;
            case"delete":
                id = Integer.parseInt(request.getParameter("id"));
                dao.delete(id);
                response.sendRedirect("inicio");
            break;
            case"view":
                List<almacen>lista=dao.getAll();
                request.setAttribute("productos",lista);
                request.getRequestDispatcher("index.jsp").forward(request,response);
            break;
            default:
                break;
            
        }
        }catch(Exception ex){
            System.out.println("ERROR:" + ex.getMessage());
        }
        
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

}
