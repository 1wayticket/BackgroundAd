package test.quzubuluo.com.app.model;

/**
 * Created by Administrator on 2017/8/17.
 */

public class PicResponse {

    /**
     * goodsId : 15
     * imgDesc :
     * imgId : 402
     * imgOriginal : images/201708/goods_img/15_P_1502073448418.jpg
     * imgUrl : images/201708/goods_img/15_P_1502073448418.jpg
     * thumbUrl :
     */

    private int goodsId;
    private String imgDesc;
    private int imgId;
    private String imgOriginal;
    private String imgUrl;
    private String thumbUrl;

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public String getImgDesc() {
        return imgDesc;
    }

    public void setImgDesc(String imgDesc) {
        this.imgDesc = imgDesc;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getImgOriginal() {
        return imgOriginal;
    }

    public void setImgOriginal(String imgOriginal) {
        this.imgOriginal = imgOriginal;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getThumbUrl() {
        return thumbUrl;
    }

    public void setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
    }
}
