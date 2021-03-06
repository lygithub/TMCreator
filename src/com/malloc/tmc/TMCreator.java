package com.malloc.tmc;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import net.ontopia.topicmaps.core.TopicIF;
import net.ontopia.topicmaps.core.TopicMapBuilderIF;
import net.ontopia.topicmaps.core.TopicMapIF;
import net.ontopia.topicmaps.core.TopicMapStoreIF;
import net.ontopia.topicmaps.impl.basic.InMemoryTopicMapStore;
import net.ontopia.topicmaps.xml.XTMTopicMapWriter;

public class TMCreator {

    /**
     * All the resource Video视频，"rmvb", "avi", "mp4", "flv", "wmv"
     * Audio音频，"mp3", "wav", "ape", "ogg" Diagram图片，"jpg", "bmp", "png", "git"
     * Book书籍，"txt", "pdf", "doc", "ppt" Code代码，"cpp", "h", "java", "cs", "py",
     * "xml" Other其他，"exe", "plist", "bat"
     */
    static final String Resource[] = { "Video", "Audio", "Diagram", "Book",
            "Code", "Other" };
    static final String Video[] = { "rmvb", "avi", "mp4", "flv", "wmv" };
    static final String Audio[] = { "mp3", "wav", "ape", "ogg" };
    static final String Diagram[] = { "jpg", "bmp", "png", "git" };
    static final String Book[] = { "txt", "pdf", "doc", "ppt" };
    static final String Code[] = { "cpp", "h", "java", "cs", "py", "xml" };
    static final String Other[] = { "exe", "plist", "bat" };

    // path of TMResource
    static final String TMResource = "X:/EclipseWorkspace/TMResrouce";
    static final String TMObject = "";
    // static final String TMResource = "X:\\EclipseWorkspace\\TMResrouce";

    File dir = new File(TMResource);

    // 存储
    static ArrayList<ResourceType> RTArray = new ArrayList<ResourceType>();

    // 以递归的方式遍历目录及其子目录的所有文件
    public static void directoryTraverseByRecursion(String sPath) {

        File resourceDir = new File(sPath);
        // isDirectory判断此路径所表示的文件是否是一个文件夹，isFile判断此路径所表示的文件是否是一个标准文件
        if (!resourceDir.isDirectory()) {
            System.out.println(sPath + "is not a directory!");
            return;
        }

        // listFiles返回一个File对象数组，每个数组保存对应目录下的每个文件或目录
        File[] arrayFile = resourceDir.listFiles();

        for (int i = 0; i < arrayFile.length; i++) {
            if (arrayFile[i].isDirectory()) {
                // getName返回由此路径名表示的文件或目录的名称
                System.out.println("-" + arrayFile[i].getName() + "\n");

                // getAbsolutePath返回此路径名的规范路径名字符串，getPath将路径名转换为一个路径名字符串
                directoryTraverseByRecursion(arrayFile[i].getAbsolutePath());

            } else {
                System.out.println("---" + arrayFile[i].getName());

                ResourceType rt = new ResourceType(arrayFile[i].getName());

                // 判断文件名是否符合命名规范
                // if (rt.getFullName() == null || rt.getName() == null
                // || rt.getCreator() == null || rt.getDate() == null
                // || rt.getSize() == null || rt.getSuffix() == null)
                // continue;

                // 将文件的ResourceType对象保存到ArrayList
                RTArray.add(rt);

            }
        }
    }

    // 将读取到的资源信息存储到指定文件
    // public static void resourceAppend(String filePath, String appendContent)
    // {
    // try {
    // // 打开一个写文件器，FileWriter构造函数中的第二个参数true表示以追加形式写文件
    // FileWriter fw = new FileWriter(filePath, true);
    // fw.write(appendContent);
    // fw.close();
    // } catch (IOException e) {
    // e.printStackTrace();
    // }
    // }

    public TMCreator() {

    }

    // main for test
    public static void main(String[] args) {
        // currentTimeMillis返回当前时间距离1970-01-01零点的时间差，单位ms
        long START = System.currentTimeMillis();

        directoryTraverseByRecursion(TMResource);

//        long END = System.currentTimeMillis();
        
//        System.out.println("Time Cost: " + (END - START) + "ms (" + END + "-"
//                + "" + START + ")\n");
//
//        System.out.println("RTArray size = " + RTArray.size());
//        for (ResourceType rt : RTArray) {
//            rt.printRType();
//        }
//
//        RTArray.clear();

        // 创建TM
        TopicMapStoreIF store = new InMemoryTopicMapStore();
        TopicMapIF topicmap = store.getTopicMap();
        TopicMapBuilderIF builder = topicmap.getBuilder();


        // topic of resource
        TopicIF topicResource = builder.makeTopic();
        builder.makeTopicName(topicResource, "Resource");
        
        TopicIF topicVideo = builder.makeTopic();
        builder.makeTopicName(topicVideo, "Video");
        topicVideo.addType(topicResource);
        TopicIF topicAudio = builder.makeTopic();
        builder.makeTopicName(topicAudio, "Audio");
        topicAudio.addType(topicResource);
        TopicIF topicDiagram = builder.makeTopic();
        builder.makeTopicName(topicDiagram, "Diagram");
        topicDiagram.addType(topicResource);
        TopicIF topicBook = builder.makeTopic();
        builder.makeTopicName(topicBook, "Book");
        topicBook.addType(topicResource);
        TopicIF topicCode = builder.makeTopic();
        builder.makeTopicName(topicCode, "Code");
        topicCode.addType(topicResource);
        TopicIF topicOther = builder.makeTopic();
        builder.makeTopicName(topicOther, "Other");
        topicOther.addType(topicResource);

        // topic of Video
        TopicIF topicmp3 = builder.makeTopic();
        builder.makeTopicName(topicmp3, "mp3");
        topicmp3.addType(topicAudio);
        TopicIF topicape = builder.makeTopic();
        builder.makeTopicName(topicape, "ape");
        topicape.addType(topicAudio);
        TopicIF topicogg = builder.makeTopic();
        builder.makeTopicName(topicogg, "ogg");
        topicogg.addType(topicAudio);

        // topic of Book
        TopicIF topicpdf = builder.makeTopic();
        builder.makeTopicName(topicpdf, "pdf");
        topicpdf.addType(topicBook);
        TopicIF topictxt = builder.makeTopic();
        builder.makeTopicName(topictxt, "txt");
        topictxt.addType(topicBook);

        // topic of Code
        TopicIF topiccpp = builder.makeTopic();
        builder.makeTopicName(topiccpp, "cpp");
        topiccpp.addType(topicCode);

        // topic of Diagram
        TopicIF topicbmp = builder.makeTopic();
        builder.makeTopicName(topicbmp, "bmp");
        topicbmp.addType(topicDiagram);
        TopicIF topicjpg = builder.makeTopic();
        builder.makeTopicName(topicjpg, "jpg");
        topicjpg.addType(topicDiagram);

        // topic of Other
        TopicIF topicplist = builder.makeTopic();
        builder.makeTopicName(topicplist, "plist");
        topicplist.addType(topicOther);

        // topic of Video
        TopicIF topicavi = builder.makeTopic();
        builder.makeTopicName(topicavi, "avi");
        topicavi.addType(topicVideo);
        TopicIF topicrmvb = builder.makeTopic();
        builder.makeTopicName(topicrmvb, "rmvb");
        topicrmvb.addType(topicVideo);

        

        for (ResourceType rt : RTArray) {
            TopicIF tTemp = builder.makeTopic();
            builder.makeTopicName(tTemp, rt.getFullName());
            switch(rt.getSuffix()){
            case "mp3":
                tTemp.addType(topicmp3);
                break;
            case "ogg":
                tTemp.addType(topicogg);
                break;
            case "ape":
                tTemp.addType(topicape);
                break;
            case "avi":
                tTemp.addType(topicavi);
                break;
            case "rmvb":
                tTemp.addType(topicrmvb);
                break;
            case "bmp":
                tTemp.addType(topicbmp);
                break;
            case "jpg":
                tTemp.addType(topicjpg);
                break;
            case "txt":
                tTemp.addType(topictxt);
                break;
            case "pdf":
                tTemp.addType(topicpdf);
                break;
            case "cpp":
                tTemp.addType(topiccpp);
                break;
            case "plist":
                tTemp.addType(topicplist);
                break;
            default:
                break;
            }
        }
        
        
        // having created the topic map we are now ready to export it
        try {
            new XTMTopicMapWriter("xxx.xtm").write(topicmap);
        } catch (IOException e) {
            e.printStackTrace();
        }

        
        

        RTArray.clear();
        

        
        
      long END = System.currentTimeMillis();
        
      System.out.println("Time Cost: " + (END - START) + "ms (" + END + "-"
              + "" + START + ")\n");
        
        
        //test
        // 有个String数组，我想用数组里的每个String当作是变量名，
        // 变量名为前缀p后接String，这样要怎么实现啊？就是下面的p#SS的部分
        // String name[] = {...};
        // for (String SS : name) {
        // Person p#SS = new Person(SS);
        // getNickname(p#SS);
        // }

        // ResourceType rt1 = new ResourceType("WWE-Z-20140515-45mb-rmvb");
        // rt1.printRType();
        // rt1.printRType(rt1.getFullName());
        //
        //
        // ResourceType rt = new ResourceType("a5", "1a", "a2", "3a", "4a");
        // rt.printRType();
        // rt.printRType(rt.getFullName());

        // for (String x : Video) {
        // System.out.println(x);
        // }
        // for (int i = 0; i < Video.length; i++) {
        // System.out.println(Video[i]);
        // }
        // System.out.println(TMResource);
    }
}
