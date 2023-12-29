
package cn.northpark.vo;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class PageData {

    @SerializedName("cid")
    private Long mCid;
    @SerializedName("from")
    private String mFrom;
    @SerializedName("has_alias")
    private Boolean mHasAlias;
    @SerializedName("height")
    private Long mHeight;
    @SerializedName("page")
    private Long mPage;
    @SerializedName("part")
    private String mPart;
    @SerializedName("rotate")
    private Long mRotate;
    @SerializedName("tid")
    private Long mTid;
    @SerializedName("vid")
    private String mVid;
    @SerializedName("width")
    private Long mWidth;

    public Long getCid() {
        return mCid;
    }

    public void setCid(Long cid) {
        mCid = cid;
    }

    public String getFrom() {
        return mFrom;
    }

    public void setFrom(String from) {
        mFrom = from;
    }

    public Boolean getHasAlias() {
        return mHasAlias;
    }

    public void setHasAlias(Boolean hasAlias) {
        mHasAlias = hasAlias;
    }

    public Long getHeight() {
        return mHeight;
    }

    public void setHeight(Long height) {
        mHeight = height;
    }

    public Long getPage() {
        return mPage;
    }

    public void setPage(Long page) {
        mPage = page;
    }

    public String getPart() {
        return mPart;
    }

    public void setPart(String part) {
        mPart = part;
    }

    public Long getRotate() {
        return mRotate;
    }

    public void setRotate(Long rotate) {
        mRotate = rotate;
    }

    public Long getTid() {
        return mTid;
    }

    public void setTid(Long tid) {
        mTid = tid;
    }

    public String getVid() {
        return mVid;
    }

    public void setVid(String vid) {
        mVid = vid;
    }

    public Long getWidth() {
        return mWidth;
    }

    public void setWidth(Long width) {
        mWidth = width;
    }

}
