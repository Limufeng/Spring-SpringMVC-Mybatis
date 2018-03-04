package controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.xwork.RandomStringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import bean.UploadedImageFile;

@Controller
public class UploadController {

	@RequestMapping("/uploadImage")
	public ModelAndView upload(HttpServletRequest request, UploadedImageFile file)
			throws IllegalStateException, IOException {
		// ����UploadedImageFile ���Ѿ�ע����� image
		String name = RandomStringUtils.randomAlphanumeric(10);
		// ͨ�� RandomStringUtils.randomAlphanumeric(10);��ȡһ������ļ�����
		// ��Ϊ�û������ϴ���ͬ�ļ������ļ���Ϊ�˲�����ԭ�����ļ���ͨ������ļ����İ취�����
		String newFileName = name + ".jpg";
		File newFile = new File(request.getServletContext().getRealPath("/image"), newFileName);
		// ����request.getServletContext().getRealPath ��ȡ��webĿ¼�µ�imageĿ¼�����ڴ���ϴ�����ļ���
		newFile.getParentFile().mkdirs();
		file.getImage().transferTo(newFile);
		// ����file.getImage().transferTo(newFile); �����ļ�

		ModelAndView mav = new ModelAndView("showUploadedFile");
		mav.addObject("imageName", newFileName);
		// �����ɵ�����ļ����ύ����ͼ�����ں�������ʾ
		return mav;
	}

}
