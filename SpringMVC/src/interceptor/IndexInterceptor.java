package interceptor;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class IndexInterceptor extends HandlerInterceptorAdapter {
	
	/** 
     * ��ҵ��������������֮ǰ������ 
     * �������false 
     *     �ӵ�ǰ������������ִ��������������afterCompletion(),���˳���������
     * �������true 
     *    ִ����һ��������,ֱ�����е���������ִ����� 
     *    ��ִ�б����ص�Controller 
     *    Ȼ�������������, 
     *    �����һ������������ִ�����е�postHandle() 
     *    �����ٴ����һ������������ִ�����е�afterCompletion() 
     */ 
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("preHandle(), �ڷ���Controller֮ǰ������");
		return true;
	}
	
	 /**
     * ��ҵ��������������ִ����ɺ�,������ͼ֮ǰִ�еĶ���   
     * ����modelAndView�м������ݣ����統ǰʱ��
     */ 
	

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("postHandle(), �ڷ���Controller֮�󣬷�����ͼ֮ǰ������,�������ע��һ��ʱ�䵽modelAndView�У����ں�����ͼ��ʾ");
		modelAndView.addObject("date", "�����������ɵ�ʱ��:" + new Date());
	}

	/** 
     * ��DispatcherServlet��ȫ����������󱻵���,������������Դ��  
     *  
     * �����������׳��쳣ʱ,��ӵ�ǰ����������ִ�����е���������afterCompletion() 
     */
	
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("afterCompletion(), �ڷ�����ͼ֮�󱻵���");
	}
}