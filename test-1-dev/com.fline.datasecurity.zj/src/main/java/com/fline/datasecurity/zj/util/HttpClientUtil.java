package com.fline.datasecurity.zj.util;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * HTTP工具，基于连接池优化管理。
 * 
 * @author zhuzl
 *
 */
public class HttpClientUtil {
	private static Logger LOG = LogManager.getLogger(HttpClientUtil.class);

	/**
	 * keep alive策略
	 * 
	 * @author zhuzl
	 *
	 */
	private static class DefaultConnectionKeepAliveStategy implements ConnectionKeepAliveStrategy {
		@Override
		public long getKeepAliveDuration(HttpResponse response, HttpContext arg1) {
			HeaderElementIterator it = new BasicHeaderElementIterator(response.headerIterator(HTTP.CONN_KEEP_ALIVE));
			while (it.hasNext()) {
				HeaderElement he = it.nextElement();
				String param = he.getName();
				String value = he.getValue();
				if (value != null && param.equalsIgnoreCase("timeout")) {
					return Long.parseLong(value) * 1000;
				}
			}
			return 60 * 1000;// 如果没有约定，则默认定义时长为60s
		}
	}

	/**
	 * http连接池中的空闲连接管理
	 * 
	 * @author zhuzl
	 *
	 */
	public static class IdleConnectionMonitorThread extends Thread {

		private final HttpClientConnectionManager connMgr;
		private volatile boolean shutdown;

		public IdleConnectionMonitorThread(HttpClientConnectionManager connMgr) {
			super();
			this.connMgr = connMgr;
		}

		@Override
		public void run() {
			try {
				while (!shutdown) {
					synchronized (this) {
						wait(5000);
						// Close expired connections
						connMgr.closeExpiredConnections();
						// Optionally, close connections
						// that have been idle longer than 30 sec
						connMgr.closeIdleConnections(30, TimeUnit.SECONDS);
					}
				}
			} catch (InterruptedException ex) {
				LOG.error("httpclient空闲连接监控线程终止.", ex);
			}
		}

		public void shutdown() {
			shutdown = true;
			synchronized (this) {
				notifyAll();
			}
		}

	}

	/**
	 * 建立全局共享的httpclient
	 */
	private static CloseableHttpClient httpClient = null;
	static {
		// http 连接池
		PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
		connectionManager.setMaxTotal(1000);
		connectionManager.setDefaultMaxPerRoute(200);// 例如默认每路由最高50并发，具体依据业务来定
		// 从连接池中获取连接的时间最长为100ms，建立与服务端通信的时间最长为1秒，处理业务最长时间为10秒
		RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(1000).setConnectTimeout(1000)
				.setSocketTimeout(10000).build();

		ConnectionKeepAliveStrategy kaStrategy = new DefaultConnectionKeepAliveStategy();
		httpClient = HttpClients.custom().setConnectionManager(connectionManager).setKeepAliveStrategy(kaStrategy)
				.setDefaultRequestConfig(requestConfig).build();

		// 控制空闲连接
		IdleConnectionMonitorThread idleConnectionMonitor = new IdleConnectionMonitorThread(connectionManager);
		idleConnectionMonitor.start();

	}

	/**
	 * Get 请求
	 * 
	 * @param url
	 * @return
	 */
	public static String doGet(String url) {
		return doGet(url, null);
	}

	/**
	 * Get 请求， 参数以Map形式传入，拼接到URI中。使用URIBuilder创建
	 * 
	 * @param url
	 * @param param
	 * @return
	 */
	public static String doGet(String url, Map<String, String> param) {
		String resultString = "";
		CloseableHttpResponse response = null;
		try {
			// 创建uri
			URIBuilder builder = new URIBuilder(url);
			if (param != null) {
				for (String key : param.keySet()) {
					builder.addParameter(key, param.get(key));
				}
			}
			URI uri = builder.build();

			// 创建http GET请求
			HttpGet httpGet = new HttpGet(uri);
			// 执行请求
			response = httpClient.execute(httpGet);
			// 判断返回状态是否为200
			if (response.getStatusLine().getStatusCode() == 200) {
				resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
			} else {
				resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
			}
			EntityUtils.consume(response.getEntity());
		} catch (Exception e) {
			LOG.error("HttpClientUtil.doGet for '" + url + "' return error." + e.getMessage());
		} finally {
			try {
				response.close();
			} catch (IOException e) {
				LOG.error("close response failed.", e);
			}
		}
		return resultString;
	}

	/**
	 * 调用无参的POST请求
	 * 
	 * @param url
	 * @return
	 */
	public static String doPost(String url) {
		return doPost(url, null);
	}

	/**
	 * 基于字符串&拼接的查询条件
	 * 
	 * @param url
	 * @param param
	 * @return
	 */
	public static String doPostWithStringParam(String url, String param) {
		CloseableHttpResponse response = null;
		String resultString = "";
		try {
			// 创建httpclient对象
			CloseableHttpClient httpClient = HttpClients.createDefault();
			// 创建Http Post请求
			HttpPost httpPost = new HttpPost(url);
			StringEntity reqEntity = new StringEntity(param);
			httpPost.setEntity(reqEntity);
			httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
			httpPost.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
			// 执行http请求
			response = httpClient.execute(httpPost);
			resultString = EntityUtils.toString(response.getEntity(), "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("HttpClientUtil.doPost for " + url + " return error." + e.getMessage());
		} finally {
			try {
				response.close();
			} catch (IOException e) {
				LOG.error("close response failed.", e);
			}
		}

		return resultString;
	}

	/**
	 * 以Map形式传递form参数
	 * 
	 * @param url
	 * @param param
	 * @return
	 */
	public static String doPost(String url, Map<String, String> param) {
		CloseableHttpResponse response = null;
		String resultString = "";
		try {
			// 创建Http Post请求
			HttpPost httpPost = new HttpPost(url);
			// 创建参数列表
			if (param != null) {
				List<NameValuePair> paramList = new ArrayList<>();
				for (String key : param.keySet()) {
					paramList.add(new BasicNameValuePair(key, param.get(key)));
				}
				// 模拟表单
				UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList, "UTF-8");
				httpPost.setEntity(entity);
			}
			httpPost.addHeader("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:1.7.6)");
			httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
			// 执行http请求
			response = httpClient.execute(httpPost);
			resultString = EntityUtils.toString(response.getEntity(), "utf-8");
		} catch (Exception e) {
			LOG.error("HttpClientUtil.doPost for " + url + " return error." + e.getMessage());
		} finally {
			try {
				response.close();
			} catch (IOException e) {
				LOG.error("close response failed.", e);
			}
		}

		return resultString;
	}

	/**
	 * post 传递JSON格式的参数
	 * 
	 * @param url
	 * @param json
	 * @return
	 */
	public static String doPostJson(String url, String json) {
		return doPostJsonWithHeaders(url, null, json);
	}

	/**
	 * post 传递JSON格式的参数
	 * 
	 * @param url
	 * @param json
	 * @return
	 */
	public static String doPostJsonWithHeaders(String url, Map<String, String> headers, String json) {
		CloseableHttpResponse response = null;
		String resultString = "";
		try {
			// 创建Http Post请求
			HttpPost httpPost = new HttpPost(url);
			if (headers != null) {
				for (Entry<String, String> header : headers.entrySet()) {
					httpPost.addHeader(header.getKey(), header.getValue());
				}
			}
			// 创建请求内容
			StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
			httpPost.setEntity(entity);
			// 执行http请求
			response = httpClient.execute(httpPost);
			resultString = EntityUtils.toString(response.getEntity(), "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				response.close();
			} catch (IOException e) {
				LOG.error("close response failed.", e);
			}
		}

		return resultString;
	}
}
