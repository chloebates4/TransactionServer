import java.io.File;
import java.util.StringTokenizer;


public class FileUtils
{

	public static String getProperPathString(String pathString, char wrongChar){
		StringBuffer pathStringBuffer = new StringBuffer(pathString);
		int index;
		int offset = -1;
		while((index = pathString.indexOf(wrongChar, offset + 1)) != -1){
			pathStringBuffer.setCharAt(index, File.separatorChar);
			offset = index;
		}
		return pathStringBuffer.toString();
	}

	public static String[] getClassPaths(){
		String[] classPaths = new String[1];
		String classPath = System.getProperty(“java.class.path”);
		StringTokenizer tokenizer = new StringTokenizer(classPath, File.pathSeparator);
		int count = 0;
		String[] oldClassPaths;
		String token;

		while(tokenizer.hasMoreTokens())
		{
			token = tokenizer.nextToken();
			if(token.endsWith(File.separator))
				token = token.substring(0, token.length()-1);
			classPaths[count] = token;
			oldClassPaths = classPaths;
			classPaths = new String[++count + 1];
			System.arraycopy(oldClassPaths, 0, classPaths, 0, count);
		}
		oldClassPaths = classPaths;
		classPaths = new String[count];
		System.arraycopy(oldClassPaths, 0, classPaths, 0, count);
		return classPaths;
	}
}
