package com.member.controller;

import com.member.entity.Member;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

import static com.member.util.MemberConstants.SERVICE;
import static com.member.util.MemerCommonUitl.getMemberSession;
import static com.member.util.MemerCommonUitl.gsonToJson;

@WebServlet("/memberGetPictureServlet")
@MultipartConfig
public class MemberGetPictureServlet extends HttpServlet {
    private static final long serialVersionUID = 5938459106015723851L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        Member member = getMemberSession(request,"member");

        Part imgPart = request.getPart("image");
        // 獲取圖片 Part

        String imgStorgePath = "C:/backend/project_workspace/Five_NBP.gg/src/main/webapp/img/member/member_pic";
        // 確定圖片儲存的目標路徑

        String imgName = imgPart.getSubmittedFileName();
        // 獲取圖片的檔名

        imgPart.write(imgStorgePath + File.separator + imgName);
        // 將圖片儲存到目標路徑

        String imgUrl = "/Five_NBP_gg/img/member/member_pic/" + imgName;
        // 設定圖片對外的url

        member.setHeadshot(imgUrl);
        SERVICE.edit(member);
        System.out.println("訊息：會員　" + member.getNick() + " 上傳圖片成功");

        gsonToJson(response,member);


    }
}
