package com.example.servlets;

import com.example.db.Facade;
import com.example.servlets.pages.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PageChainServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PageChainBase pageChainBase = new CreateProductPage();
        pageChainBase.linkWith(new DeleteProductPage())
                .linkWith(new CatalogProductPage())
                .linkWith(new ReadProductPage())
                .linkWith(new UpdateProductPage());

        pageChainBase.workUri(req, resp);

    }
}
