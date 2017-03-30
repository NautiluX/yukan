package com.ntlx.rest;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletResponse;

import com.ntlx.board.Board;
import com.ntlx.exception.BoardNotWorldReadable;

@WebServlet("/getBoardWorldReadable")
public class GetBoardWorldReadable extends GetBoard {

	private static final long serialVersionUID = 1L;

	public GetBoardWorldReadable() throws NamingException, SQLException {
		super();
	}
	
	@Override
	protected void printBoard(HttpServletResponse response, Board board) throws IOException, BoardNotWorldReadable {
		if (board.isWorldReadable()) {
			response.getOutputStream().print(board.toString());
		} else {
			throw new BoardNotWorldReadable();
		}
	}
}