package com.ways.os.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wwzy on 16/3/17.
 */
public class BundleTranser implements Serializable{
    private List<? extends XModel>  serializables;

    public List<? extends XModel>  getSerializables() {
        return serializables;
    }

    public void setSerializables(List<? extends XModel> serializables) {
        this.serializables = serializables;
    }
}
