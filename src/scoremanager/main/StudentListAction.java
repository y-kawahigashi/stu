package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import tool.Action;

public class StudentListAction extends Action{

	@Override
	public void execute(HttpServletRequest request,HttpServletResponse response
			) throws Exception {
			HttpSession session = request.getSession();
			Teacher teacher = (Teacher)session.getAttribute("user");
	}

}
