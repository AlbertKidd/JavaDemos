package com.demos.mslinks;

import mslinks.ShellLink;
import org.junit.Test;

/**
 * @author Kidd
 *         CreateTime 2017/8/12.
 */
public class MsLinksDemo {

    @Test
    public void test(){
        try {
            ShellLink.createLink("D:\\games\\liuxinghudiejian\\liuxinghudiejian\\meteor.exe",
                    "C:\\Users\\hasee\\Desktop\\流星蝴蝶剑.lnk");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
