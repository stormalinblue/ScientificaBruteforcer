protected void doGet(HttpServletRequest request, HttpServletResponse response)throws 
     ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		int n1 = Integer.parseInt(request.getParameter("n1"));
		int n2 = Integer.parseInt(request.getParameter("n2"));

		out.println(n1 + n2 + "");
	}
}