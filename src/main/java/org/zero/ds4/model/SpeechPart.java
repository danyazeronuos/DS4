package org.zero.ds4.model;

import lombok.Builder;

@Builder
public record SpeechPart(
        Integer id,
        String title
) {}
