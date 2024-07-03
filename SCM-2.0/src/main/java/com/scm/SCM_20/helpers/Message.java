package com.scm.SCM_20.helpers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Message {

    private String content;

    @Builder.Default
    private MessageTypes types = MessageTypes.blue;

}
