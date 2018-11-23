package com.springbootlearn.damo.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class ESConfig {
    private String host;
    private int port;
    private String clusterName;
}
