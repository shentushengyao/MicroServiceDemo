package com.spring.boot.springbootservice.sys.websocket;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController("messageCenter")
@PreAuthorize("hasAuthority('ADMIN')")
public class WebSocketController {

    @RequestMapping(value = "/pushVideoListToWeb", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    Map<String, Object> pushVideoListToWeb(@RequestBody Map<String, Object> param) {
        Map<String, Object> result = new HashMap<String, Object>();

        try {
            User user = User.class.cast(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
            WebSocketServer.sendInfo("有新客户呼入,sltAccountId:" + user.getUsername());
            result.put("operationResult", true);
        } catch (IOException e) {
            result.put("operationResult", true);
        }
        return result;

    }
}
