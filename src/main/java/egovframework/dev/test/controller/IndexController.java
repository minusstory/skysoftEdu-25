package egovframework.dev.test.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import egovframework.framework.annotation.PageTitle;


@Controller
public class IndexController {
	/** Log Info */
	protected Log log = LogFactory.getLog(this.getClass());

	@PageTitle("메인페이지")
	@RequestMapping("index.do")
	public ModelAndView index(){
		return new ModelAndView("/index");
	}
}
