package com.lirugo.print_service.graph_ql.filter;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderFilter {
    private int authorId;
    private int executorId;

    @JsonProperty("authorId")
    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    @JsonProperty("executorId")
    public int getExecutorId() {
        return executorId;
    }

    public void setExecutorId(int executorId) {
        this.executorId = executorId;
    }
}
