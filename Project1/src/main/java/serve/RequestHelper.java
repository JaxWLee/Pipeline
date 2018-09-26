package serve;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EmployeeDAO;
import model.Employee;
import model.Reimbursement;
import service.EmployeeService;
import org.json.simple.JSONObject;

public class RequestHelper {
	public static void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI();
		if (uri.equals("/Project1.do")) {
			// grab index page
			response.sendRedirect("/Project1/index.html");
		}
		if (uri.equals("/Project1/login.do")) {
			// grab login form
			//			if (request.getSession().getAttribute("username").equals(null)) {
			//				response.getWriter().append("You have already logged in, logout to change user");
			//			}
			response.sendRedirect("/Project1/login.html");
		}
		if (uri.equals("/Project1/logout.do")) {
			request.getSession().setAttribute("username", "null");
			request.getSession().setAttribute("manager", 0);
			response.sendRedirect("/Project1.do");
		}
		if (uri.equals("/Project1/login/submit.do")) {
			// attempt to login and validate
			Employee employee = EmployeeService.login(request.getParameter("username").toString(),
					request.getParameter("password").toString());
			if (employee.getUsername() == "null") {
				response.getWriter().append("User does not Exit");
			} else 

				if (employee.getManager() == 1) {
					request.getSession().setAttribute("username", employee.getUsername());
					request.getSession().setAttribute("manager", employee.getManager());
					System.out.println("in manager");
					response.sendRedirect("/Project1/manager.do");
				} else if (employee.getManager() == 0) {
					request.getSession().setAttribute("username", employee.getUsername());
					request.getSession().setAttribute("manager", employee.getManager());
					response.sendRedirect("/Project1/employee.do");
				}
		}
		if (uri.equals("/Project1/username.do")) {
			// get the username to dispaly in dashboard
			System.out.println(request.getSession().getAttribute("username").toString());
			response.getWriter().append(request.getSession().getAttribute("username").toString());
		}
		if (uri.equals("/Project1/dashboard.do")) {
			// get dashboard
			System.out.println(request.getSession().getAttribute("manager").toString());
			if (response.getWriter().append(request.getSession().getAttribute("manager").toString()).equals("1")) {
				response.sendRedirect("/Project1/manager.do");
			}
			else {
				response.sendRedirect("/Project1/employee.do");
			}

		}

		// Employee Routes
		if (uri.equals("/Project1/employee.do")) {
			// get employee dashboard
			response.sendRedirect("/Project1/employee.html");
		}
		if (uri.equals("/Project1/employee/pending.do")) {
			// get pending requests for the employee logged in
			List<Reimbursement> reimbursements = EmployeeService.viewPending(request.getSession().getAttribute("username").toString());
			for (int i = 0; i < reimbursements.toArray().length; i++) {
				response.getWriter().append(reimbursements.toArray()[i].toString());
			}

		}
		if (uri.equals("/Project1/employee/resolved.do")) {
			// view resolved requests
			List<Reimbursement> reimbursements = EmployeeService.viewResolved(request.getSession().getAttribute("username").toString());
			for (int i = 0; i < reimbursements.toArray().length; i++) {
				response.getWriter().append(reimbursements.toArray()[i].toString());
			}
		}
		if (uri.equals("/Project1/employee/reimburse.do")) {
			// get reimbursement form
			response.sendRedirect("/Project1/create.html");
		}
		if (uri.equals("/Project1/employee/reimburse/submit.do")) {
			// submit new reimbursement request
			Reimbursement reimbursement = new Reimbursement();
			reimbursement.setValue(Double.parseDouble(request.getParameter("value")));
			reimbursement.setSubmittedBy(request.getSession().getAttribute("username").toString());
			EmployeeService.submitReimbursement(reimbursement);
			response.sendRedirect("/Project1/employee/pending.do");
		}
		if (uri.equals("/Project1/employee/info.do")) {
			// view employee information page
			response.sendRedirect("/Project1/info.html");
			
		}
		if(uri.equals("/Project1/employee/info/get.do")) {
			// view employee information
			Employee employee = EmployeeService.viewInfo(request.getSession().getAttribute("username").toString());
			JSONObject json = new JSONObject();
			json.put("first_name", employee.getFirst_name());
			json.put("last_name", employee.getLast_name());
			response.getWriter().append(json.toJSONString());
						
		}
		if (uri.equals("Project1/employee/info/submit.do")) {
			// update employee information
			Employee employee = EmployeeService.viewInfo(request.getSession().getAttribute("username").toString());
			employee.setFirst_name(request.getParameter("first_name"));
			employee.setLast_name(request.getParameter("last_name"));
			EmployeeService.updateInfo(employee);
			response.sendRedirect("/Project1/employee.do");
		}
		// Manager Routes
		if (uri.equals("/Project1/manager.do")) {
			// get manager dashboard
			response.sendRedirect("/Project1/manager.html");
		}
		if (uri.equals("/Project1/manager/pending/update.do")) {
			// update one pending requests
		}
		if (uri.equals("/Project1/manager/resolved.do")) {
			// view all resolved requests
		}
		if (uri.equals("/Project1/manager/pending.do")) {
			// view all pending requests
		}
		if (uri.equals("/Project1/manager/employee.do")) {
			// view one employee
		}
		if (uri.equals("/Project1/manager/employee/request.do")) {
			// view all requests by a certain employee
		}

	}
}
