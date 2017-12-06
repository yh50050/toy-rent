package com.toy.utils;

/**
 * 参数格式检查工具类
 * 
 * @author 枫茗丿love
 *
 */
public class CheckParamUtils {

	/**
	 * 邮箱格式检验
	 * 
	 * @param email
	 * @return
	 */
	public static boolean checkEmail(String email) {
		if (email.matches("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$"))
			return true;
		else
			return false;
	}

	/**
	 * 手机号格式检验
	 * 
	 * @param phone
	 * @return
	 */
	public static boolean checkPhone(String phone) {
		if (phone.matches("^[0-9]{11}$"))
			return true;
		else
			return false;
	}

	/**
	 * 账号格式检验（6-18位英文、数字、下划线）
	 * 
	 * @param uname
	 * @return
	 */
	public static boolean checkUname(String uname) {
		if (uname.matches("^[A-Za-z0-9_]{6,18}$"))
			return true;
		else
			return false;
	}

	/**
	 * 密码格式检验（6-18位英文、数字、下划线、+、-、.、*）
	 * 
	 * @param passwd
	 * @return
	 */
	public static boolean checkPasswd(String passwd) {
		if (passwd.matches("^[A-Za-z0-9_|\\.|\\+|-|\\*]{6,18}$"))
			return true;
		else
			return false;
	}

	/**
	 * 姓名格式检验
	 * 
	 * @param name
	 * @return
	 */
	public static boolean checkName(String name) {
		if (name.matches("^[A-Za-z\\u4e00-\\u9fa5]{1,32}$"))
			return true;
		else
			return false;
	}

	/**
	 * 昵称格式检验
	 * 
	 * @param name
	 * @return
	 */
	public static boolean checkNickname(String name) {
		if (!name.matches("^[;]$") && name.length() < 32)
			return true;
		else
			return false;
	}

}
