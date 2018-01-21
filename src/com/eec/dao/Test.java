package com.eec.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.eec.dao.impl.FounderUserDaoImpl;
import com.eec.entity.FounderUser;

/**
 * 程序很可能会执行到executeUpdate()方法时就会停止执行，这是因为在Oracle数据库中还有未提交的事务，在数据库中点击commit就可以了。
 * 
 * @author SJF18
 * 
 */
public class Test {
	public static void main(String[] args) {
		Date date = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd HH:MM:ss");
		String date1 = "1992-11-10 12:12:12";
		try {
			date = sdf.parse(date1);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		java.sql.Date time = new java.sql.Date(date.getTime());
		FounderUser user = new FounderUser(1007, "谢实俊", "989796", "男",
				"18355099898", time);
		FounderUserDao dao = new FounderUserDaoImpl();
		boolean flag = dao.insertUserByObj(user);
		if (flag) {
			System.out.println("成功插入数据");
		} else {
			System.out.println("失败");
		}
	}
}
