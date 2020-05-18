
package com.mycompany.test.util;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 *
 */

/**
 * @author e210636
 *
 */
public class FindAnnotations {

    public static void main(final String[] args) throws IOException {
        File folder = new File("C:/Projectdata/extreme/spring-boot-cxf-rest/target/spring-boot-cxf-rest-1.0.0-SNAPSHOT/WEB-INF/spring");
        File[] listOfFiles = folder.listFiles();
        for (File listOfFile : listOfFiles) {
            JarFile jarFile = new JarFile(listOfFile);
            Enumeration<JarEntry> e = jarFile.entries();
            URL[] urls = { new URL("jar:file:" + listOfFile + "!/") };
            URLClassLoader cl = URLClassLoader.newInstance(urls);
            while (e.hasMoreElements()) {
                JarEntry je = e.nextElement();
                if (je.isDirectory() || !je.getName().endsWith(".class")) {
                    continue;
                }
                // -6 because of .class
                String className = je.getName().substring(0, je.getName().length() - 6);
                className = className.replace('/', '.');
                System.out.println("jarname " + jarFile.getName());
                if (!className.contains("module-info")) {
                    Class c = null;
                    try {
                        c = cl.loadClass(className);
                    } catch (ClassNotFoundException | java.lang.NoClassDefFoundError e1) {
                        System.out.println("Missing class" + e1);
                    }
                    if ((null != c) && c.isAnnotation()) {
                        System.out.println("" + c);
                    }
                }
            }
        }
    }
}
