package com.eec.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.eec.dao.FounderFileDao;
import com.eec.entity.FounderFile;
import com.eec.entity.FounderMessage;
import com.eec.util.JDBCUtil2;

public class FounderFileDaoImpl implements FounderFileDao {
	/**
	 * 将图片信息插入到数据库中
	 */
	public int insertPicToDB(FounderFile founderFile) {
		String sql = " insert into founderfile (fildid, filename, filedes, filepic) "
				+ "values (fileid_nextval.nextval, ?, ?, ?) ";
		Object[] objs = { founderFile.getFileName(), founderFile.getFileDes(),
				founderFile.getFilePic() };
		int temp = JDBCUtil2.executeUpdate(sql, objs);
		return temp;
	}

	@Override
	public List<FounderFile> getFileByAE(int currentPage, int pageCount,
			int fileId, String fileName, String fileDes, double filePriceMin,
			double filePriceMax) {

		StringBuilder sql = new StringBuilder(
				"select fildid, filename, filepic, fileprice, filedes from (select * from (select fildid, filename, filepic, fileprice, filedes, rownum bianhao from founderfile");

		sql.append(" where rownum <= " + currentPage * pageCount + "");

		if (fileId != 0) {
			sql.append(" and fildid = " + fileId + "");
		}
		if (!"".equals(fileName)) {
			sql.append(" and filename like '%" + fileName + "%'");
		}
		if (!"".equals(fileDes)) {
			sql.append(" and filedes like '%" + fileDes + "%'");
		}
		if (filePriceMin != 0) {
			sql.append(" and fileprice >= " + filePriceMin + " ");
		}
		if (filePriceMax != 0) {
			sql.append(" and fileprice <= " + filePriceMax + " ");
		}
		sql.append(" ) aa where bianhao > " + (currentPage - 1) * pageCount
				+ ") bb ");

		ResultSet rs = JDBCUtil2.executeQuery(sql.toString());
		List<FounderFile> fileList = new ArrayList<FounderFile>();
		try {
			while (rs.next()) {
				FounderFile file = new FounderFile();
				file.setFileid(rs.getInt(1));
				file.setFileName(rs.getString(2));
				file.setFilePic(rs.getString(3));
				file.setFilePrice(rs.getDouble(4));
				file.setFileDes(rs.getString(5));
				fileList.add(file);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			JDBCUtil2.closeRPC(rs, rs.getStatement(), rs.getStatement()
					.getConnection());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return fileList;
	}

	@Override
	public int getCount(int pageCount, int fileId, String fileName,
			String fileDes, double filePriceMin, double filePriceMax) {
		StringBuilder sql = new StringBuilder(
				"select count(*) from founderfile where 1=1 ");

		if (fileId != 0) {
			sql.append(" and fildid = " + fileId + "");
		}
		if (!fileName.equals("")) {
			sql.append(" and filename like '%" + fileName + "%'");
		}
		if (!fileDes.equals("")) {
			sql.append(" and filedes like '%" + fileDes + "%'");
		}
		if (filePriceMin != 0) {
			sql.append(" and fileprice >= " + filePriceMin + " ");
		}
		if (filePriceMax != 0) {
			sql.append(" and fileprice <= " + filePriceMax + " ");
		}

		ResultSet rs = JDBCUtil2.executeQuery(sql.toString());
		int temp = 0;
		try {
			while (rs.next()) {
				temp = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			JDBCUtil2.closeRPC(rs, rs.getStatement(), rs.getStatement()
					.getConnection());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return temp;
	}

	@Override
	public FounderFile getFileInfo(int fileid) {
		String sql = " select * from founderfile where fildid=? ";
		Object[] objs = { fileid };
		ResultSet rs = JDBCUtil2.executeQuery(sql, objs);
		/* 定义一个founderfile对象，用于返回对象 */
		FounderFile founderFile = null;
		try {
			while (rs.next()) {
				founderFile = new FounderFile();
				founderFile.setFileid(rs.getInt(1));
				founderFile.setFileName(rs.getString(2));
				founderFile.setFilePic(rs.getString(3));
				founderFile.setFilePrice(rs.getDouble(4));
				founderFile.setFileDes(rs.getString(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			JDBCUtil2.closeRPC(rs, rs.getStatement(), rs.getStatement()
					.getConnection());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return founderFile;
	}

	@Override
	public int insertMsg(FounderMessage fm) {
		/* 插入一条留言信息到数据库中 */
		String sql = "insert into foundermessage (messageid, userid, username, fildid, messagecontent, "
				+ "messagedate) "
				+ "values (fileid_nextval.nextval, ?, ?, ?, ?, sysdate) ";
		Object[] objs = { fm.getUserId(), fm.getUserName(), fm.getFileId(),
				fm.getMessageContent() };
		int temp = JDBCUtil2.executeUpdate(sql, objs);
		return temp;
	}

	@Override
	public List<FounderMessage> getFMById(int fileid) {
		/* 时间变换 */
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String sql = " select messageid, userid, username, fildid, messagecontent, to_char(messagedate,'yyyy-mm-dd hh24:mi:ss') messagedate from foundermessage where fildid=? order by messagedate desc ";
		Object[] objs = { fileid };
		ResultSet rs = JDBCUtil2.executeQuery(sql, objs);
		List<FounderMessage> fmList = new ArrayList<FounderMessage>();
		try {
			while (rs.next()) {
				FounderMessage fm = new FounderMessage(rs.getInt(1),
						rs.getInt(2), rs.getString(3), rs.getInt(4),
						rs.getString(5), sdf.parse(rs.getString(6)));
				fmList.add(fm);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} finally {
			try {
				JDBCUtil2.closeRPC(rs, rs.getStatement(), rs.getStatement()
						.getConnection());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return fmList;
	}

	@Override
	public List<FounderMessage> getPageResultByData(int fileid,
			int currentPage, int pageCount) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String sql = " select messageid, userid, username, fildid, messagecontent, messagedate from (select * from "
				+ " ( select * from (select messageid, userid, username, fildid, messagecontent, to_char(messagedate,'yyyy-mm-dd hh24:mi:ss') messagedate, rownum biao from foundermessage where fildid= "
				+ fileid
				+ " order by messagedate desc) where biao<= "
				+ currentPage
				* pageCount
				+ ") aa "
				+ " where biao>"
				+ (currentPage - 1) * pageCount + ") bb ";

		ResultSet rs = JDBCUtil2.executeQuery(sql);
		List<FounderMessage> fmList = new ArrayList<FounderMessage>();
		try {
			while (rs.next()) {
				FounderMessage fm = new FounderMessage(rs.getInt(1),
						rs.getInt(2), rs.getString(3), rs.getInt(4),
						rs.getString(5), sdf.parse(rs.getString(6)));
				fmList.add(fm);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} finally {
			try {
				JDBCUtil2.closeRPC(rs, rs.getStatement(), rs.getStatement()
						.getConnection());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return fmList;
	}

}
