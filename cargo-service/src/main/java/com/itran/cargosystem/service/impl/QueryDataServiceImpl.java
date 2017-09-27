package com.itran.cargosystem.service.impl;

import java.io.IOException;

import com.itran.cargosystem.entity.vo.FlightDateVo;
import com.itran.cargosystem.service.QueryDataService;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;


/**
 * 类说明
 * 
 * @author lsf
 * @date 2017年7月13日 新建
 */
@Service
public class QueryDataServiceImpl implements QueryDataService {

	private static final Logger logger = Logger.getLogger(QueryDataServiceImpl.class);

	@Override
	public String queryData(FlightDateVo fliDateVo) {

		StringBuffer queryDataUrls = new StringBuffer();
		queryDataUrls.append("http://112.91.83.84/zuhapi/loadmanage/");
		queryDataUrls.append(fliDateVo.getMethods() + "?"); // Methods
		queryDataUrls.append("startFdate=" + fliDateVo.getStartFdate() + "&");
		/*queryDataUrls.append("startFdate=2016-8-24&");	*/				// startFdate
		queryDataUrls.append("endFdate=" + fliDateVo.getEndFdate() + "&"); 
		/*queryDataUrls.append("endFdate=2016-8-24&");*/					// endFdate

		CloseableHttpClient httpclient = HttpClients.createDefault();
		String resultsJosnStr = null;
		try {
			// 创建httpget.
			HttpGet httpget = new HttpGet(queryDataUrls.toString());
			// 执行get请求.
			CloseableHttpResponse response = httpclient.execute(httpget);
			try {
				// 获取响应实体
				HttpEntity entity = response.getEntity();
				// 打印响应状态
				System.out.println(response.getStatusLine());
				if (entity != null) {
					// 打印响应内容长度
					System.out.println("Response content length: " + entity.getContentLength());
					// 打印响应内容
					resultsJosnStr = EntityUtils.toString(entity, "UTF-8");
				}
			} finally {
				try {
					response.close();
				} catch (IOException e) {
					logger.error(e.getClass().getName() + ":" + e.getMessage());
				}
			}
		} catch (Exception e) {
			logger.error(e.getClass().getName() + ":" + e.getMessage());
		} finally {
			// 关闭连接,释放资源
			try {
				httpclient.close();
			} catch (IOException e) {
				logger.error(e.getClass().getName() + ":" + e.getMessage());
			}
		}
		return resultsJosnStr;
	}

}
