package day1;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: bberzhou@gmail.com
 * @date: 6/30/2021
 * Create By Intellij IDEA
 */
public class test {
    public static void main(String[] args) {
        ArrayList<String> strings = new ArrayList<>();
        ArrayList<Integer> integers = new ArrayList<>();
        Class<? extends ArrayList> aClass = strings.getClass();
        Class<? extends ArrayList> aClass1 = integers.getClass();

        if (aClass.equals(aClass1)){
            System.out.println("==");
        }
//        在编译之后程序会采取去泛型化的措施。也就是说Java中的泛型，只在编译阶段有效。
//        在编译过程中，正确检验泛型结果后，会将泛型的相关信息擦出，
//        并且在对象进入和离开方法的边界处添加类型检查和类型转换的方法。
//        也就是说，泛型信息不会进入到运行时阶段

//        对此总结成一句话：泛型类型在逻辑上看以看成是多个不同的类型，实际上都是相同的基本类型。
//        泛型类，是在实例化类的时候指明泛型的具体类型；泛型方法，是在调用方法的时候指明泛型的具体类型 。

    }
}
