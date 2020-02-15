package com.training.auths;

import com.google.common.collect.Sets;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@NoArgsConstructor
public class JwtAuthInfo {
    private Map<String, String> infos=new HashMap<>(10);
    private Object object;
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(final String token){
        this.token=token;
    }
    public void setObject(final Object object){
        this.object=object;
    }
    public Object getObject(){
        return this.object;
    }
    public void put(final String name, final String val){
        this.infos.put(name, val);
    }
    public Set<String> keys(){
        return Sets.newHashSet(this.infos.keySet());
    }
    public boolean contains(final String name){
        return this.infos.containsKey(name);
    }
    public String get(final String name, final String defaultVal){
        return this.infos.getOrDefault(name, defaultVal);
    }

}
