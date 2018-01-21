package com.eec.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.eec.dao.impl.FounderUserDaoImpl;
import com.eec.entity.FounderUser;

/**
 * ����ܿ��ܻ�ִ�е�executeUpdate()����ʱ�ͻ�ִֹͣ�У�������Ϊ��Oracle���ݿ��л���δ�ύ�����������ݿ��е��commit�Ϳ����ˡ�
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
		FounderUser user = new FounderUser(1007, "лʵ��", "989796", "��",
				"18355099898", time);
		FounderUserDao dao = new FounderUserDaoImpl();
		boolean flag = dao.insertUserByObj(user);
		if (flag) {
			System.out.println("�ɹ���������");
		} else {
			System.out.println("ʧ��");
		}
	}
}
