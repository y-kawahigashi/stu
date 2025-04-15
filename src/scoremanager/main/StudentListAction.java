package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import tool.Action;

public class StudentListAction extends Action{

	@Override
	public void execute(HttpServletRequest request,HttpServletResponse response
			) throws Exception {
			HttpSession session = request.getSession();
			Teacher teacher = (Teacher)session.getAttribute("user");

			String entYearStr = "";//入力された入学年度
			String classNum = "";//入力されたクラス番号
			String isAttendStr = "";//入力された在学フラグ
			int entYear = 0;//入学年度
			boolean isAttend = false;//在学フラグ
			List<Student> students = null;//学生リスト
			LocalDate todaysDate = LocalDate.now();//LocalDateインスタンスを取得
			int year = todaysDate.getYear();//現在の年を取得
			StudentDao sDao = new StudentDao();//学生Dao
			ClassNumDao cNumDao = new ClassNumDao();//クラス番号Daoを初期化
			Map<String,String> errors = new HashMap<>();//エラーメッセージ

			//リクエストパラメータの取得 2
			entYearStr = request.getParameter("f1");
			classNum = request.getParameter("f2");
			isAttendStr = request.getParameter("f3");

			//ビジネスロジック 4
			if (entYearStr != null) {
				entYear = Integer.parseInt(entYearStr);
			}
			// リストを初期化
			List<Integer> entYearSet = new ArrayList<>();
			// 10年前から1年後まで年をリストに追加
			for(int i = year - 10; i < year + 1; i++) {
				entYearSet.add(i);
			}

			List<String> list = cNumDao.filter(teacher.getschool());

			if (entYear != 0 && !classNum.equals("0")){
				students = sDao.filter(teacher.getschool(),entYear,classNum,isAttend);
			} else if (entYear != 0 && classNum.equals("0")){
				students = sDao.filter(teacher.getschool(), entYear, isAttend);
			} else if (entYear != 0 && classNum == null || entYear == 0 && classNum.equals("0"));
	}

}
