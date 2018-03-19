package com.demo.mydemo;

/**
 * Created by V-Windows on 19.03.2018.
 *
 */

public class SearchArticle {

    private int articleId;
    private String articleName;
    private String articleImagePath;

    public SearchArticle(){
        // leerer kontstruktor
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    public String getArticleImagePath() {
        return articleImagePath;
    }

    public void setArticleImagePath(String articarticleImagePathleImage) {
        this.articleImagePath = articleImagePath;
    }
}
