package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import bean.Student;

@Controller
public class StudentController {
	
	@RequestMapping("/addStudent")
	public ModelAndView add(Student student) throws Exception {
		ModelAndView mav = new ModelAndView("showStudent");
		return mav;
	}
	
}
