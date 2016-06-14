package cn.forever.elasticsearch.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.CharEncoding;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * @ClassName: HttpUtils
 * @Description: (Http帮助类)
 * @author Zhaoxu
 * @date 2016年4月8日 上午11:08:36
 */
public class HttpUtils {

	private HttpUtils() {
		throw new AssertionError("HttpUtils 不能被实例化....");
	}

	/**
	 * @Title: httpPost
	 * @Description: (post请求)
	 * @param url
	 * @param params
	 * @return
	 */
	public static String httpPost(String url, Map<String, String> params) {
		HttpPost httppost = new HttpPost(url);
		// 创建参数队列
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();

		params.forEach((k, v) -> {
			formparams.add(new BasicNameValuePair(k, v));
		});
		// 设置参数
		httppost.setEntity(new UrlEncodedFormEntity(formparams, Charset.forName(CharEncoding.UTF_8)));
		return request(httppost);
	}

	/**
	 * @Title: httpPost
	 * @Description: (post请求)
	 * @param url
	 * @param params
	 * @return
	 */
	public static String httpPost(String url, String params) {
		HttpPost httppost = new HttpPost(url);
		httppost.setEntity(new StringEntity(params, Charset.forName(CharEncoding.UTF_8)));
		return request(httppost);
	}

	/**
	 * @Title: httpPut
	 * @Description: (put请求)
	 * @param url
	 * @param params
	 * @return
	 */
	public static String httpPut(String url, Map<String, String> params) {
		HttpPut httpPut = new HttpPut(url);
		// 创建参数队列
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();

		params.forEach((k, v) -> {
			formparams.add(new BasicNameValuePair(k, v));
		});
		// 设置参数
		httpPut.setEntity(new UrlEncodedFormEntity(formparams, Charset.forName(CharEncoding.UTF_8)));
		return request(httpPut);
	}

	/**
	 * @Title: httpPut
	 * @Description: (put请求)
	 * @param url
	 * @param params
	 * @return
	 */
	public static String httpPut(String url, String params) {
		HttpPut httpPut = new HttpPut(url);
		httpPut.setEntity(new StringEntity(params, Charset.forName(CharEncoding.UTF_8)));
		return request(httpPut);
	}

	/**
	 * @Title: httpPut 
	 * @Description: (put请求) 
	 * @param url
	 * @return
	 */
	public static String httpPut(String url) {
		return httpPut(url, "");
	}

	/**
	 * @Title: httpGet
	 * @Description: (get请求)
	 * @param url
	 * @return
	 */
	public static String httpGet(final String url) {
		return request(new HttpGet(url));
	}

	/**
	 * @Title: httpDelete
	 * @Description: (delete请求)
	 * @param url
	 * @return
	 */
	public static String httpDelete(final String url) {
		return request(new HttpDelete(url));
	}

	/**
	 * @Title: request
	 * @Description: (发起请求)
	 * @param httpRequest
	 * @return
	 */
	private static String request(HttpRequestBase httpRequest) {
		String result = null;
		// 创建默认的httpClient实例.
		try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
			try (CloseableHttpResponse response = httpclient.execute(httpRequest)) {
				HttpEntity resultEntity = response.getEntity();
				if (resultEntity != null) {
					result = EntityUtils.toString(resultEntity, Charset.forName(CharEncoding.UTF_8));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * @Title: decodeURL
	 * @Description: (URL解码)
	 * @param url
	 * @return
	 */
	public static String decodeURL(String url) {
		if (url == null) {
			return null;
		}
		try {
			return URLDecoder.decode(url, CharEncoding.UTF_8);
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}

	/**
	 * @Title: encodeURL
	 * @Description: (URL编码)
	 * @param url
	 * @return
	 */
	public static String encodeURL(String url) {
		try {
			return URLEncoder.encode(url, CharEncoding.UTF_8);
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}
}
