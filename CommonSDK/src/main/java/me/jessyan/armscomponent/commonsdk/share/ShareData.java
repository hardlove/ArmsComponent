package me.jessyan.armscomponent.commonsdk.share;

/**
 * Created by Chenlu on 2018/11/3
 * Email:chenlu@globalroam.com
 */
public class ShareData {

    private String title;
    private String mTitleUrl;
    private String mText;
    private String mImagePath;
    private String mUrl;
    private String mComment;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleUrl() {
        return mTitleUrl;
    }

    public void setTitleUrl(String titleUrl) {
        mTitleUrl = titleUrl;
    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
    }

    public String getImagePath() {
        return mImagePath;
    }

    public void setImagePath(String imagePath) {
        mImagePath = imagePath;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public String getComment() {
        return mComment;
    }

    public void setComment(String comment) {
        mComment = comment;
    }
}
