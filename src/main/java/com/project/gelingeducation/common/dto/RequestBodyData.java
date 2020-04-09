package com.project.gelingeducation.common.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class RequestBodyData implements Serializable {

    private static final long serialVersionUID = -5400425570949112432L;

    private String data;
}
