package javaUtil;
/**
 * �ж�����������Ƿ�Ϊ��
 */
public class StrEmptyUtil {
	public static boolean isEmpty(String str){
		if(str==null||"".equals(str.trim())){
			return true;
		}
		else{
			return false;
		}
	}
	public static String strTrim(String str1){
		String str2;
		str2 = str1.trim().replace(" ","");
		return str2;
	}
}
