package com.jinsungwon99.post.domain.content;

import com.jinsungwon99.post.domain.common.DatetimeInfo;

public abstract class Content  {

    protected String contentText;
    protected final DatetimeInfo datetimeInfo;

     protected Content(String contentText) {

        checkText(contentText);
        this.contentText = contentText;
        this.datetimeInfo = new DatetimeInfo();
    }
    public void updateContent(String updateContent){

        checkText(updateContent);
        this.contentText = updateContent;
        this.datetimeInfo.updateEditDatetime();
    }

    protected abstract void checkText(String contentText);

    public String getContentText() {
        return contentText;
    }

    public DatetimeInfo getDatetimeInfo() {
        return datetimeInfo;
    }
}
