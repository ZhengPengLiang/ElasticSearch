package com.springbootlearn.damo.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ESConfig {
    private String host;
    private int port;
    private String clusterName;
}
