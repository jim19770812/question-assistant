package com.training.config;

import com.training.exceptions.APIException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.*;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
@ComponentScan(basePackages = {"com.training"})
public class Swagger2Config {
    @Value("${server.port}")
    private String serverPort;
    @Value("${app.swagger2.enable}")
    private boolean swaggerEnabled;

    /**
     * 用于设置swagger的header,返回异常代码,绑定测试服务器主机
     * @return
     */
    @Bean
    public Docket createDocket(){
        var responseMessages = getResponseMessage();
        var parameterBuilder = new ParameterBuilder();
        parameterBuilder.parameterType("header")
                .name("Authorization")
                .description("登录令牌")
                .modelRef(new ModelRef("String"))
                .required(false).build();

        var parameters = new ArrayList<Parameter>();
        parameters.add(parameterBuilder.build());
        var result=new Docket(DocumentationType.SWAGGER_2)
                .pathMapping("/")
                .enable(this.swaggerEnabled)
                .select()
                //.apis(RequestHandlerSelectors.any())
                .apis(RequestHandlerSelectors.basePackage("com.training.controllers"))
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, responseMessages)
                .globalResponseMessage(RequestMethod.PUT, responseMessages)
                .globalResponseMessage(RequestMethod.POST, responseMessages)
                .globalResponseMessage(RequestMethod.DELETE, responseMessages)
                .globalOperationParameters(parameters)
                ;
        return result;
    }

    /**
     * 设置返回信息
     */
    private ArrayList<ResponseMessage> getResponseMessage() {
        ArrayList<ResponseMessage> responseMessages = new ArrayList<>();
        responseMessages.add(new ResponseMessageBuilder().code(APIException.SUCC).message("成功").build());
        responseMessages.add(new ResponseMessageBuilder().code(1001).message("业务异常,通常用于弹窗提示直接输出错误,如存在关联无法删除之类的业务异常").build());
        responseMessages.add(new ResponseMessageBuilder().code(1002).message("登录令牌错误,需要刷新令牌后重新尝试登录").build());
        responseMessages.add(new ResponseMessageBuilder().code(1003).message("参数错误,用于参数校验失败后定位到错误字段,返回json数组如:[{\"email\":\"邮箱格式错误\"},...]").build());
        responseMessages.add(new ResponseMessageBuilder().code(1004).message("刷新令牌失败,需要重新登录").build());
        responseMessages.add(new ResponseMessageBuilder().code(1005).message("没有访问权限").build());
        responseMessages.add(new ResponseMessageBuilder().code(1006).message("服务器内部异常,必须处理,出现后请反馈给后端,带上错误编号即可,例: [错误编号]:null point Exception").build());
        return responseMessages;
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("投票助手2")
                .description("投票助手api测试工具")
                .termsOfServiceUrl("127.0.0.1:"+this.serverPort)
                .version("1.0")
                .build();
    }
}
