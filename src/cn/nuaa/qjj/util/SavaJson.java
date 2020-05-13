package cn.nuaa.qjj.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Map;

import cn.nuaa.qjj.main.MainSchdule;
import net.sf.json.JSONObject;

public class SavaJson {

	public static JSONObject save() {
		JSONObject jo = new JSONObject();
		Map<Integer, Integer> utility = MainSchdule.perUtility;
/*
		utility.put(1, 34);
		utility.put(2, 45);
		utility.put(3, 4546);
		utility.put(4, 4646);
		utility.put(5, 46466);
		utility.put(2313, 124453);
		utility.put(3010, 2423);*/

		String[] arr = new String[3301];
		String[] arr2 = new String[3301];
		for (Integer i : utility.keySet()) {
			Integer j = utility.get(i);
			arr[i - 1] = String.valueOf(i);
			arr2[i - 1] = String.valueOf(j);
		}

	/*	for (String s : arr) {
			System.out.print(s + ",");
		}*/
		jo.put("time", arr);
		jo.put("data", arr2);
		saveDataToFile("data", jo.toString());
		return jo;
	}

	private static void saveDataToFile(String fileName, String data) {
		BufferedWriter writer = null;
		File file = new File("e:\\" + fileName + ".json");
		// 如果文件不存在，则新建一个
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// 写入
		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, false), "UTF-8"));
			writer.write(data);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (writer != null) {
					writer.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JSONObject rs = SavaJson.save();
		// System.out.println(rs.toString());
	}

}
