package cn.itcast.test;

import java.io.File;

public class FileTest {
public static void main(String[] args) {
	//剪切文件 C:\Users\superboy\Desktop\route.txt->>d:\
	File file = new File("D:\\route.txt");
	File file2 = new File("E:\\route.txt");
	file2.renameTo(file);
	
}
}
