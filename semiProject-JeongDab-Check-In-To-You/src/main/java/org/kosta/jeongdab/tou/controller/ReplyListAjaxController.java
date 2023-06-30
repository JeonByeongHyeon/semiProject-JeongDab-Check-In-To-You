package org.kosta.jeongdab.tou.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.kosta.jeongdab.tou.model.ReplyDAO;
import org.kosta.jeongdab.tou.model.ReplyVO;

public class ReplyListAjaxController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		long boardNo = Long.parseLong(request.getParameter("boardNo"));
		ArrayList<ReplyVO> arrayList = ReplyDAO.getInstance().replyList(boardNo);
		JSONArray jsonArray = new JSONArray(arrayList);
		request.setAttribute("responsebody", jsonArray);
		return "AjaxView";
	}
}
