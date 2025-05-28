package backend.servlet;

import backend.dao.UsuarioDAO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");

        UsuarioDAO dao = new UsuarioDAO();
        if (dao.autenticar(email, senha)) {
            HttpSession session = request.getSession();
            session.setAttribute(email, senha); 
            response.sendRedirect("index.jsp");
        } else {
            response.sendRedirect("login.jsp?erro=true");
        }

    }
}
