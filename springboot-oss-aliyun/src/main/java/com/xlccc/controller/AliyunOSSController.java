package com.xlccc.controller;

import com.xlccc.utils.AliyunOSSUtil;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;

@Controller
@RequestMapping("/oss")
public class AliyunOSSController {

    private final org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private AliyunOSSUtil aliyunOSSUtil;

    /**
     * 测试上传文件到阿里云OSS存储
     *
     * @return
     */
    @RequestMapping("/testUpload")
    @ResponseBody
    public String testUpload() {
        File file = new File("C:\\Users\\macro\\Desktop\\xlccc.jpg");
        AliyunOSSUtil aliyunOSSUtil = new AliyunOSSUtil();
        String url = aliyunOSSUtil.upLoad(file);
        System.out.println(url);
        return "success";
    }

    /**
     * 通过文件名下载文件
     */
    @RequestMapping("/testDownload")
    @ResponseBody
    public String testDownload() {
        AliyunOSSUtil aliyunOSSUtil = new AliyunOSSUtil();
        aliyunOSSUtil.downloadFile(
                "test/2020-01-26/efa55e1c511d4dbfa5032ad0563dd205-xlccc.jpg", "C:\\Users\\macro\\Desktop\\xlccc-600782-unsplash.jpg");
        return "success";
    }

    /**
     * 列出某个文件夹下的所有文件
     */
    @RequestMapping("/testListFile")
    @ResponseBody
    public String testListFile() {
        AliyunOSSUtil aliyunOSSUtil = new AliyunOSSUtil();
        aliyunOSSUtil.listFile();
        return "success";
    }

    /**
     * 文件上传（供前端调用）
     */
    @RequestMapping(value = "/uploadFile")
    public String uploadPicture(@RequestParam("file") MultipartFile file, Model model) {
        logger.info("文件上传");
        String filename = file.getOriginalFilename();
        System.out.println(filename);
        try {

            if (file != null) {
                if (!"".equals(filename.trim())) {
                    File newFile = new File(filename);
                    FileOutputStream os = new FileOutputStream(newFile);
                    os.write(file.getBytes());
                    os.close();
                    file.transferTo(newFile);
                    // 上传到OSS
                    String uploadUrl = aliyunOSSUtil.upLoad(newFile);
                    model.addAttribute("url", uploadUrl);
                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "success";
    }
}