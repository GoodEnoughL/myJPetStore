package org.csu.mypetstore.web;

import org.csu.mypetstore.domain.Item;
import org.csu.mypetstore.domain.Product;
import org.csu.mypetstore.service.CatelogService;
import org.csu.mypetstore.service.UserLogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ViewProductServlet extends HttpServlet {
    private String productId;
    private static final String viewProduct="/WEB-INF/jsp/catalog/Product.jsp";
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        productId=request.getParameter("productId");
        CatelogService service=new CatelogService();
        Product product=service.getProduct(productId);
        List<Item> itemList=service.getItemListByProduct(productId);
        HttpSession session=request.getSession();
        session.setAttribute("product",product);
        session.setAttribute("itemList",itemList);


        UserLogService recordLog =new UserLogService();
        recordLog.recordUserLog(request,response,viewProduct + "查看小类" +productId);

        request.getRequestDispatcher(viewProduct).forward(request,response);
    }
}
