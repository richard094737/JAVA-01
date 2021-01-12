import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class XlassLoader extends ClassLoader {


    private byte[] file2Bytes(String filePath) {
        FileInputStream fileInputStream = null;
        byte[] resultByte = null;
        byte[] transByte = new byte[1];
        byte[] readByte = new byte[1];
        ByteArrayOutputStream byteArrayOutputStream = null;
        int n;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream(2048);
            fileInputStream = new FileInputStream(new File(filePath));

            while ((n = fileInputStream.read(readByte)) != -1) {
                transByte[0] = (byte) (255 - readByte[0]);
                byteArrayOutputStream.write(transByte, 0, n);
            }
            resultByte = byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (byteArrayOutputStream != null) {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return resultByte;


    }

    protected Class<?> findClass(String name, String filePath) throws ClassNotFoundException {
        byte[] classByte = file2Bytes(filePath);
        return defineClass(name, classByte, 0, classByte.length);
    }


    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException {
        String classPath = XlassLoader.class.getResource("Hello.xlass").getPath();
        XlassLoader xlassLoader = new XlassLoader();
        Class<?> xlassLoaderClass = xlassLoader.findClass("Hello", classPath);
        Method method = xlassLoaderClass.getDeclaredMethod("hello");
        method.setAccessible(true);
        try {
            method.invoke(xlassLoaderClass.newInstance());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }


    }
}
