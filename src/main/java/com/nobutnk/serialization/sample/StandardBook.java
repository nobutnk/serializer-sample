/**
 * 
 */
package com.nobutnk.serialization.sample;

import java.io.Serializable;
import java.util.List;

/**
 * @author nobutnk
 *
 */
public class StandardBook implements Serializable {

    private static final long serialVersionUID = 1L;

    private String isbn;
    private String title;
    private int price;
    private List<String> tags;
    /**
     * @return the isbn
     */
    public String getIsbn() {
        return isbn;
    }
    /**
     * @param isbn the isbn to set
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }
    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }
    /**
     * @return the price
     */
    public int getPrice() {
        return price;
    }
    /**
     * @param price the price to set
     */
    public void setPrice(int price) {
        this.price = price;
    }
    /**
     * @return the tags
     */
    public List<String> getTags() {
        return tags;
    }
    /**
     * @param tags the tags to set
     */
    public void setTags(List<String> tags) {
        this.tags = tags;
    }

}
