package com.metaarchit.wechat.nowait.util;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.X509TrustManager;

/**
 * 信任管理器
 * @author HuangKailie
 * @createTime 2017-11-14 15:44:25
 */
public class MyX509TrustManager implements X509TrustManager {

	/**
	 * 檢查客戶端證書
	 */
	public void checkClientTrusted(X509Certificate[] arg0, String arg1)
			throws CertificateException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 檢查服務器證書
	 */
	public void checkServerTrusted(X509Certificate[] arg0, String arg1)
			throws CertificateException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 返回受信任的X509證書數組
	 */
	public X509Certificate[] getAcceptedIssuers() {
		// TODO Auto-generated method stub
		return null;
	}

}
