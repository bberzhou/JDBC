package com.pojo;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: bberzhou@gmail.com
 * @date: 8/1/2021
 * Create By Intellij IDEA
 */
public class Dom4jTest {
    @Test
    public void test1() throws DocumentException {
        //  首先创建一个SaxReader 输入流，读取xml配置文件，生成Document对象
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read("src/Books.xml");
        //org.dom4j.tree.DefaultDocument@4926097b [Document: name src/Books.xml]
        System.out.println(document);

    }

    //  读取books.xml 文件生成book类
    @Test
    public void test2() throws DocumentException {
        //  1、读取books.xml文件
        SAXReader saxReader = new SAXReader();
        //  注意在Junit测试中，相对路径是从模块名开始算
        Document document = saxReader.read("src/Books.xml");
        //  2、通过Document对象获取根元素
        Element rootElement = document.getRootElement();
        // System.out.println(rootElement);
        //  3、通过根元素获取book标签对象
        //  element() 和elements() 都是通过标签名查找子元素
        List<Element> books = rootElement.elements("book");
        ArrayList<Book> booksArr = new ArrayList<>();
        //  4、遍历，处理每个book 表爱你转换为Book类
        for (Element book :books ){
            Book book1 = new Book();
            //  asXML()， 把标签对象，转换为标签字符串
            // System.out.println(book.asXML());


            // 根据标签名取
            // Element nameElement = book.element("name");
            //  getText()：这个方法可以获取标签中的文本内容
            // String text = nameElement.getText();
            // System.out.println(text);

            //  上面这两个可以直接写成一个，使用elementText() 方法，根据元素取得内容 text
            String name = book.elementText("name");
            // System.out.println(name);
            String sn = book.attributeValue("sn");
            String price = book.elementText("price");
            //  获取book 的属性 sn
            String author = book.elementText("author");
            //  设置属性
            book1.setName(name);
            book1.setSn(sn);
            book1.setAuthor(author);
            book1.setPrice(BigDecimal.valueOf(Double.parseDouble(price)));
            //  添加到列表里面
            booksArr.add(book1);
            book1 = null;
        }
        for (Book b :booksArr){
            System.out.println(b);
        }
    }
}
