package com.kidd.demos.vfs;

import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemManager;
import org.apache.commons.vfs2.VFS;
import org.testng.annotations.Test;

import java.io.File;

/**
 * @author Kidd
 *         CreateTime 2017/12/5.
 */
public class VfsTest {

    @Test
    public void test() throws Exception{
        FileSystemManager manager = VFS.getManager();
        FileObject file = manager.resolveFile("res:springContext.xml");
        System.out.println("file " + file.exists());

        FileObject file1 = manager.resolveFile("res:springcontext.xml");
        System.out.println("file1 " + file1.exists());

    }

    @Test
    public void test1() throws Exception{
        File file = new File("src/main/resources/springContext.xml");
        System.out.println("file " + file.exists());

        File file1 = new File("src/main/resources/springcontext.xml");
        System.out.println("file1 " + file1.exists());
    }

}
