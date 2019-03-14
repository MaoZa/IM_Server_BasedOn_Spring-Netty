package cn.dawnland.im.controller;

import cn.dawnland.im.handler.IMHandler;
import cn.dawnland.im.model.result.ReturnData;
import io.netty.bootstrap.ServerBootstrap;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetSocketAddress;

/**
 * @author Cap_Sub
 */
@RestController
@RequestMapping("test")
@Api(value = "测试接口", tags = {"测试接口"})
public class TestController {

    @Autowired
    private IMHandler imHandler;
    @Autowired
    @Qualifier("tcpSocketAddress")
    private InetSocketAddress tcpSocketAddress;

    @ApiOperation("获取IMHandler")
    @GetMapping("getIMHandler")
    public ReturnData getIMHandler(){
        return ReturnData.isOk().data(tcpSocketAddress);
    }
}
