package com.example.servlets;

import com.example.db.Facade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class PageChainBase implements PageChain {

    protected final Facade FACADE = new Facade();
    private PageChain next;

    public PageChain linkWith(PageChain next) {
        this.next = next;
        return next;
    }

    public abstract void workUri(HttpServletRequest req, HttpServletResponse resp) throws IOException;

    protected void workUriNext(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (next != null) {
            next.workUri(req, resp);
        }
    }

}
