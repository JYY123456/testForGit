package com.fline.datasecurity.zj.intercept;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 性能监控的Filter
 * 
 * @author zhuzl
 *
 */
@WebFilter(filterName = "performanceFilter", urlPatterns = "/api/v1/*")
public class PerformanceFilter implements Filter {
	private static Logger LOG = LogManager.getLogger(PerformanceFilter.class);

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		long start = System.currentTimeMillis();
		chain.doFilter(req, res);
		long end = System.currentTimeMillis();
		LOG.debug("performancefilter: url:" + ((HttpServletRequest) req).getRequestURI() + ", cost " + (end - start)
				+ "ms");
	}

}
