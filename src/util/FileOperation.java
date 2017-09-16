package util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Hashtable;
import java.util.List;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;

@SuppressWarnings({ "unchecked", "unused" })
public class FileOperation {
	public static long getCRC(InputStream fin, String dirname, String filename)
			throws IOException {
		long ret = 0;
		CheckedInputStream checkin = new CheckedInputStream(fin, new CRC32());
		byte buffer[] = new byte[1024];
		int size = 0;
		File f = new File(dirname);
		if (!f.exists())
			mk(dirname, "/");
		f = new File(dirname + filename);
		FileOutputStream fout = new FileOutputStream(f);
		while ((size = checkin.read(buffer)) != -1) {
			fout.write(buffer, 0, size);
		}
		fout.flush();
		fout.close();
		ret = checkin.getChecksum().getValue();
		checkin.close();
		return ret;
	}

	public static long getCRC(InputStream fin) throws IOException {
		long ret = 0;
		CheckedInputStream checkin = new CheckedInputStream(fin, new CRC32());
		byte buffer[] = new byte[1024];
		int size = 0;
		while ((size = checkin.read(buffer)) != -1)
			;
		ret = checkin.getChecksum().getValue();
		checkin.close();
		return ret;
	}

	public static boolean checkFile(InputStream fin, long crcValue)
			throws IOException {
		boolean ret = false;
		if (getCRC(fin) - crcValue == 0)
			ret = true;
		return ret;
	}

	public static boolean delFile(String file) throws IOException {
		boolean ret = false;
		File f = new File(file);
		if (f.isFile()) {
			f.delete();
			ret = true;
		}
		return ret;
	}

	public static void copyFile(String src, String target) throws IOException {
		FileInputStream fin = new FileInputStream(new File(src));
		FileOutputStream fout = new FileOutputStream(new File(target));
		int size = 0;
		byte[] buffer = new byte[1024];
		while ((size = fin.read(buffer)) != -1)
			fout.write(buffer, 0, size);
		fout.flush();
		fout.close();
		fin.close();
	}

	public static void moveFile(String src, String target) throws IOException {
		copyFile(src, target);
		delFile(src);
	}

	public static void outPut(BufferedInputStream in, OutputStream out)
			throws IOException {
		byte[] buffer = new byte[1024];
		int size = 0;
		while ((size = in.read(buffer)) != -1)
			out.write(buffer, 0, size);
		out.flush();
	}

	public static void mk(String path, String split) {
		String[] dirs = path.split(split);
		File f = null;
		f = new File(path);
		if (f.exists())
			return;
		String d = "";
		for (int i = 0; i < dirs.length; i++) {
			d = d + dirs[i] + File.separator;
			f = new File(d);
			if (!f.exists())
				f.mkdir();
		}
	}

	public static String getFilePath(String userid, String orgid) {
		String ret = "";
		if (userid == null || userid.equalsIgnoreCase(""))
			if (orgid == null || orgid.equalsIgnoreCase(""))
				ret = "pubFile";
			else
				ret = "organFile" + "/" + orgid;
		else
			ret = "userFile" + "/" + userid;
		return ret;
	}

	public static List addPath(List rspList, List source) {
		String path = "";
		for (int i = 0; i < rspList.size(); i++) {
			Hashtable row = new Hashtable();
			row = (Hashtable) rspList.get(i);
			String[] tmp = getFilePath(source, row.get("FILE_ID").toString())
					.split(",");
			path = "";
			for (int j = tmp.length - 1; j > 1; j--)
				path = path + "/" + tmp[j];
			if (path.equalsIgnoreCase(""))
				path = "/";
			row.put("FILE_PATH", path);
		}
		return rspList;
	}

	private static String getFilePath(List rspList, String start) {
		StringBuffer path = new StringBuffer();
		String fid = "";
		int rows = rspList.size();
		if (start.indexOf("-") == 0 || start.equalsIgnoreCase("000"))
			return path.toString();
		for (int i = 0; i < rows; i++) {
			Hashtable row = new Hashtable();
			row = (Hashtable) rspList.get(i);
			fid = row.get("FILE_ID").toString();
			if (fid.equalsIgnoreCase(start)) { // &&
				// row.get("FILE_IS_DIR").toString().equalsIgnoreCase("1")){
				path.append(",");
				path.append(row.get("FILE_DISP_NAME").toString());
				path
						.append(getFilePath(rspList, row.get("FILE_PID")
								.toString()));
			}  
		}
		return path.toString();
	}

	public static boolean existFile(String path) {
		boolean ret = false;
		File f = new File(path);
		File[] files = f.listFiles();
		if (files != null && files.length >= 1)
			ret = true;
		return ret;
	}

	public static String getFileName(String name) {
		String ret = "";
		if (name.indexOf("/") >= 0)
			ret = name.substring(name.lastIndexOf("/") + 1);
		// 显示文件名
		else
			ret = name.substring(name.lastIndexOf("/") + 1);
		// 显示文件名
		return ret;
	}
}
