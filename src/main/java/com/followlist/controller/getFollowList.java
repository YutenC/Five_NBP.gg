package com.followlist.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.followlist.entity.FollowList;
import com.followlist.util.FollowListConstants;
import com.followlist.util.ResFollowList;
import com.google.gson.Gson;
import com.member.entity.Member;

@WebServlet("/getFollowPd")
public class getFollowList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public getFollowList() {
        super();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	HttpSession httpSession = req.getSession();
    	Member member = new Member();
    	member.setMember_id(1);
    	httpSession.setAttribute("member", member);
    	Member getMember = (Member)httpSession.getAttribute("member");
    	Integer memberId = null;
    	if (getMember != null) {
    		memberId = getMember.getMember_id();
    	} else {
    		res.sendRedirect("/Five_NBP.gg");
    		return;
    	}
    	Gson gson = new Gson();
    	res.setCharacterEncoding("UTF-8");
    	res.setContentType("application/json");
    	PrintWriter pw = res.getWriter();
   		List<ResFollowList> rsFList = FollowListConstants.FPSERVICE.getAllFollowProduct(memberId);
   		pw.println(gson.toJson(rsFList));
	}

}
