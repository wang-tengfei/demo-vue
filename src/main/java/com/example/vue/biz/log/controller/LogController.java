package com.example.vue.biz.log.controller;

import com.example.vue.biz.log.controller.web.OperationLogResult;
import com.example.vue.biz.log.service.OperationLogService;
import com.example.vue.common.constant.Result;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.extensions.Extension;
import io.swagger.v3.oas.annotations.extensions.ExtensionProperty;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.links.Link;
import io.swagger.v3.oas.annotations.links.LinkParameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.OAuthFlow;
import io.swagger.v3.oas.annotations.security.OAuthFlows;
import io.swagger.v3.oas.annotations.security.OAuthScope;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.servers.ServerVariable;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tengfei
 */
@RestController
@RequestMapping("/vue")
@OpenAPIDefinition(
        info = @Info(
                title = "Overview", version = "1.0.0.BETA", description = "Application Protection Overview API",
                license = @License(name = "Apache 2.0", url = "http://foo.bar"),
                contact = @Contact(url = "https://www.nexusguard.com", name = "Nexusguard", email = "tengfei.wang@nexusguard.com")
        ),
        servers = {
                @Server(
                        description = "https",
                        url = "https://localhost:8788",
                        variables = {
                                @ServerVariable(name = "var1", description = "var 1", defaultValue = "1", allowableValues = {"1", "2"})
                        }),
                @Server(description = "http", url = "http://localhost:8788")
        }
)
@Tag(name = "Logs API", description = "logs api")
@SecurityScheme(name = "access_token",
        type = SecuritySchemeType.APIKEY,
        in = SecuritySchemeIn.QUERY,
        flows = @OAuthFlows(
                implicit = @OAuthFlow(authorizationUrl = "https://api.nexusguard.com/api/oauth",
                        scopes = {
                                @OAuthScope(name = "app_id", description = "id of your app"),
                                @OAuthScope(name = "secret", description = "secret of your app")
                        })
        )
)
public class LogController {

    @Autowired
    private OperationLogService logService;

    @RequestMapping(value = "/logs", method = RequestMethod.GET)
    public Result getLogs(@RequestParam(value = "type", required = false) Integer[] type) {
        return logService.getLogList(type);
    }


    @Operation(summary = "get logs", description = "get logs with page", operationId = "get_logs_witch_page",
            parameters = {
                    @Parameter(name = "page_index", description = "page index of log", in = ParameterIn.QUERY, required = true, schema = @Schema(type = "string")),
                    @Parameter(name = "page_size", description = "page size of log", in = ParameterIn.QUERY, required = true, schema = @Schema(type = "string")),
                    @Parameter(name = "userName", description = "user name", in = ParameterIn.QUERY, required = false, schema = @Schema(type = "string")),
                    @Parameter(name = "type", description = "user type", in = ParameterIn.QUERY, required = false, schema = @Schema(type = "integer"))
            },
            requestBody = @RequestBody(
                    content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE)
            ),
            responses = {
                    @ApiResponse(
                            description = "logs response",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = OperationLogResult.class)),
                            links = {
                                    @Link(
                                            name = "testId",
                                            operationId = "getAllUserName",
                                            parameters = @LinkParameter(name = "userId", expression = "$responses.result.userId"))
                            }
                    )
            },
            extensions = {
                    @Extension(properties = {
                            @ExtensionProperty(name = "x-api-type", value = "5"),
                            @ExtensionProperty(name = "x-rewrite-uri", value = "[\"/speapi/SpeAdminv2Policy/policy_overview\", \"/speapi/SpeAdminv2Policy/policy_overview\"]", parseValue = true)
                    })
            }
    )
    @RequestMapping(value = "/logs/page", method = RequestMethod.GET, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result getLogWithPage(@RequestParam("page_index") Integer pageNum,
                                 @RequestParam("page_size") Integer pageSize,
                                 @RequestParam(value = "userName", required = false) String userName,
                                 @RequestParam(value = "type", required = false) Integer type,
                                 @RequestParam(value = "startTime", required = false) Long startTime,
                                 @RequestParam(value = "endTime", required = false) Long endTime) {
        return logService.getLogWithPage(pageNum, pageSize, userName, type, startTime, endTime);
    }

    @RequestMapping(value = "/log-type", method = RequestMethod.GET)
    public Result getLogType() {
        return logService.getLogType();
    }
}
