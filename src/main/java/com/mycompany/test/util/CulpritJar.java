
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
public class CulpritJar {
    public static void main(final String[] args) throws IOException, ClassNotFoundException {
        File folder = new File("D:/core-renewal/temp/cldp-srv/cco-cldp-war/target/cco-cldp-war-2.31.2-SNAPSHOT/WEB-INF/lib");
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
                if ("com.edb.fs.enterprise.domain.common.v2.PagingInputElementType".equals(className)) {
                    System.out.println(jarFile.getName());
                    Class c = cl.loadClass(className);
                    System.out.println(c);
                }
            }
        }
    }
}
