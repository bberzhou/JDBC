package day2BLOB;

/**
 * @description:
 * @author: bberzhou@gmail.com
 * @date: 7/1/2021
 * Create By Intellij IDEA
 */
public class aboutBlob {
    /**
     * MySQL中BLOB是一个二进制大型对象，是一个可以存储大量数据的容器，他能容纳不同大小的数据。
     * 插入BLOB类型的数据必须使用PreparedStatement，因为BLOB类型的数据无法使用字符串拼接
     * MySQL的四种BLOB类型：
     *          TinyBlob 最大255字节
     *          Blob    最大65K
     *          MediumBlob 最大16M
     *          LongBlob 最大4G
     *
     * 如果在制定了相关的Blob类型之后，还报错：XXX too large ,那么在mysql 的安装目录下，找到my.ini文件
     * 加上配置参数：max_allowed_packet = 16M,然后再重启mysql服务
     *
     *
     *
     */
}
