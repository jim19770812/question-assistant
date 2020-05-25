package com.training.enums;

import com.google.common.base.MoreObjects;

/**
 * 问卷状态
 */
public enum QuestionSheetStatus {
    UN_PUBLISH(0),    //待发布
    PUBLISHED(1),     //已发布
    STOPED(2)         //已停止
    ;

    private Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    QuestionSheetStatus(Integer status){
        this.status=status;
    }

    @Override
    public String toString() {
        return Integer.toString(this.status);
    }
}
