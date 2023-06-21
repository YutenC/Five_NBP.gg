package com.shop.followlist.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.member.entity.Member;
import com.shop.followlist.util.FollowListConstants;
import com.shop.followlist.util.ResFollowList;

@WebServlet("/FollowList")
public class FollowListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public FollowListController() {
        super();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	req.setCharacterEncoding("UTF-8");
    	
    	Gson gson = new Gson();
    	res.setCharacterEncoding("UTF-8");
    	res.setContentType("application/json");
    	PrintWriter pw = res.getWriter();
    	
    	HttpSession httpSession = req.getSession();
    	Member member = new Member();
    	member.setMember_id(1);
    	httpSession.setAttribute("member", member);
    	
    	Member getMember = (Member)httpSession.getAttribute("member");
    	Integer memberId;
    	
    	if (getMember == null) {
    		res.sendRedirect("/Five_NBP.gg");
    		return;
    	} else {
    		memberId = getMember.getMember_id();
    	}
    	
    	if (req.getParameter("getAll") != null) {
    		List<ResFollowList> rsFList = FollowListConstants.FPSERVICE.getAllFollowProduct(memberId);
    		pw.println(gson.toJson(rsFList));
    		return;
    	}
    	
    	if (req.getParameter("delPdId") != null) {
    		Integer delPdId = Integer.valueOf(req.getParameter("delPdId"));
        	boolean result = FollowListConstants.FPSERVICE.deleteFollowList(memberId, delPdId);
        	pw.print(gson.toJson(result));
        	return;
    	}
    	
    	
	}

}
