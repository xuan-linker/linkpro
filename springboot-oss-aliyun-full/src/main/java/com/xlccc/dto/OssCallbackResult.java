package com.xlccc.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Linker
 * @date 2021/2/4 19:18
 * @description：oss上传文件的回调结果
 */
@Data
public class OssCallbackResult {
    @ApiModelProperty("文件名称")
    private String filename;
    @ApiModelProperty("文件大小")
    private String size;
    @ApiModelProperty("文件的mimeType")
    private String mimeType;
    @ApiModelProperty("图片文件的宽")
    private String width;
    @ApiModelProperty("图片文件的高")
    private String height;
}
