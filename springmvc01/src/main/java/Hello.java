import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 描述:
 *
 * @author Maserhe
 * @create 2021-01-13 1:11
 */
public class Hello extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String method = req.getParameter("method");
        if (method.equals("add")) req.setAttribute("msg", "使用add方法");
        if (method.equals("delete")) req.setAttribute("msg", "使用了delete方法");

        req.getRequestDispatcher("/WEB-INF/test.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
