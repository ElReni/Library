package ru.elreni.spring.config;

import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class DispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] {SpringConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		// TODO Auto-generated method stub
		return new String[] {"/"};
	}
	
	@Override
	public void onStartup(jakarta.servlet.ServletContext servletContext) throws jakarta.servlet.ServletException {
		super.onStartup(servletContext);
	    registerHiddenFieldFilter(servletContext);
	}

	private void registerHiddenFieldFilter(jakarta.servlet.ServletContext servletContext) {
		servletContext.addFilter("hiddenHttpMethodFilter",
				new HiddenHttpMethodFilter()).addMappingForUrlPatterns(null ,true, "/*");
	}


}
