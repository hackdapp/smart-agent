package com.cloudwise.smartagent.model;

import java.util.HashMap;
import java.util.Map;

/**
 * @author nolan
 *
 */
public class ServiceItem {
    private String id;
    private String serviceType;
    private int frequency;
    private boolean status;
    private Map<String, Object> config = new HashMap();
    
    
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
 
    public int getFrequency() {
        return frequency;
    }
    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }
    
   
    public Map<String, Object> getConfig() {
        return config;
    }
    public void setConfig(Map<String, Object> config) {
        this.config = config;
    }
    @Override
    public String toString() {
        return "ServiceItemModel [id=" + id + ", status=" + status
                + ", frequency=" + frequency + ", probability="  
                + ", config=" + config + "]";
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ServiceItem other = (ServiceItem) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}
