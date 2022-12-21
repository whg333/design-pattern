package com.whg;

import com.ksyun.ks3.service.Ks3;
import com.ksyun.ks3.service.Ks3Client;
import com.ksyun.ks3.service.Ks3ClientConfig;

import java.io.File;

public class TestKs3Upload {

    public static void main(String[] args) {
        // yourEndpoint填写Bucket所在地域对应的Endpoint。以中国（北京）为例，Endpoint填写为ks3-cn-beijing.ksyuncs.com。如果使用自定义域名，设置endpoint为自定义域名，同时设置domainMode为true
        String endpoint = "ks3-cn-beijing.ksyuncs.com";
        // 金山云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用子账号账号进行 API 访问或日常运维，请登录 https://uc.console.ksyun.com/pro/iam/#/user/list 创建子账号。
        String accessKeyId = "AKLTcAz0Zt8RQZKQUUOv0w2iIQ";
        String accessKeySecret = "OHF6hmal5H1OzWzbXlwQ9ul+2DLJeq6BE49sKlkGTEtmkw64xN7kBKcnTyQDYiccjQ==";
        // 创建Ks3ClientConfig 实例。
        Ks3ClientConfig config = new Ks3ClientConfig();
        // 设置域名
        config.setEndpoint(endpoint);
        // 创建Ks3Client实例
        Ks3 client = new Ks3Client(accessKeyId,accessKeySecret,config);
        // 填写本地文件的完整路径。如果未指定本地路径，则默认从示例程序所属项目对应本地路径中上传文件流。
        File file = new File("D:\\whg\\temp\\test.png");
        // 依次填写Bucket名称（例如examplebucket）和Object完整路径（例如exampledir/exampleobject.txt）。Object完整路径中不能包含Bucket名称。
        client.putObject("cms", "upload/test2.png", file);
    }

}
