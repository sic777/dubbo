package com.sic777.utils.encrypt.mac;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * <p></p>
 *
 * @author Zhengzhenxie<br>
 *         <br>2017-12-09 17:55
 * @version v1.0
 * @since 2018-03-03 17:15
 */
public class HmacSHA1Tools {
	private static final String MAC_NAME = "HmacSHA1";
	private static final String ENCODING = "UTF-8";
	/**
	 * 随便写的密钥
	 */
	private static final String ENCRYPT_KEY = "466ad5bf345de924aa115b7c721d39db379051fe";

	public static byte[] HmacSHA1Encrypt(String encryptText) throws Exception {
		byte[] data = ENCRYPT_KEY.getBytes(ENCODING);
		// 根据给定的字节数组构造一个密钥,第二参数指定一个密钥算法的名称
		SecretKey secretKey = new SecretKeySpec(data, MAC_NAME);
		// 生成一个指定 Mac 算法 的 Mac 对象
		Mac mac = Mac.getInstance(MAC_NAME);
		// 用给定密钥初始化 Mac 对象
		mac.init(secretKey);
		byte[] text = encryptText.getBytes(ENCODING);
		// 完成 Mac 操作
		return mac.doFinal(text);
	}
}
