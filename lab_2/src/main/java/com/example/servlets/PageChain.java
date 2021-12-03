package com.example.servlets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface PageChain {

    PageChain linkWith(PageChain next);

    void workUri(HttpServletRequest req, HttpServletResponse resp) throws IOException;

}
